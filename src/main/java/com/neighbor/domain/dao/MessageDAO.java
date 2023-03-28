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

    //    <!-- 내가 보낸 쪽지 전체 -->
    public List<MessageRoomDTO> findAll(Long memberId){return messageMapper.selectAll(memberId);}

    //    <!-- 해당 게시글의 쪽지 내역-->
    public List<MessageVO> findMessage(Long messageRoomId){return messageMapper.selectMessage(messageRoomId);}

    //    <!-- 전체 쪽지 개수 -->
    public Integer findCountList(Long memberId){return messageMapper.selectCountList(memberId);}

    //    <!-- 쪽지 대화 내용 개수 -->
    public Integer findCountMessage(Long messageRoomId){return messageMapper.selectCountMessage(messageRoomId);}

    //    <!-- 쪽지 대화 내역 중 가장 최근 날짜 -->
    public String findLatestDate(Long messageRoomId){return messageMapper.selectLatestDate(messageRoomId);}

    //    <!-- 대화방 번호 가져오기 -->
    public Long findMessageRoomId(Long memberId, Long boardId){return messageMapper.selectMessageRoomId(memberId, boardId);}

}
