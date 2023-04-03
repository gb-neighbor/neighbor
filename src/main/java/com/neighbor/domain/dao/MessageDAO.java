package com.neighbor.domain.dao;

import com.neighbor.domain.dto.BoardDTO;
import com.neighbor.domain.dto.Criteria;
import com.neighbor.domain.dto.MessageDTO;
import com.neighbor.domain.dto.MessageRoomDTO;
import com.neighbor.domain.vo.MemberVO;
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
    public List<MessageVO> findMessage(Long messageRoomId, Criteria criteria){return messageMapper.selectMessage(messageRoomId, criteria);}

    //    <!-- 전체 쪽지 개수 -->
    public Integer findCountList(Long memberId){return messageMapper.selectCountList(memberId);}

    //    <!-- 쪽지 대화 내용 개수 -->
    public Integer findCountMessage(Long messageRoomId){return messageMapper.selectCountMessage(messageRoomId);}

    //    <!-- 쪽지 대화 내역 중 가장 최근 날짜 -->
    public String findLatestDate(Long messageRoomId){return messageMapper.selectLatestDate(messageRoomId);}

    //    <!-- 대화방 번호 가져오기 -->
    public Long findMessageRoomId(Long customerId, Long boardId){return messageMapper.selectMessageRoomId(customerId, boardId);}

    //    <!-- 쪽지 상대방 정보 가져오기 -->
    public MemberVO findTargetInfo(Long memberId){return messageMapper.selectTargetInfo(memberId);}

    //    <!--  쪽지 입력  -->
    public void saveMessageVO(MessageVO messageVO){messageMapper.insertMessageVO(messageVO);}

    //    <!--  쪽지방 생성  -->
    public void saveMessageRoomVO(Long boardId, Long sellerId, Long customerId){messageMapper.insertMessageRoomVO(boardId, sellerId, customerId);}

    //    <!--  작성 게시물 수  -->
    public Integer findCountBoard(Long memberId){return messageMapper.selectCountBoard(memberId);}

    //    <!--  작성 후기 수  -->
    public Integer findCountReply(Long memberId){return messageMapper.selectCountReply(memberId);}

    //    <!--  게시글 평점  -->
    public Integer findAvgScore(Long boardId){return messageMapper.selectCountBoard(boardId);}

    //    <!--  게시글당 총 후기 수  -->
    public Integer findTotalReply(Long boardId){return messageMapper.selectTotalReply(boardId);}

    //    <!--  내가 쓴 게시글 전체 조회  -->
    public List<BoardDTO> findBoardByMemberId(Long memberId){return messageMapper.selectBoardByMemberId(memberId);}


}
