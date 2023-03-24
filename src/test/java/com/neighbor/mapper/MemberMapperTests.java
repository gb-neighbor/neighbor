package com.neighbor.mapper;

import com.neighbor.domain.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class MemberMapperTests {

    @Autowired
    MemberMapper memberMapper;

    /*전체조회*/
    @Test
    public void selectAllTest() {
        List<MemberVO> listMember = memberMapper.selectAll();
        log.info(String.valueOf(listMember));
    }
    
    /*인원 수 조회*/
    @Test
    public void selectTotalMembersTest() {
        Integer members = memberMapper.selectTotalMembers();
        System.out.println(members);
    }

    /*회원관리 멤버 삭제*/
    @Test
    public void deleteTest() {
        MemberVO memberVO = new MemberVO();
        memberVO.setMemberId(4L);
        memberMapper.delete(4L);
    }

}
