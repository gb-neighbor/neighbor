package com.neighbor.domain.dao;

import com.neighbor.domain.dto.BoardDTO;
import com.neighbor.domain.vo.BoardFileVO;
import com.neighbor.domain.vo.BoardVO;
import com.neighbor.mapper.BoardFileMapper;
import com.neighbor.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardDAO {

    private final BoardMapper boardMapper;

//    게시글 추가
    public void save(BoardVO boardVO){boardMapper.insert(boardVO);}
    
//    마지막에 추가된 BoardId 가져오기
    public Long getBoardId(){
        return boardMapper.getLastBoardId();
    }

//    대시보드 게시글 전체 조회
    public List<BoardDTO> findAll(){return boardMapper.selectAll();}

//    게시판 목록 삭제
    public void delete(Long boardId) {
        boardMapper.delete(boardId);
    }


}
