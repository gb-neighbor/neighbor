package com.neighbor.dao;

import com.neighbor.domain.dao.BoardDAO;
import com.neighbor.domain.dto.BoardDTO;
import com.neighbor.domain.vo.BoardVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class BoardDAOTests {


    @Autowired
    private BoardDAO boardDAO;

    /*대시보드 게시판 목록 조회*/
    @Test
    public void dashBoardListTest() {
        List<BoardDTO> boardDAOList = boardDAO.list();
        log.info(String.valueOf(boardDAOList));
    }

    /*게시판 목록 삭제*/
    @Test
    public void boardDeleteTest() {
        BoardVO boardVO = new BoardVO();
        boardVO.setBoardId(4L);
        boardDAO.delete(boardVO.getBoardId());
    }

}
