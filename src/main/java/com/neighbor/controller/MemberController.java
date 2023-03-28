package com.neighbor.controller;

import com.neighbor.domain.dto.MemberDTO;
import com.neighbor.domain.vo.MemberVO;
import com.neighbor.service.MemberService;
import com.sun.jdi.connect.Transport;
import com.sun.xml.messaging.saaj.packaging.mime.internet.MimeBodyPart;
import com.sun.xml.messaging.saaj.packaging.mime.internet.MimeMultipart;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnailator;
import org.apache.logging.log4j.message.Message;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.HttpServletBean;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.ws.mime.MimeMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

@Controller
@RequestMapping("/members/*")
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;

   /* //    대쉬보드 전체조회 (PostMan까지 끝냄.)
    @GetMapping("list")
    public List<MemberDTO> showList(){
        return memberService.getList();
    }

    //   회원관리 멤버삭제 (PostMan까지 끝냄.)
    @DeleteMapping("delete/{memberId}")
    public void delete(@PathVariable Long memberId) {
        memberService.delete(memberId);
    }*/



    // 회원가입 페이지로 이동
    @GetMapping("register")
    public String goSignUp(){
        return "/login/join";
    }

    //메인에서 로그인으로 이동
    @GetMapping("login")
    public String goLogin() {
        return "/login/login";
    }

    // 아이디찾기 페이지로 이동
    @GetMapping("find-id")
    public String goFindId() { return"/login/find-my-id";}

    // 아이디찾기 페이지로 이동
    @GetMapping("find-password")
    public String goFindPassword() { return"/login/find-my-password";}

    // 비밀번호 변경 페이지로 이동
    @GetMapping("change-password")
    public String goChangePassword() { return"/login/update-password";}

    //회원가입
    @PostMapping("register")
    public String signUp(MemberVO memberVO) {
        memberService.signUp(memberVO);
        return "redirect:/main/main";
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
    public RedirectView login(String memberIdentification, String memberPassword, HttpSession httpSession){
        String path = "";
        Long memberId = memberService.login(memberIdentification, memberPassword);
        memberService.login(memberIdentification, memberPassword);

        if(memberService.login(memberIdentification, memberPassword) == null){
            path = "/members/login";
        }else{
            httpSession.setAttribute("memberId", memberId);
            path = "/main/main";
        }
        log.info(String.valueOf(httpSession.getAttribute("memberId")));
        return new RedirectView(path);
    }

    //로그아웃
    @GetMapping("logout")
    public RedirectView logout(HttpSession httpSession){
        httpSession.invalidate();
           return new RedirectView("/main/main");
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

}
