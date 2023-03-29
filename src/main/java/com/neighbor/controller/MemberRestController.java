package com.neighbor.controller;

import com.neighbor.domain.vo.MailVO;
import com.neighbor.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RequestMapping("/members/*")
@RestController
@Slf4j
@RequiredArgsConstructor
public class MemberRestController {

    private final MemberService memberService;

//   아이디 중복 검사
    @GetMapping("checkId")
    public Long checkId(@RequestParam("memberIdentification") String memberIdentification){
        return memberService.checkId(memberIdentification);
    }

//  닉네임 중복 검사
    @GetMapping("checkNickname")
    public Long checkNickname(@RequestParam("memberNickname") String memberNickname){
        return memberService.checkNickname(memberNickname);
    }

//  이메일 중복 검사
    @GetMapping("checkEmail")
    public Long checkEmail(@RequestParam("memberEmail") String memberEmail){
        return memberService.checkEmail(memberEmail);
    }

// 아이디 찾기
@PostMapping("send-id")
public RedirectView sendFindIdentifcationMail(String memberEmail) {
    String memberIdentification = memberService.findIdentification(memberEmail);

    if(memberIdentification == null){
        return new RedirectView("/members/login");
    }

    MailVO mailVO = new MailVO();
    mailVO.setAddress(memberEmail);
    mailVO.setTitle("이웃집 반찬 아이디 찾기");
    mailVO.setMessage("안녕하세요. 이웃집 반찬입니다.\n\n 회원님의 아이디는 " + memberIdentification + "입니다.");

    memberService.sendMail(mailVO);

    return new RedirectView("/members/login");
}

    // 비밀번호 찾기
    @PostMapping("send-password")
    public RedirectView sendFindPasswordMail(String memberEmail) {
        String memberIdentification = memberService.findIdentification(memberEmail);
        String randomkey = memberService.randomKey();


        if(memberIdentification == null){
            return new RedirectView("/members/login");
        }

        MailVO mailVO = new MailVO();
        mailVO.setAddress(memberEmail);
        mailVO.setTitle("이웃집 반찬 비밀번호 변경");
        mailVO.setMessage("안녕하세요. 이웃집 반찬입니다.\n\n 비밀번호 변경을 위해선 아래 경로에 들어가주세요 \n\n 경로: http://localhost:10000/members/change-password?memberIdentification="+memberIdentification);

        memberService.sendMail(mailVO);

        return new RedirectView("/members/login");
    }
}
