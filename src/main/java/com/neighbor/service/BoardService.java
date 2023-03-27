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
public class BoardService implements FileService{
    private final BoardFileDAO boardFileDAO;
    private final BoardDAO boardDAO;

/*---------------------------------게시글 추가-----------------------------------*/
    public void write(BoardVO boardVO, BoardFileVO boardFileVO){
//        먼저 받은 썸네일 제외한 부분 다 저장하고
//        boardDAO.save(boardVO); 이거 수정하세요 팀장님
//        방금 저장한 board의 아이디 가져와서 boardFileVO에 저장 한 다음
        boardFileVO.setBoardId(boardDAO.getBoardId());
//        그걸 다시 boardFileDAO에 저장
        boardFileDAO.save(boardFileVO);
    }

    //  관리자 페이지 판매 목록 게시글 전체 조회
    public List<BoardDTO> getList(){
        return boardDAO.findAll();
    }

    //    게시판 목록 삭제
    public void delete(Long boardId) {
        boardDAO.delete(boardId);
    }

//    대시보드 게시글 조회
    public List<BoardDTO> getListBy() {
        return boardDAO.findAllBy();
    }

//    관리자 페이지 판매목록 게시글 판매 대기중인 목록만 보기
    public List<BoardDTO> getWaitList() {
        return boardDAO.findWait();
    }

//    관리자 페이지 판매 목록 게시글 전체 글 수
    public Integer getCountAll() {
        return boardDAO.countAll();
    }

//  관리자 페이지 판매 목록 게시글 판매대기중인 글 수
    public Integer getCount() {
        return boardDAO.count();
    }

}
