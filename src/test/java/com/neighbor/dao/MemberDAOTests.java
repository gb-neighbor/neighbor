package com.neighbor.dao;


import com.neighbor.domain.dao.MemberDAO;
import com.neighbor.domain.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class MemberDAOTests {

    @Autowired
    private MemberDAO memberDAO;

    /* 대시보드 멤버 전체 조회 테스트*/
    @Test
    public void memberListTest() {
        List<MemberVO> memberVOList = memberDAO.list();
        log.info(String.valueOf(memberVOList));
    }

    /*회원관리 멤버 총 인원수 테스트*/
    @Test
    public void memberTotalTest() {
        int members = memberDAO.totalMembers();
        System.out.println(members);
    }


}
