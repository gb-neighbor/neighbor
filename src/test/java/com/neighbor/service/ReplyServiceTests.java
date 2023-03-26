package com.neighbor.service;

import com.neighbor.domain.dto.ReplyDTO;
import com.neighbor.domain.vo.MemberVO;
import com.neighbor.mapper.ReplyMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class ReplyServiceTests {

    @Autowired
    private ReplyService replyService;

    /*전체조회 테스트*/
    @Test
    public void getListTest() {
        replyService.getList();
    }

    /*삭제 테스트*/
    @Test
    public void deleteTest() {
        MemberVO memberVO = new MemberVO();
        memberVO.setMemberId(3L);
        replyService.delete(memberVO.getMemberId());
    }

}
