package com.neighbor.controller;

import com.neighbor.domain.dto.MemberDTO;
import com.neighbor.domain.vo.MemberVO;
import com.neighbor.service.KakaoService;
import com.neighbor.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnailator;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Controller
@RequestMapping("/members/*")
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;
    private final KakaoService kakaoService;

    // 회원가입 페이지로 이동
    @GetMapping("register")
    public String goSignUp(){
        return "/login/join";
    }

    //메인에서 로그인으로 이동
    @GetMapping("login")
    public String goLogin(HttpServletRequest req) {
        if(req.getHeader("Cookie") != null) {
            Cookie[] cookies = req.getCookies();
            for (Cookie cookie : cookies) {
                if(cookie.getName().equals("memberVO")) {
                    req.getSession().setAttribute("memberVO", Long.valueOf(cookie.getValue()));
                    return "redirect: /main/main";
                }
            }
        }


        return "/login/login";
    }

    //로그인 실패시 로그인fail html로 이동
    @GetMapping("login-fail")
    public String goFail() {
        return "/login/fail";
    }

    //회원가입에서 로고 클릭시 세션비우고 메인으로 이동
    @GetMapping("join-to-main")
    public String joinToMain(HttpSession session){
        session.invalidate();
        return "redirect:/main";
    }

    // 아이디찾기 페이지로 이동
    @GetMapping("find-id")
    public String goFindId() { return"/login/find-my-id";}

    // 아이디찾기 페이지로 이동
    @GetMapping("find-password")
    public String goFindPassword() { return"/login/find-my-password";}

    // 비밀번호 변경 페이지로 이동
    @GetMapping("change-password")
    public String goChangePassword(String memberIdentification, String memberRandomKey) {
        String dbRandomkey = Base64.getEncoder().encodeToString(memberService.findRandomKeyByIdentification(memberIdentification).getBytes(StandardCharsets.UTF_8));
        String dbEmail = memberService.findEmailByIdentification(memberIdentification);
        if(dbRandomkey.equals(memberRandomKey)){
            memberService.updateRandomKey(null, dbEmail);
        }
        return"/login/update-password";
    }

    //회원가입
    @PostMapping("register")
    public String signUp(MemberVO memberVO, HttpSession httpSession) {
        httpSession.invalidate();
        memberService.signUp(memberVO);
        return "redirect:/main";
    }

    // 계정정보가 없을시 알림 페이지 이동
    @GetMapping("no-join")
    public String noJoin(){
        return "/login/no-join";
    }


    //    파일 업로드
    @PostMapping("upload")
    @ResponseBody
    public List<String> upload(@RequestParam("file") List<MultipartFile> multipartFiles) throws IOException {
        List<String> uuids = new ArrayList<>();
        String path = "C:/upload/profiles/" + getPath();
        File file = new File(path);
        if(!file.exists()) {file.mkdirs();}

        for(int i=0; i<multipartFiles.size(); i++){
            uuids.add(UUID.randomUUID().toString());
            multipartFiles.get(i).transferTo(new File(path, uuids.get(i) + "_" + multipartFiles.get(i).getOriginalFilename()));

            if(multipartFiles.get(i).getContentType().startsWith("image")){
                FileOutputStream out = new FileOutputStream(new File(path, "t_" + uuids.get(i) + "_" + multipartFiles.get(i).getOriginalFilename()));
                Thumbnailator.createThumbnail(multipartFiles.get(i).getInputStream(), out, 100, 100);
                out.close();
            }
        }
        return uuids;
    }

    //    파일 불러오기
    @GetMapping("display")
    @ResponseBody
    public byte[] display(String fileName) throws IOException {
        return FileCopyUtils.copyToByteArray(new File("C:/upload/profiles", fileName));
    }

    //    현재 날짜 경로 구하기
    private String getPath(){
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
    }

    // 로그인
    @PostMapping("login")
    public RedirectView login(String memberIdentification, String memberPassword, String saveEmail, HttpSession httpSession, HttpServletResponse response){
        String path = "";
        Long memberId = memberService.login(memberIdentification, memberPassword);
        memberService.login(memberIdentification, memberPassword);

        if(memberService.login(memberIdentification, memberPassword) == null){
            path = "/members/login-fail";
        }else{
            MemberVO memberVO = memberService.findInfoByIdentification(memberIdentification);
            httpSession.setAttribute("memberVO", memberVO.getMemberId());

            if(saveEmail != null){
//                Cookie memberIdentificationCookie = new Cookie("memberIdentification", memberIdentification);
//                Cookie memberPasswordCookie = new Cookie("memberPassword", memberPassword);
//                response.addCookie(memberIdentificationCookie);
//                response.addCookie(memberPasswordCookie);

                Cookie cookie = new Cookie("memberVO", String.valueOf(memberId));
                cookie.setPath("/");
                cookie.setMaxAge(60 * 60 * 3);

                response.addCookie(cookie);
            }
            if(memberVO.getMemberId() == 1){
                path = "/admin/dashboard/list";
                log.info("관리자 계정으로 들어옴 !!!!!!!!!!!!!!!!!!!!!!!");
            }else{
                log.info("메인 일반 계정으로 들어옴 !!!!!!!!!!!!!!!!!!!!!!!");
                path = "/main";
            }

        }
        log.info(path);
        return new RedirectView(path);
    }

    //로그아웃
    @GetMapping("logout")
    public RedirectView logout(HttpSession httpSession, HttpServletRequest req, HttpServletResponse resp){
        httpSession.invalidate();
        if(req.getHeader("Cookie") != null) {
            Cookie[] cookies = req.getCookies();
            for (Cookie cookie : cookies) {
                cookie.setPath("/");
                cookie.setMaxAge(0); //초단위
                resp.addCookie(cookie);
            }
        }

        return new RedirectView("/main");
    }

    //아이디 찾기
    @PostMapping("find-id")
    public void findIdentification(String memberEmail) {
        memberService.findIdentification(memberEmail);
    }

    //비밀번호 변경
    @PostMapping("change-password")
    public RedirectView updatePassword(String memberIdentification, String memberPassword ) {
        memberService.updatePassword(memberIdentification, memberPassword);
        return new RedirectView("/members/login");
    }


    //    카카오 로그인
    @GetMapping("/kakao-login")
    public String kakaoCallback(String code, HttpSession session) throws Exception {
        log.info("code1234 : " + code);
        String token = kakaoService.getKaKaoAccessToken(code);
//        session.setAttribute("token", token);
        MemberVO kakaoInfo = kakaoService.getKakaoInfo(token);
        log.info("kakaoInfo : " + kakaoInfo);
        log.info(kakaoInfo.getMemberEmail());


        if(memberService.checkEmail(kakaoInfo.getMemberEmail()) == 0){
            session.setAttribute("memberVO", kakaoInfo);
            return "redirect:no-join";
        }else{
            MemberVO memberVO = memberService.findInfoByEmail(kakaoInfo.getMemberEmail());
            session.setAttribute("memberVO", memberVO.getMemberId());
            return "redirect:/main";
        }
    }


    @GetMapping("/kakao-logout")
    public void kakaoLogout(HttpSession session){
        log.info("logout");
        memberService.logoutKakao((String)session.getAttribute("token"));
        session.invalidate();
    }

    //네이버 로그인 비교 페이지로 이동
    @GetMapping("callback")
    public String naverLogin(){
        return "/login/compare";
    }
}
