package com.neighbor.service;

import com.neighbor.domain.dao.BoardFileDAO;
import com.neighbor.domain.vo.BoardFileVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardFileService {
    private final BoardFileDAO boardFileDAO;

    //    파일 추가
    public void save(BoardFileVO boardFileVO){
        boardFileDAO.save(boardFileVO);
    };
}
