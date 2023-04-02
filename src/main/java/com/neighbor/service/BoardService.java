package com.neighbor.service;

import com.neighbor.domain.dao.BoardDAO;
import com.neighbor.domain.dao.BoardFileDAO;
import com.neighbor.domain.dao.ReplyDAO;
import com.neighbor.domain.dto.BoardDTO;
import com.neighbor.domain.dto.Critera2;
import com.neighbor.domain.vo.BoardFileVO;
import com.neighbor.domain.vo.BoardVO;
import com.neighbor.domain.vo.MemberVO;
import com.neighbor.domain.vo.ReplyVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardDAO boardDAO;
    private final BoardFileDAO boardFileDAO;
    private final ReplyDAO replyDAO;


    /*---------------------------------게시글 추가-----------------------------------*/
    public Long write(BoardDTO boardDTO) {
        boardDAO.save(boardDTO);
        Long boardId = boardFileDAO.getCurrentSeq();
        return boardId;
    }

    /* 목록페이지 게시글 가져오기 */
    public List<BoardDTO> getAllMemberBoard(Critera2 critera2, Long memberId) {
        critera2.create(getTotal());
        return boardDAO.findAllBoardMember(critera2, memberId);
    }
    
    /* 디테일 페이지 위해 보드아이디로 모든 정보 가져오기 */
    public BoardDTO getInfoForDetail(Critera2 critera2, Long boardId){
        critera2.create(getTotal());
        BoardDTO boardDTO = boardDAO.findAllBoardMemberByBoard(critera2, boardId);
        boardDTO.setFiles(boardFileDAO.getFilesByBoardId(boardId));
        List<ReplyVO> replyVOList = replyDAO.getAllReplyByBoardId(boardId);
        boardDTO.change(boardDTO.getBoardRegion());
        int avgScore = 0;
        int sum = 0;
        for (ReplyVO reply : replyVOList) {
            sum += reply.getReplyScore();
        }
        int avg = replyVOList.size() > 0 ? sum / replyVOList.size() : 0;

        // 평균 점수 더하기
        boardDTO.setAvgScore(avg);
        boardDTO.setTotalReply(replyVOList.size());

        return boardDTO;
    }

//    페이징 처리를 위해서 모든 보드갯수 가져오기
    public int getTotal(){
        return boardDAO.getTotal();
    }


// 맴버id로 모든 보드정보 가져오기
    public List<BoardVO> getBoardInfo(Long memberId) {
        return boardDAO.getBoardByMemberId(memberId);
    }

    //  관리자 페이지 판매 목록 게시글 전체 조회
    public List<BoardDTO> getList() {
        return boardDAO.findAll();
    }

    //  게시판 목록 삭제
    public void delete(Long boardId) {
        boardDAO.delete(boardId);
    }

    //  대시보드 게시글 조회
    public List<BoardDTO> getListBy() {
        return boardDAO.findAllBy();
    }

    //  관리자 페이지 판매목록 게시글 판매 대기중인 목록만 보기
    public List<BoardDTO> getWaitList() {
        return boardDAO.findWait();
    }

    //    관리자 페이지 판매 목록 게시글 전체 글 수
    public Integer getCountAll() {
        return boardDAO.countAll();
    }

    //    관리자 페이지 판매 목록 게시글 판매대기중인 글 수
    public Integer getCount() {
        return boardDAO.count();
    }

    //    게시글 제목
    public String showBoardTitle(Long boardId) {
        return boardDAO.findBoardTitle(boardId);
    }

    //    보드아이디로 맴버정보 가져오기
    public MemberVO showMemberInfoByBoardId(Long boardId){return boardDAO.findMemberInfoByBoardId(boardId);}
}
