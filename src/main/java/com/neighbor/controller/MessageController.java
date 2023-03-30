package com.neighbor.controller;

import com.neighbor.domain.vo.MemberVO;
import com.neighbor.domain.vo.MessageRoomVO;
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

    @PostMapping("detail/{boardId}/{memberId}/{targetId}")
    @ResponseBody
    public List<MessageVO> getMessageRoomId(@PathVariable("boardId") Long boardId, @PathVariable("memberId") Long memberId, @PathVariable("targetId") Long targetId){
        Long sellerId = boardService.showMemberInfoByBoardId(boardId).getMemberId();
        Boolean isMeSeller = sellerId == memberId;
        Long customerId = ( isMeSeller ? targetId : memberId);
        return messageService.showMessage(messageService.getMessageRoomId(customerId, boardId));
    }

    @PostMapping("targetInfo/{targetId}/{boardId}")
    @ResponseBody
    public Map<String, Object> getTargetInfos(@PathVariable("targetId") Long memberId, @PathVariable("boardId") Long boardId){
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
//      판매자 정보(번호) 가져오기
//        1. detail
//            쪽지함이 있나요?
//                sender: 반드시 구매자 (Session에 있는 사람)
//                getter: 반드시 판매자
//            쪽지함이 없나요?(무조건 구매페이지)
//                sender: 반드시 구매자 (Session에 있는 사람)
//                getter: 반드시 판매자
//
//        2. 쪽지함 (roomId가 무조건 존재하고)
//            sender: (Session에 있는 사람)
//                    이때 boardId로 sellerId 가져와서 sender와 같으면 판매자
//            getter: sellerId와 다르면 구매자
        Long boardId = messageVO.getBoardId();
        Long sellerId = boardService.showMemberInfoByBoardId(boardId).getMemberId();
        Boolean isMeSeller = sellerId == messageVO.getMessageSenderId();
        Long customerId = ( isMeSeller ? messageVO.getMessageGetterId() : messageVO.getMessageSenderId());
        Long messageRoomId = messageService.getMessageRoomId(customerId, boardId);

        if(isMeSeller){
            messageVO.setMessageRoomId(messageRoomId);
            messageService.saveMessageVO(messageVO);
            return messageVO;
        }
        if(messageRoomId != null) {
            messageVO.setMessageRoomId(messageRoomId);
            messageService.saveMessageVO(messageVO);
            return messageVO;
        }else{
            messageService.saveMessageRoomVO(boardId, sellerId, customerId);
            messageVO.setMessageRoomId(messageService.getMessageRoomId(customerId, boardId));
            messageService.saveMessageVO(messageVO);
            return messageVO;
        }
    }


}
