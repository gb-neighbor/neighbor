package com.neighbor.controller;

import com.neighbor.domain.vo.MemberVO;
import com.neighbor.domain.vo.MessageVO;
import com.neighbor.service.BoardService;
import com.neighbor.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.lang.reflect.Member;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/messages/*")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;
    private final BoardService boardService;

    @PostMapping("detail/{boardId}")
    @ResponseBody
    public List<MessageVO> getMessageRoomId(@PathVariable("boardId") Long boardId, HttpSession session){
        return messageService.showMessage(messageService.getMessageRoomId(1L, boardId));
    }

    @PostMapping("targetInfo/{sellerId}/{boardId}")
    @ResponseBody
    public Map<String, Object> getTargetInfos(@PathVariable("sellerId") Long memberId, @PathVariable("boardId") Long boardId){
        MemberVO memberVO = messageService.getTargetInfo(memberId);
        String boardTitle = boardService.showBoardTitle(boardId);
        Map<String, Object> result = new HashMap<>();

        result.put("targetInfo", memberVO);
        result.put("boardTitle", boardTitle);

        return result;
    }

    @PostMapping("insert")
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public MessageVO insertMessage(@RequestBody MessageVO messageVO){

        messageVO.setMessageRoomId(messageService.getMessageRoomId(messageVO.getMessageSenderId(), messageVO.getBoardId()));
        messageService.saveMessageVO(messageVO);
        return messageVO;
    }


}
