package com.neighbor.mapper;

import com.neighbor.domain.vo.BoardFileVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class BoardFileMapperTests {
    @Autowired
    BoardFileMapper boardFileMapper;

    @Test
    public void testInsert(){
        BoardFileVO boardFileVO = new BoardFileVO();
        boardFileVO.setBoardFileOriginalName("fdka");
        boardFileVO.setBoardFilePath("C:/upload/3/23");
        boardFileVO.setBoardFileUuid("dfdsa");
        boardFileVO.setBoardFileSize("20");
        boardFileVO.setBoardId(1L);
        boardFileMapper.insert(boardFileVO);
    }

    @Test
    public void testSelectAll(){
        log.info(String.valueOf(boardFileMapper.selectAll(1L)));
    }

    @Test
    public void testDelete(){
        boardFileMapper.delete(1L);
    }

    @Test
    public void testSelectYesterday(){
        boardFileMapper.selectYesterday();
    }
    
}
