package com.neighbor.mapper;

import com.neighbor.domain.dto.BoardDTO;
import com.neighbor.domain.vo.BoardVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class BoardMapperTests {

    @Autowired
    BoardMapper boardMapper;

    @Test
    public void testInsert(){
        BoardVO boardVO = new BoardVO();
        boardVO.setBoardContent("테스트 내용1 ");
        boardVO.setBoardRegion(1);
        boardVO.setBoardTitle("테스트 제목 1");
        boardVO.setMemberId(1L);
        boardMapper.insert(boardVO);
    }

    @Test
    public void testSelect(){
        BoardDTO boardDTO = boardMapper.select(1);
        Assertions.assertThat(boardDTO.getBoardRegion()).isEqualTo(1);
        /* 널포인터 익셉션 어떻게 안뜨게 하지*/
    }

    @Test
    public void selectAllTest() {
        log.info(boardMapper.selectAll().toString());
    }
}
