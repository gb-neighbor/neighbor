package com.neighbor.dao;


import com.neighbor.domain.dao.MemberDAO;
import com.neighbor.domain.dto.MemberDTO;
import com.neighbor.domain.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.UUID;

@SpringBootTest
@Slf4j
public class MemberDAOTests {

    @Autowired
    private MemberDAO memberDAO;

    /* 대시보드 멤버 전체 조회 테스트*/
    @Test
    public void memberListTest() {
        List<MemberDTO> memberVOList = memberDAO.findAll();
        log.info(String.valueOf(memberVOList));
    }

    /* 회원 관리 멤버 삭제 테스트*/
    @Test
    public void memberDeleteTest() {
        MemberVO memberVO = new MemberVO();
        memberVO.setMemberId(4L);
        memberDAO.delete(4L);
    }

    @Test
    public void registerTest(){
        MemberVO memberVO = new MemberVO();
        memberVO.setMemberId(1L);
        memberVO.setMemberIdentification("dlstk3031");
        memberVO.setMemberPassword("ids30312345!");
        memberVO.setMemberNickname("lym");
        memberVO.setMemberName("임의택");
        memberVO.setMemberBirth("19980728");
        memberVO.setMemberEmail("dlstk3031@naver.com");
        memberVO.setMemberPhone("01072716604");
        memberVO.setMemberRegion(1);
        memberVO.setMemberHiSentence("안녕하세요");
        memberVO.setMemberProfileOriginalName("테스트1.png");
        memberVO.setMemberProfilePath("2023/3/27");
        memberVO.setMemberProfileSize("1024");
        memberVO.setMemberProfileUuid(UUID.randomUUID().toString());
        memberDAO.save(memberVO);
    }

}
