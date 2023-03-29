package com.neighbor.service;

import com.neighbor.domain.dto.MemberDTO;
import com.neighbor.domain.vo.MemberVO;
import com.neighbor.mapper.MemberMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.UUID;

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

    @Test
    public void signUpTest() {
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
        memberService.signUp(memberVO);
    }

    @Test
    public void memberOneTest(){

        log.info(String.valueOf(memberService.getOneMemberInfo(1L)));
    }


}
