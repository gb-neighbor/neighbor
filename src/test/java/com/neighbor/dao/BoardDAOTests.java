package com.neighbor.dao;

import com.neighbor.domain.dao.BoardDAO;
import com.neighbor.domain.dto.BoardDTO;
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

    @Test
    public void dashBoardListTest() {
        List<BoardDTO> boardDAOList = boardDAO.list();
        log.info(String.valueOf(boardDAOList));
    }

}
