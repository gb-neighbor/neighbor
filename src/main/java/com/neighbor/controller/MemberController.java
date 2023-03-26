package com.neighbor.controller;

import com.neighbor.domain.dto.MemberDTO;
import com.neighbor.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@RestController
@RequestMapping("/members/*")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    //    대쉬보드 전체조회 (PostMan까지 끝냄.)
    @GetMapping("list")
    public List<MemberDTO> showList(){
        return memberService.getList();
    }

    //   회원관리 멤버삭제 (PostMan까지 끝냄.)
    @DeleteMapping("delete/{memberId}")
    public void delete(@PathVariable Long memberId) {
        memberService.delete(memberId);
    }

}
