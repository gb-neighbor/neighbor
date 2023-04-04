package com.neighbor.controller;

import com.neighbor.domain.dto.*;
import com.neighbor.service.ReplyFileService;
import com.neighbor.service.ReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/replies/*")
@RequiredArgsConstructor
@Slf4j
public class ReplyController {
    private final ReplyService replyService;
    private final ReplyFileService replyFileService;

    @PostMapping("upload/{boardId}")
    @ResponseBody
    public void uploadReply(@PathVariable Long boardId, @RequestBody ReplyDTO replyDTO, Session session, CriteraForBoard criteraForBoard){
//        나중에 맴버아이디 바꾸기
        replyDTO.setMemberId(1L);
        replyDTO.setBoardId(boardId);
        replyService.saveReply(replyDTO);
        log.info(String.valueOf(replyDTO.getFiles()));
        log.info(replyDTO.getReplyContent());
        log.info(String.valueOf(replyDTO.getReplyScore()));
    }

    @PostMapping("lists/{boardId}")
    @ResponseBody
    public List<ReplyDTO> getReply(@PathVariable Long boardId, CriteraForBoard criteraForBoard){
        List<ReplyDTO> replyDTOList =  replyService.getAllReplyMemberByBoardId(boardId, criteraForBoard);
        return replyDTOList;
    }
}
