package com.neighbor.controller;

import com.neighbor.domain.dto.*;
import com.neighbor.domain.vo.ReplyVO;
import com.neighbor.service.MemberService;
import com.neighbor.service.ReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/replies/*")
@RequiredArgsConstructor
@Slf4j
public class ReplyController {

    @PostMapping("upload/{boardId}")
    public void uploadReply(@PathVariable Long boardId, ReplyVO replyVO, Session session, Critera2 critera2){
//        나중에 맴버아이디 바꾸기
        replyVO.setMemberId(1L);
        log.info(replyVO.getReplyContent());
        log.info(String.valueOf(replyVO.getReplyScore()));
    }
}
