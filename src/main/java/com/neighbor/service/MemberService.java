package com.neighbor.service;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.neighbor.domain.dao.MemberDAO;
import com.neighbor.domain.dto.MemberDTO;
import com.neighbor.domain.vo.MailVO;
import com.neighbor.domain.vo.MemberVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {
    private  final MemberDAO memberDAO;

    @Autowired
    private JavaMailSender mailSender;

    //    대쉬보드 전체조회
    public List<MemberDTO> getList(){
        return memberDAO.findAll();
    }

    //    대쉬보드 일부조회
    public List<MemberDTO> getListBy(){
        return memberDAO.findAllBy();
    }

    //   회원관리 멤버삭제
    public void delete(Long memberId) {
        memberDAO.delete(memberId);
    }

//    회원관리 멤버 총 수
    public Integer getCountAll() {
        return memberDAO.countAll();
    }

    // 회원가입
    public void signUp(MemberVO memberVO){
        memberDAO.save(memberVO);
    }

    // 아이디 중복
    public Long checkId(String memberIdentification){
        return memberDAO.selectByIdentification(memberIdentification);
    }

    // 닉네임 중복
    public Long checkNickname(String memberNickName){
        return memberDAO.selectByNickname(memberNickName);
    }

    // 이메일 중복
    public Long checkEmail(String memberEmail){
        return memberDAO.selectByEmail(memberEmail);
    }

    // 로그인
    public Long login(String memberIdentification, String memberPassword){ return memberDAO.selectById(memberIdentification, memberPassword);}

    // 아이디 찾기
    public String findIdentification(String memberEmail){ return memberDAO.selectMyIdentification(memberEmail);}

    // 비밀번호 변경
    public void updatePassword(String memberIdentification, String memberPassword ) { memberDAO.updateMyPassword(memberIdentification, memberPassword);}

    // 메일 보내기
    public void sendMail(MailVO mail) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mail.getAddress());
//        message.setFrom(""); from 값을 설정하지 않으면 application.yml의 username값이 설정됩니다.
        message.setSubject(mail.getTitle());
        message.setText(mail.getMessage());

        mailSender.send(message);
    }

//    랜덤키 발행
    public String randomKey() {
        Random random = new Random();
        String randomNum = "";

        for(int i = 0; i < 6; i++) {
            String number = Integer.toString(random.nextInt(10));
            randomNum += number;
        }

        return randomNum;
    }


//    카카오 로그인
    public String getKaKaoAccessToken(String code){
        String access_Token="";
        String refresh_Token ="";
        String reqURL = "https://kauth.kakao.com/oauth/token";

        try{
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            //POST 요청을 위해 기본값이 false인 setDoOutput을 true로
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            //POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            StringBuilder sb = new StringBuilder();
            sb.append("grant_type=authorization_code");
            sb.append("&client_id=f14254e0493a2817e0651408ee4c2504"); // TODO REST_API_KEY 입력
            sb.append("&redirect_uri=http://localhost:10000/login"); // TODO 인가코드 받은 redirect_uri 입력
            sb.append("&code=" + code);
            bw.write(sb.toString());
            bw.flush();

            //결과 코드가 200이라면 성공
            int responseCode = conn.getResponseCode();
            log.info("responseCode : " + responseCode);
            //요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }
            log.info("response body : " + result);

            //Gson 라이브러리에 포함된 클래스로 JSON파싱 객체 생성
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);

            access_Token = element.getAsJsonObject().get("access_token").getAsString();
            refresh_Token = element.getAsJsonObject().get("refresh_token").getAsString();

            log.info("access_token : " + access_Token);
            log.info("refresh_token : " + refresh_Token);

            br.close();
            bw.close();
        }catch (IOException e) {
            e.printStackTrace();
        }

        return access_Token;
    }

    public void getKakaoInfo(String token) throws Exception {

        String reqURL = "https://kapi.kakao.com/v2/user/me";

        //access_token을 이용하여 사용자 정보 조회
        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("Authorization", "Bearer " + token); //전송할 header 작성, access_token전송

            //결과 코드가 200이라면 성공
            int responseCode = conn.getResponseCode();
            log.info("responseCode : " + responseCode);

            //요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }
            log.info("response body : " + result);

            //Gson 라이브러리로 JSON파싱
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);

            int id = element.getAsJsonObject().get("id").getAsInt();
            boolean hasEmail = element.getAsJsonObject().get("kakao_account").getAsJsonObject().get("has_email").getAsBoolean();
            String email = "";
            if(hasEmail){
                email = element.getAsJsonObject().get("kakao_account").getAsJsonObject().get("email").getAsString();
            }

            log.info("id : " + id);
            log.info("email : " + email);

            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void logoutKakao(String token){
        String reqURL ="https://kapi.kakao.com/v1/user/logout";
        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");

            conn.setRequestProperty("Authorization", "Bearer " + token);
            int responseCode = conn.getResponseCode();
            log.info("responseCode : " + responseCode);

            if(responseCode ==400)
                throw new RuntimeException("카카오 로그아웃 도중 오류 발생");

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String br_line = "";
            String result = "";
            while ((br_line = br.readLine()) != null) {
                result += br_line;
            }
            log.info("결과");
            log.info(result);
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

}
