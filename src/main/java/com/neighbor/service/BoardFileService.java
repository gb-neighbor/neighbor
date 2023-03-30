package com.neighbor.service;

import com.neighbor.domain.dao.BoardFileDAO;
import com.neighbor.domain.dto.BoardDTO;
import com.neighbor.domain.vo.BoardFileVO;
import com.neighbor.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardFileService {
    private final BoardFileDAO boardFileDAO;

    //    파일 추가
    public void upload(BoardDTO boardDTO){
        boardDTO.setBoardId(boardFileDAO.getCurrentSeq());
            boardFileDAO.saveMain(boardDTO);
            boardFileDAO.saveDetail(boardDTO);
    }

    public BoardFileVO getMainFile(Long boardId){
        return boardFileDAO.getMainFile(boardId);
    }

    public List<BoardFileVO> getAllFile(Long boardId){
        return boardFileDAO.findAll(boardId);
    }

}
