package com.neighbor.controller;

import com.neighbor.domain.vo.MessageVO;
import com.neighbor.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/messages/*")
@RequiredArgsConstructor
public class MessageRestController {

    private final MessageService messageService;

    @PostMapping("detail")
    public List<MessageVO> getMessageRoomId(@RequestBody Map<String, String> requestMap){
        Long memberId = Long.valueOf(requestMap.get("memberId"));
        Long boardId = Long.valueOf(requestMap.get("boardId"));
        return messageService.showMessage(messageService.getMessageRoomId(memberId, boardId));
    }


}
