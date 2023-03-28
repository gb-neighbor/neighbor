package com.neighbor.mapper;

import com.neighbor.domain.dto.BoardDTO;
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
        BoardDTO boardDTO= new BoardDTO();
        boardDTO.setBoardId(3L);
        boardDTO.setFileMainSize("20MB");
        boardDTO.setFileMainName("djfk");
        boardDTO.setFileMainUuid("fdsaf");
        boardDTO.setFileMainPath("C:upload");
        boardFileMapper.insertMain(boardDTO);
    }

    @Test
    public void testInsertMain(){

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
