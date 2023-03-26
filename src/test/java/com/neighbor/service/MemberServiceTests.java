package com.neighbor.service;

import com.neighbor.domain.dto.MemberDTO;
import com.neighbor.domain.vo.MemberVO;
import com.neighbor.mapper.MemberMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class MemberServiceTests {

    @Autowired
    MemberService memberService;

    @Test
    public void getListTest() {
        memberService.getList();
    }

    @Test
    public void deleteTest() {
        MemberVO memberVO = new MemberVO();
        memberVO.setMemberId(3L);
        memberService.delete(memberVO.getMemberId());
    }


}
