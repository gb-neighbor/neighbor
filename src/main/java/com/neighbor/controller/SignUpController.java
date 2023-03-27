package com.neighbor.controller;


import com.neighbor.domain.vo.MemberVO;
import com.neighbor.service.MemberService;
import com.neighbor.service.SignUpService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/signups/*")
public class SignUpController {

    private final SignUpService signUpService;

    //메인페이지로 이동
    @GetMapping("main")
    public String goMain(){
        return "/main/main";
    }

    // 회원가입 페이지로 이동
    @GetMapping("sign-up")
    public String goSignUp(Model model){
        model.addAttribute(new MemberVO());
        return "/login/join";
    }

    //회원가입
    @PostMapping("sign-up")
    public RedirectView signUp(MemberVO memberVO) {
        signUpService.signUp(memberVO);
        return new RedirectView("/signups/main");
    }

}
