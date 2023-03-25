package com.neighbor.service;

import com.neighbor.domain.dao.BoardDAO;
import com.neighbor.domain.dao.BoardFileDAO;
import com.neighbor.domain.dto.BoardDTO;
import com.neighbor.domain.vo.BoardFileVO;
import com.neighbor.domain.vo.BoardVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService implements FileService{
    private final BoardFileDAO boardFileDAO;
    private final BoardDAO boardDAO;

/*---------------------------------게시글 추가-----------------------------------*/
    public void write(BoardVO boardVO, BoardFileVO boardFileVO){
//        먼저 받은 썸네일 제외한 부분 다 저장하고
        boardDAO.save(boardVO);
//        방금 저장한 board의 아이디 가져와서 boardFileVO에 저장 한 다음
        boardFileVO.setBoardId(boardDAO.getBoardId());
//        그걸 다시 boardFileDAO에 저장
        boardFileDAO.save(boardFileVO);
    }

}
