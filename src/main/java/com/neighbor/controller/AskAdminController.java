package com.neighbor.controller;


import com.neighbor.domain.dao.AskAdminDAO;
import com.neighbor.domain.dto.*;
import com.neighbor.domain.vo.AskAdminVO;
import com.neighbor.service.AskAdminService;
import com.neighbor.service.BoardService;
import com.neighbor.service.MemberService;
import com.neighbor.service.ReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/askAdmins/*")
public class AskAdminController {

    private final AskAdminService askAdminService;
    private final MemberService memberService;
    private final BoardService boardService;
    private final ReplyService replyService;

    @GetMapping("write")
    public String goWrite(Model model){
        model.addAttribute(new AskAdminVO());
        return "mypage/inquiry_to_admin_write";
    }

    @PostMapping("write")
    public RedirectView goWrite(AskAdminVO askAdminVO, RedirectAttributes redirectAttributes){
        askAdminVO.setMemberId(2L);
        askAdminService.write(askAdminVO);
        redirectAttributes.addFlashAttribute(askAdminVO);
        return new RedirectView("inquiryList");
    }

    @GetMapping("inquiryList")
    public String list(Model model, AskAdminVO askAdminVO){
        askAdminVO.setMemberId(2L);
        model.addAttribute("askAdmin",askAdminService.listOne(askAdminVO.getMemberId()));
        return "mypage/inquiry_to_admin";
    }

    @GetMapping("detail/{askAdminId}")
    public String detail(Model model, @PathVariable Long askAdminId){
        model.addAttribute("askAdmin",askAdminService.detail(askAdminId));
        log.info(askAdminId.toString());
        return "mypage/inquiry_to_admin_detail";
    }


    @GetMapping("detailDelete/{askAdminId}")
    public RedirectView deleteDetail(Model model,@PathVariable Long askAdminId){
        askAdminService.deleteDetail(askAdminId);
        log.info("삭제들어옴");
        return new RedirectView("/askAdmins/inquiryList");
    }

}
