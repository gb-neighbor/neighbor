package com.neighbor.service;

import com.neighbor.domain.dao.MessageDAO;
import com.neighbor.domain.dto.MessageDTO;
import com.neighbor.domain.dto.MessageRoomDTO;
import com.neighbor.domain.vo.MessageVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageDAO messageDAO;

    public List<MessageRoomDTO> showList(Long memberId){return messageDAO.findAll(memberId);}

    public String showLatestDate(Long messageRoomId){return messageDAO.findLatestDate(messageRoomId);}

    public String showBoardTitle(Long boardId){return messageDAO.findBoardTitle(boardId);}

}
