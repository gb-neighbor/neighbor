package com.neighbor.controller;

import com.neighbor.domain.vo.MailVO;
import com.neighbor.domain.vo.MemberVO;
import com.neighbor.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

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

    //  휴대폰 번호 중복 검사
    @GetMapping("checkPhone")
    public Long checkPhone(@RequestParam("memberPhone") String memberPhone){
        return memberService.checkPhone(memberPhone);
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

        if(memberIdentification == null){
            return new RedirectView("/members/login");
        }

        String randomkey = memberService.randomKey();
        memberService.updateRandomKey(randomkey, memberEmail);

        MailVO mailVO = new MailVO();
        mailVO.setAddress(memberEmail);
        mailVO.setTitle("이웃집 반찬 비밀번호 변경");
        mailVO.setMessage("안녕하세요. 이웃집 반찬입니다.\n\n 비밀번호 변경을 위해선 아래 경로에 들어가주세요 \n\n " +
                "경로: http://localhost:10000/members/change-password?memberIdentification="+memberIdentification + "&memberRandomKey=" + Base64.getEncoder().encodeToString(randomkey.getBytes(StandardCharsets.UTF_8)));

        memberService.sendMail(mailVO);

        return new RedirectView("/members/login");
    }

    //네이버 로그인
    @PostMapping("login-naver")
    public Integer loginOauth(String memberEmail, HttpSession httpSession){
        Long memberId = memberService.loginOauth(memberEmail);
        log.info(String.valueOf(memberId));
        if(memberId == null){
            MemberVO memberVO = new MemberVO();
            memberVO.setMemberEmail(memberEmail);
            httpSession.setAttribute("memberVO", memberVO);
            return  0;
        }else{
            MemberVO memberVO = memberService.findInfoByEmail(memberEmail);
            httpSession.setAttribute("memberVO", memberVO.getMemberId());
            return 1;
        }
    }

}
