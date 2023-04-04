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

    @PostMapping("lists/{boardId}/{page}")
    @ResponseBody
    public List<ReplyDTO> getReply(@PathVariable("boardId") Long boardId, @PathVariable("page") Integer page){
        CriteraForBoard criteraForBoard = new CriteraForBoard();
        criteraForBoard.setPage(page);
        criteraForBoard.create(replyService.getReplyTotal());
        List<ReplyDTO> replyDTOList =  replyService.getAllReplyMemberByBoardId(boardId, criteraForBoard);
        log.info("boardId: "+ boardId);
        log.info(replyDTOList.toString());
        return replyDTOList;
    }
}
