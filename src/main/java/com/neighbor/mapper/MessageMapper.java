package com.neighbor.mapper;

import com.neighbor.domain.dto.MessageDTO;
import com.neighbor.domain.dto.MessageRoomDTO;
import com.neighbor.domain.vo.MessageVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MessageMapper {

//    <!-- 내가 보낸 쪽지 전체 -->
    public List<MessageRoomDTO> selectAll(Long memberId);

//    <!-- 해당 게시글의 쪽지 내역-->
    public List<MessageVO> selectOne(Long messageRoomId);

//    <!-- 전체 쪽지 개수 -->
    public Integer selectCountList(Long memberId);

//    <!-- 쪽지 대화 내용 개수 -->
    public Integer selectCountMessage(Long messageRoomId);

//    <!-- 쪽지 대화 내역 중 가장 최근 날짜 -->
    public String selectLatestDate(Long messageRoomId);

//    <!-- 보드 제목 -->
    public String selectBoardTitle(Long boardId);



}
