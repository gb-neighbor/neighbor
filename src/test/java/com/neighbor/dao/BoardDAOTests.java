package com.neighbor.dao;

import com.neighbor.domain.dao.BoardDAO;
import com.neighbor.domain.dao.BoardFileDAO;
import com.neighbor.domain.dto.BoardDTO;
import com.neighbor.domain.vo.BoardFileVO;
import com.neighbor.domain.vo.BoardVO;
import com.neighbor.mapper.BoardFileMapper;
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
    @Autowired
    private BoardFileDAO boardFileDAO;

    /*대시보드 게시판 목록 조회*/
    @Test
    public void dashBoardListTest() {
        List<BoardDTO> boardDAOList = boardDAO.findAll();
        log.info(String.valueOf(boardDAOList));
    }

    /*게시판 목록 삭제*/
    @Test
    public void boardDeleteTest() {
        BoardVO boardVO = new BoardVO();
        boardVO.setBoardId(4L);
        boardDAO.delete(boardVO.getBoardId());
    }

    @Test
    public void boardInsertTest(){
//        BoardVO boardVO = new BoardVO();
//        boardVO.setMemberId(1L);
//        boardVO.setBoardRegion(1);
//        boardVO.setBoardContent("다오 테스트 내용 1");
//        boardVO.setBoardTitle("다오 테스트 제목 1");
//        boardDAO.save(boardVO);
//        log.info(String.valueOf(boardDAO.getBoardId()));
    }
    @Test
    public void boardDTOTEST(){
//        List<BoardDTO> boardDTOList = boardDAO.findAllBoardMember();
//        for(BoardDTO boardDTO:boardDTOList){
//            boardDTO.change(boardDTO.getBoardRegion());
//            log.info(boardDTO.getBoardRegionKo());
//        }
    }

}
