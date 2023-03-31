package com.neighbor.mapper;

import com.neighbor.domain.dto.BoardDTO;
import com.neighbor.domain.vo.BoardFileVO;
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
        BoardDTO boardDTO = new BoardDTO();
        BoardFileVO boardFileVO = new BoardFileVO();
        boardDTO.setBoardContent("테스트 내용1 ");
        boardDTO.setBoardRegion(1);
        boardDTO.setBoardTitle("테스트 제목 1");
        boardDTO.setMemberId(1L);
        boardMapper.insert(boardDTO);
    }

    @Test
    public void testgetBoardMember(){
//         log.info(String.valueOf(boardMapper.getMemberBoardInfoByBoardId(1L)));
    }

    @Test
    public void testAllBoardMemberFile(){
        log.info(String.valueOf(boardMapper.selectAllBoardMember()));
    }

    @Test
    public void testSelect(){
        BoardDTO boardDTO = boardMapper.select(1);
        Assertions.assertThat(boardDTO.getBoardRegion()).isEqualTo(1);
        /* 널포인터 익셉션 어떻게 안뜨게 하지*/
    }

    /*관리자 대시보드 게시판*/
    @Test
    public void selectAllTest() {
        log.info(boardMapper.selectAll().toString());
    }

    /*관리자 게시판 목록 삭제*/
    @Test
    public void deleteTest() {
        BoardVO boardVO = new BoardVO();
        boardVO.setBoardId(4L);
        boardMapper.delete(boardVO.getBoardId());
    }


}
