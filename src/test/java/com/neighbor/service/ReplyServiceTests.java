package com.neighbor.service;

import com.neighbor.controller.ReplyController;
import com.neighbor.domain.dto.Critera2;
import com.neighbor.domain.dto.ReplyDTO;
import com.neighbor.domain.vo.MemberVO;
import com.neighbor.domain.vo.ReplyFileVO;
import com.neighbor.mapper.ReplyMapper;
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
        Critera2 critera2 = new Critera2();
        critera2.setPage(1);
        critera2.create(10);
        log.info(String.valueOf(replyService.getAllReplyMemberByBoardId(49L, critera2)));
    }

    @Test
    public void testController(){
        Long boardId = 49L;
        Critera2 critera2 = new Critera2();
        critera2.setPage(1);
        log.info(String.valueOf(replyController.getReply(boardId, critera2)));
    }



}
