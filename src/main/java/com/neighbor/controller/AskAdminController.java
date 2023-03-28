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

    //    대시보드 전체조회
    @GetMapping("listAll")
    public String showAllList(Model model){
        List<BoardDTO> boardDTOS = new ArrayList<>();
        List<MemberDTO> memberDTOS = new ArrayList<>();

        memberDTOS = memberService.getListBy();
        for(MemberDTO memberDTO:memberDTOS){
            memberDTO.change(memberDTO.getMemberRegion());
        };
        model.addAttribute("members", memberDTOS);

        boardDTOS = boardService.getListBy();
        for(BoardDTO boardDTO:boardDTOS){
            boardDTO.change(boardDTO.getBoardRegion());
            boardDTO.saleChange(boardDTO.getBoardStatus());
        };
        model.addAttribute("boards", boardDTOS);
        
        model.addAttribute("asks", askAdminService.getListBy());
        model.addAttribute("replys", replyService.getListBy());
        return "admin/dash-board";
    }

// 문의목록 문의사항 답변대기중 조회
    @GetMapping("list")
    public String showList(Criteria criteria, Model model) {
      if(criteria.getPage() == 0){
         criteria.create(1,6);
      }

        model.addAttribute("Asks", askAdminService.getListAll(criteria.create(criteria.getPage(), criteria.getAmount()))); // 업데이트된 Criteria 객체 전달
        model.addAttribute("pagination", new PageDTO().createPageDTO(criteria, askAdminService.getCountAll()));

    return "admin/manage-qna";
    }


    //   문의목록 문의사항 삭제
   @DeleteMapping("delete/{askAdminId}")
    public void delete(@PathVariable Long askAdminId) {
        askAdminService.delete(askAdminId);
    }

//    문의목록 총 질문 수
    @GetMapping("countAll")
    public Integer getTotal() {
        return askAdminService.getCountAll();
    }

//    문의목록 답변 대기 중인 질문 수
    @GetMapping("count")
    public Integer getWaitAnswerTotal() {
        return askAdminService.getCount();
    }


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
