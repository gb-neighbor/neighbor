package com.neighbor.service;

import com.neighbor.domain.dao.MessageDAO;
import com.neighbor.domain.dto.*;
import com.neighbor.domain.vo.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageDAO messageDAO;

    //    <!-- 내가 보낸 쪽지 전체 -->
    public List<MessageRoomDTO> showList(Long memberId) {
        return messageDAO.findAll(memberId);
    }

    //    <!-- 해당 게시글의 쪽지 내역-->
    public List<MessageVO> showMessage(Long messageRoomId, Criteria criteria) {
        return messageDAO.findMessage(messageRoomId, criteria);
    }

    //    <!-- 전체 쪽지 개수 -->
    public Integer getCountList(Long memberId) {
        return messageDAO.findCountList(memberId);
    }

    //    <!-- 쪽지 대화 내용 개수 -->
    public Integer getCountMessage(Long messageRoomId) {
        return messageDAO.findCountMessage(messageRoomId);
    }

    //    <!-- 쪽지 대화 내역 중 가장 최근 날짜 -->
    public String showLatestDate(Long messageRoomId) {
        return messageDAO.findLatestDate(messageRoomId);
    }

    //    <!-- 대화방 번호 가져오기 -->
    public Long getMessageRoomId(Long customerId, Long boardId) {
        return messageDAO.findMessageRoomId(customerId, boardId);
    }

    //    <!-- 쪽지 상대방 정보 가져오기 -->
    public MemberVO getTargetInfo(Long memberId) {
        return messageDAO.findTargetInfo(memberId);
    }

    //    <!--  쪽지 입력  -->
    public void saveMessageVO(MessageVO messageVO) {
        messageDAO.saveMessageVO(messageVO);
    }

    //    <!--  쪽지방 생성  -->
    public void saveMessageRoomVO(Long boardId, Long sellerId, Long customerId) {
        messageDAO.saveMessageRoomVO(boardId, sellerId, customerId);
    }

    //    <!--  작성 게시물 수  -->
    public Integer getCountBoard(Long memberId){return messageDAO.findCountBoard(memberId);}

    //    <!--  작성 후기 수  -->
    public Integer getCountReply(Long memberId){return messageDAO.findCountReply(memberId);}

    //    <!--  게시글당 총 후기 수  -->
    public Integer getTotalReply(Long boardId){return messageDAO.findTotalReply(boardId);}

    //    <!--  게시글 평점  -->
    public Integer getAvgScore(Long boardId){return messageDAO.findAvgScore(boardId);}

    //    <!--  내가 쓴 게시글 전체 조회  -->
    public List<BoardDTO> getBoardByMemberId(Long memberId){return messageDAO.findBoardByMemberId(memberId);}

    //    <!--  비밀번호 변경  -->
    public void setNewPassword(String memberPassword, Long memberId){messageDAO.changePassword(memberPassword, memberId);}

    //    <!--  내가 작성한 후기  -->
    public List<ReplyDTO> getReplyByMemberId(Long memberId){return messageDAO.findReplyByMemberId(memberId);}

    //    <!--  게시글 썸네일 가져오기  -->
    public BoardFileVO getBoardThumbnail(Long boardId){return messageDAO.findBoardThumbnail(boardId);}

    //    <!--  판매자 아이디 가져오기  -->
    public Long getSellerId(Long boardId){return messageDAO.findSellerId(boardId);}

    //    <!-- 게시글 정보 가져오기 -->
    public BoardVO getBoardByBoardId(Long boardId){return messageDAO.findBoardByBoardId(boardId);}

    //  <!--  게시글 판매 상태 바꾸기  -->
    public void setBoardStatus(Long boardId){messageDAO.changeBoardStatus(boardId);}

    //    <!-- 게시글 판매 상태 업데이트 -->
    public void setPurchaseStatus(Long boardId, Long memberId){messageDAO.savePurchaseStatus(boardId, memberId);}

    //    <!-- 게시글 판매 상태 조회 -->
    public Long getPurchase(Long boardId, Long memberId){ return messageDAO.findPurchase(boardId, memberId);}


}