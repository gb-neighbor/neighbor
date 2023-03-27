package com.neighbor.dao;

import com.neighbor.domain.dao.ReplyDAO;
import com.neighbor.domain.dao.SignUpDAO;
import com.neighbor.domain.vo.BoardVO;
import com.neighbor.domain.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
@Slf4j
public class SignUpDAOTests {
    @Autowired
    private SignUpDAO signUpDAO;

    @Test
    public void signUpTest(){
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
        memberVO.setMemberProfileType(false);
        memberVO.setMemberProfileUuid(UUID.randomUUID().toString());
        signUpDAO.save(memberVO);
    }
}