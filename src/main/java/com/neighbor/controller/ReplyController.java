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

    @GetMapping("list")
    public String showList(Criteria criteria, Model model, @RequestParam(value = "keyword", required = false) String keyword) {
        if (criteria.getPage() == 0) {
            criteria = criteria.create(1, 6);
        }

        List<ReplyDTO> replyDTOS;
        replyDTOS = replyService.getList(criteria, keyword);

        for (ReplyDTO dto : replyDTOS) {
            dto.change(dto.getBoardRegion());
        }

        model.addAttribute("replys", replyDTOS);
        model.addAttribute("pagination", new PageDTO().createPageDTO(criteria, replyService.getCountAll()));
        model.addAttribute("replyCount", replyService.getCountAll());

        return "admin/manage-reply";
    }


    //  후기관리 삭제 (PostMan까지 확인함.)
    @DeleteMapping("delete")
    @ResponseBody
    public void delete(@RequestParam("checkedIds[]") List<String> checkIds){
        replyService.delete(checkIds);
    }


}
