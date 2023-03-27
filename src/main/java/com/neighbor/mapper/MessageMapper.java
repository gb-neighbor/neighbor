package com.neighbor.mapper;

import com.neighbor.domain.dto.MessageDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MessageMapper {
//    <!-- 내가 보낸 쪽지 전체 -->
    public List<MessageDTO> selectAll(Long memberId);

//    <!-- 해당 게시글의 쪽지 내역-->
    public List<MessageDTO> selectByBoardId(Long boardId, Long memberId, Long targetId);
}
