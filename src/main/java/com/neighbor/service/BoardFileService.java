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
//        메인파일과 서브파일 두개 다 가져왔다면
        if(boardDTO.getFileMainName() != null && boardDTO.getFiles() != null){
            boardFileDAO.saveMain(boardDTO);
            boardFileDAO.saveDetail(boardDTO);
            return;
//            서브파일이 들어오지 않고, 메인파일만 들어왔다면
        } else {
            boardFileDAO.saveMain(boardDTO);
        }
    }

    public List<BoardFileVO> getAllFile(Long boardId){
        return boardFileDAO.findAll(boardId);
    }

}
