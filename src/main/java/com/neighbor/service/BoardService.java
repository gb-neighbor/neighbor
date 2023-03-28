package com.neighbor.service;

import com.neighbor.domain.dao.BoardDAO;
import com.neighbor.domain.dao.BoardFileDAO;
import com.neighbor.domain.dto.BoardDTO;
import com.neighbor.domain.vo.BoardFileVO;
import com.neighbor.domain.vo.BoardVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService implements FileService {
    private final BoardDAO boardDAO;
    private final BoardFileDAO boardFileDAO;

    /*---------------------------------게시글 추가-----------------------------------*/
    public void write(BoardDTO boardDTO) {
        boardDAO.save(boardDTO);
    }
    
    /* 목록페이지 게시글 가져오기 */
    public List<BoardDTO> getAllMemberBoard(){
        return boardDAO.findAllBoardMember();
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


}
