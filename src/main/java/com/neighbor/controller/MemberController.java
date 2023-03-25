package com.neighbor.controller;

import com.neighbor.domain.vo.MemberVO;
import com.neighbor.service.SignUpService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member/*")
public class MemberController {
    private final SignUpService signUpService;

    //    등록
    @GetMapping("sign-up")
    public void register(Model model){
        model.addAttribute(new MemberVO());
    }

    //    등록 완료
    @PostMapping("sign-up")
    public RedirectView register(MemberVO memberVO) {
        signUpService.signUp(memberVO);
        return new RedirectView("");
    }

}
