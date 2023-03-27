package com.neighbor.controller;

import com.neighbor.domain.dto.MemberDTO;
import com.neighbor.domain.dto.ReplyDTO;
import com.neighbor.service.MemberService;
import com.neighbor.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/replys/*")
@RequiredArgsConstructor
public class ReplyController {
    private final ReplyService replyService;

//    //    대쉬보드 게시글 관리 (PostMan까지 확인함.)
    @GetMapping("list")
    public List<ReplyDTO> showList() {
        return replyService.getList();
    }

    //  후기관리 삭제 (PostMan까지 확인함.)
    @DeleteMapping("delete/{memberId}")
    public void delete(@PathVariable Long memberId){
        replyService.delete(memberId);
    }

}
