package com.neighbor.controller;

import com.neighbor.domain.vo.MemberVO;
import com.neighbor.service.BoardService;
import com.neighbor.service.MemberService;
import com.neighbor.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/mypage/*")
public class MypageController {

    private final MemberService memberService;
    private final MessageService messageService;
    private final BoardService boardService;

    //   세션 매개변수로 받기
    @GetMapping("message_box")
    public void goToMessageBox(Model model){

    }

//   회원탈퇴, 매개변수로 회원 아이디 받기
    @GetMapping("user_leave")
    public void goToUserLeave(Model model){
        MemberVO memberVO = memberService.getOneMemberInfo(1L);



        model.addAttribute("memberInfo", memberVO);
        model.addAttribute("memberId", 1L);
    }

    @PostMapping("leave")
    public String deleteMember(Long memberId){
            memberService.delete(memberId);
        return "redirect:/main";
    }

}
