package com.neighbor.service;

import com.neighbor.controller.ReplyController;
import com.neighbor.domain.dto.CriteraForBoard;
import com.neighbor.domain.dto.ReplyDTO;
import com.neighbor.domain.vo.ReplyFileVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Slf4j
public class ReplyServiceTests {

    @Autowired
    private ReplyService replyService;
    private ReplyController replyController;

    /*전체조회 테스트*/
//    @Test
//    public void getListTest() {
//        replyService.getList();
//    }

    /*삭제 테스트*/
//    @Test
//    public void deleteTest() {
//        MemberVO memberVO = new MemberVO();
//        memberVO.setMemberId(3L);
//        replyService.delete(memberVO.getMemberId());
//    }
    
//    조회 테스트
//    @Test
//    public void getBoard(){
//        log.info(String.valueOf(replyService.getListByBoardId(1L)));
//    }

    @Test
    public void testReply(){
//        ReplyDTO replyDTO = new ReplyDTO();
//        ReplyFileVO replyFileVO = new ReplyFileVO();
//        replyDTO.setMemberId(1L);
//        replyDTO.setReplyScore(0);
//        replyDTO.setBoardId(1L);
//        replyDTO.setReplyContent("fjdklsjfkdlafd");
//        List<ReplyFileVO> files = new ArrayList<>();
//        replyFileVO.setReplyFileOriginalName("1234");
//        replyFileVO.setReplyFileOriginalName("123");
//        replyFileVO.setReplyFilePath("/c/upload");
//        replyFileVO.setReplyFileUuid("1234");
//        replyFileVO.setReplyFileSize("1213");
//        files.add(replyFileVO);
//        replyDTO.setFiles(files);
//        log.info(String.valueOf(replyDTO));
//        replyService.saveReply(replyDTO);
    }

    @Test
    public void testRepliesGet(){
//        CriteraForBoard criteraForBoard = new CriteraForBoard();
//        criteraForBoard.setPage(1);
//        criteraForBoard.create(10);
//        log.info(String.valueOf(replyService.getAllReplyMemberByBoardId(1L, criteraForBoard)));
    }
    @Test
    public void testGet(){
        log.info(String.valueOf(replyService.getCountReplyMember(20L, 3L)));
    }


}
