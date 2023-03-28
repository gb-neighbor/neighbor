package com.neighbor.domain.dao;

import com.neighbor.domain.dto.MessageDTO;
import com.neighbor.domain.dto.MessageRoomDTO;
import com.neighbor.domain.vo.MessageVO;
import com.neighbor.mapper.MessageMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MessageDAO {

    private final MessageMapper messageMapper;

    public List<MessageRoomDTO> findAll(Long memberId){return messageMapper.selectAll(memberId);}

    public String findLatestDate(Long messageRoomId){return messageMapper.selectLatestDate(messageRoomId);}

    public String findBoardTitle(Long boardId){return messageMapper.selectBoardTitle(boardId);}

}
