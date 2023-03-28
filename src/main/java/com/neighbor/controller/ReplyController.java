package com.neighbor.controller;

import com.neighbor.domain.dto.*;
import com.neighbor.service.MemberService;
import com.neighbor.service.ReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/replys/*")
@RequiredArgsConstructor
@Slf4j
public class ReplyController {
    private final ReplyService replyService;

//    //    대쉬보드 게시글 관리 (PostMan까지 확인함.)
//    @GetMapping("list")
//    @ResponseBody
//    public String showList(Criteria criteria, Model model,@RequestParam("keyword") String keyword) {
//        ReplyDTO replyDTO = new ReplyDTO();
//        if(criteria.getPage() == 0){
//            criteria.create(1,6);
//        }
//
//        model.addAttribute("Replys", replyService.getList(criteria.create(criteria.getPage(), criteria.getAmount()))); // 업데이트된 Criteria 객체 전달
//        model.addAttribute("pagination", new PageDTO().createPageDTO(criteria, replyService.getCountAll()));
//        List<ReplyDTO> replyDTOS = new ArrayList<>();
//        replyDTOS = replyService.getList(criteria);
//        for(ReplyDTO replyDTO:replyDTOS){
//            replyDTO.change(replyDTO.getBoardRegion());
//        };
//        model.addAttribute("replys", replyDTOS);
//        model.addAttribute("replyCount",replyService.getCountAll());
//        replyDTO.setKeyword(keyword);
//
//        return "admin/manage-reply";
//    }

    @GetMapping("list")
    public String showList(Criteria criteria, Model model, @RequestParam(value = "keyword", required = false) String keyword) {
        if (criteria.getPage() == 0) {
            criteria.create(1, 6);
        }

        ReplyDTO replyDTO = new ReplyDTO();
        replyDTO.setKeyword(keyword); // ReplyDTO에 keyword 설정

        List<ReplyDTO> replyDTOS;
        if (keyword != null) { // keyword가 전달된 경우
            replyDTOS = replyService.getSearchList(criteria, replyDTO);
        } else { // keyword가 전달되지 않은 경우
            replyDTOS = replyService.getList(criteria);
        }

        for (ReplyDTO dto : replyDTOS) {
            dto.change(dto.getBoardRegion());
        }

        model.addAttribute("replys", replyDTOS);
        model.addAttribute("pagination", new PageDTO().createPageDTO(criteria, replyService.getCountAll()));
        model.addAttribute("replyCount", replyService.getCountAll());

        return "admin/manage-reply";
    }


    //  후기관리 삭제 (PostMan까지 확인함.)
    @DeleteMapping("delete/{memberId}")
    public void delete(@PathVariable("memberId") Long memberId){
        replyService.delete(memberId);
    }


}
