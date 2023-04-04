package com.neighbor.controller;

import com.neighbor.domain.dto.Criteria;
import com.neighbor.domain.dto.MessageDTO;
import com.neighbor.domain.dto.MessageRoomDTO;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/messages/*")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;
    private final BoardService boardService;

    @PostMapping("detail/{boardId}/{memberId}/{targetId}/{page}")
    @ResponseBody
    public List<MessageVO> getMessageRoomId(@PathVariable("boardId") Long boardId, @PathVariable("memberId") Long memberId, @PathVariable("targetId") Long targetId, @PathVariable("page") int page){
        Criteria criteria = new Criteria();

        Long sellerId = boardService.showMemberInfoByBoardId(boardId).getMemberId();
        Boolean isMeSeller = sellerId == memberId;
        Long customerId = ( isMeSeller ? targetId : memberId);
        Long messageRoomId = messageService.getMessageRoomId(customerId, boardId);
//        Integer amount = messageService.getCountMessage(messageRoomId);

        criteria.setAmount(20);
        criteria.setPage(page);
        criteria.setOffset(criteria.getOffset());

        List<MessageVO> messageVOs = messageService.showMessage(messageRoomId, criteria);

        if(messageVOs !=null){
            return messageVOs;
        }

        return null;
    }

    @PostMapping("targetInfo/{targetId}/{boardId}")
    @ResponseBody
    public Map<String, Object> getTargetInfos(@PathVariable("targetId") Long memberId, @PathVariable("boardId") Long boardId){
        MemberVO memberVO = messageService.getTargetInfo(memberId);
        String boardTitle = boardService.showBoardTitle(boardId);
        Map<String, Object> result = new HashMap<>();

        result.put("targetInfo", memberVO);
        result.put("boardTitle", boardTitle);
        result.put("boardId", boardId);

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


    @PostMapping("list/{memberId}")
    @ResponseBody
    public List<MessageDTO> getMyMessageList(@PathVariable("memberId") Long memberId){
        List<MessageDTO> result = new ArrayList<>();
        List<MessageRoomDTO> entireList = messageService.showList(memberId);

        for (MessageRoomDTO messageRoom : entireList) {
            MessageDTO messageDTO = new MessageDTO();

            messageDTO.setBoardId(messageRoom.getBoardId());
            messageDTO.setBoardTitle(boardService.showBoardTitle(messageRoom.getBoardId()));
            messageDTO.setLatestRegisterDate(messageService.showLatestDate(messageRoom.getMessageRoomId()));
            messageDTO.setMessageRoomId(messageRoom.getMessageRoomId());
            messageDTO.setTargetId(messageService.getTargetInfo(messageRoom.getTargetId()).getMemberId());
            messageDTO.setTargetNickname(messageService.getTargetInfo(messageRoom.getTargetId()).getMemberNickname());
            messageDTO.setTargetProfilePath(messageService.getTargetInfo(messageRoom.getTargetId()).getMemberProfilePath());
            messageDTO.setTargetProfileUuid(messageService.getTargetInfo(messageRoom.getTargetId()).getMemberProfileUuid());
            messageDTO.setTargetProfileOriginalName(messageService.getTargetInfo(messageRoom.getTargetId()).getMemberProfileOriginalName());
            messageDTO.setSellerId(messageService.getSellerId(messageRoom.getBoardId()));
            messageDTO.setBoardProfileOriginalName(messageService.getBoardThumbnail(messageRoom.getBoardId()).getBoardFileOriginalName());
            messageDTO.setBoardProfilePath(messageService.getBoardThumbnail(messageRoom.getBoardId()).getBoardFilePath());
            messageDTO.setBoardProfileUuid(messageService.getBoardThumbnail(messageRoom.getBoardId()).getBoardFileUuid());
            Long sellerId = messageDTO.getSellerId();
            Long customerId = (memberId==sellerId)? messageDTO.getTargetId() : memberId;
            messageDTO.setPurchaseStatus(messageService.getPurchase(messageRoom.getBoardId(), customerId));

            result.add(messageDTO);
        }

        return result;
    }

    @PostMapping("status/{boardId}/{memberId}")
    @ResponseBody
    public void updateBoardStatus(@PathVariable("boardId") Long boardId, @PathVariable("memberId") Long memberId){
        messageService.setPurchaseStatus(boardId, memberId);
    }


}
