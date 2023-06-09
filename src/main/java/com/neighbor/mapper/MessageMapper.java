package com.neighbor.mapper;

import com.neighbor.domain.dto.*;
import com.neighbor.domain.vo.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MessageMapper {

//    <!-- 내가 보낸 쪽지 전체 -->
    public List<MessageRoomDTO> selectAll(Long memberId, Criteria criteria);

//    <!-- 해당 게시글의 쪽지 내역-->
    public List<MessageVO> selectMessage(Long messageRoomId, Criteria criteria);

//    <!-- 전체 쪽지 개수 -->
    public Integer selectCountList(Long memberId);

//    <!-- 쪽지 대화 내용 개수 -->
    public Integer selectCountMessage(Long messageRoomId);

//    <!-- 쪽지 대화 내역 중 가장 최근 날짜 -->
    public String selectLatestDate(Long messageRoomId);

//    <!-- 대화방 번호 가져오기 -->
    public Long selectMessageRoomId(Long customerId, Long boardId);

//    <!-- 쪽지 상대방 정보 가져오기 -->
    public MemberVO selectTargetInfo(Long memberId);

//    <!--  쪽지 입력  -->
    public void insertMessageVO(MessageVO messageVO);

//    <!--  쪽지방 생성  -->
    public void insertMessageRoomVO(Long boardId, Long sellerId, Long customerId);

//    <!--  작성 게시물 수  -->
    public Integer selectCountBoard(Long memberId);

//    <!--  작성 후기 수  -->
    public Integer selectCountReply(Long memberId);

//    <!--  게시글 평점  -->
    public Integer selectAvgScore(Long boardId);

//    <!--  게시글당 총 후기 수  -->
    public Integer selectTotalReply(Long boardId);

//    <!--  키워드별 작성 후기 수  -->
    public Integer selectCountReplyByKeyword(Long memberId, @Param("keyword") String keyword);

//    <!--  내가 쓴 게시글 전체 조회  -->
    public List<BoardDTO> selectBoardByMemberId(Long memberId, Criteria criteria);

//    <!--  비밀번호 변경  -->
    public void updatePassword(String memberPassword, Long memberId);

//    <!--  내가 작성한 후기  -->
    public List<ReplyDTO> selectReplyByMemberId(Long memberId, @Param("cri") Criteria criteria, @Param("keyword") String keyword);

//    <!--  게시글 썸네일 가져오기  -->
    public BoardFileVO selectBoardThumbnail(Long boardId);

//    <!--  판매자 아이디 가져오기  -->
    public Long selectSellerId(Long boardId);

//    <!-- 게시글 정보 가져오기 -->
    public BoardVO selectBoardByBoardId(Long boardId);

//    <!--  게시글 판매 상태 바꾸기  -->
    public void updateBoardStatus(Long boardId);

//    <!-- 게시글 판매 상태 업데이트 -->
    public void insertPurchaseStatus(Long boardId, Long memberId);

//    <!-- 게시글 판매 상태 조회 -->
    public Long selectPurchase(Long boardId, Long memberId);


}
