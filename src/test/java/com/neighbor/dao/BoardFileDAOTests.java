package com.neighbor.dao;

import com.neighbor.domain.dao.BoardFileDAO;
import com.neighbor.domain.vo.BoardFileVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class BoardFileDAOTests {
    @Autowired
    BoardFileDAO boardFileDAO;

    @Test
    public void isnertDAOTest(){
        BoardFileVO boardFileVO = new BoardFileVO();
        boardFileVO.setBoardFileOriginalName("fdka");
        boardFileVO.setBoardFilePath("C:/upload/3/23");
        boardFileVO.setBoardFileUuid("dfdsa");
        boardFileVO.setBoardFileSize("20");
        boardFileVO.setBoardId(1L);
        boardFileDAO.save(boardFileVO);
    }

    @Test
    public void deleteDAOTest(){
        boardFileDAO.delete(3L);
    }

}
