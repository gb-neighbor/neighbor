package com.neighbor.service;

import com.neighbor.domain.dao.BoardDAO;
import com.neighbor.domain.dao.BoardFileDAO;
import com.neighbor.domain.dto.BoardDTO;
import com.neighbor.domain.vo.BoardFileVO;
import com.neighbor.domain.vo.BoardVO;
import com.neighbor.domain.vo.MemberVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardDAO boardDAO;
    private final BoardFileDAO boardFileDAO;

    /*---------------------------------게시글 추가-----------------------------------*/
    public Long write(BoardDTO boardDTO) {
        boardDAO.save(boardDTO);
        Long boardId = boardFileDAO.getCurrentSeq();
        return boardId;
    }

    /* 목록페이지 게시글 가져오기 */
    public List<BoardDTO> getAllMemberBoard() {
        return boardDAO.findAllBoardMember();
    }

    public List<BoardVO> getBoardInfo(Long memberId) {
//      List로 board 정보 다 가져옴
        List<BoardVO> boardVOList = boardDAO.getBoardByMemberId(memberId);
        for (BoardVO boardVO : boardVOList) {
//            이거를 reply에 넣어주고 reply를 List로 가져오는 매퍼를 짜주면 됨, 이걸 reply Service에서 작업한 후 컨트롤러에서 합치기
            boardVO.getBoardId();
        }
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
