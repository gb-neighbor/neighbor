package com.neighbor.service;

import com.neighbor.domain.dto.BoardDTO;
import com.neighbor.domain.vo.BoardVO;
import com.neighbor.mapper.BoardMapper;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class BoardServiceTests {

    @Autowired
    private BoardService boardService;

    /* 대시보드 게시글 전체조회 테스트*/
    @Test
    public void getListTest() {
//        boardService.getList();
    }

    /* 게시판 목록 삭제 테스트*/
    @Test
    public void deleteTest() {
        BoardVO boardVO = new BoardVO();
        boardVO.setBoardId(4L);
        boardService.delete(boardVO.getBoardId());
    }

}
