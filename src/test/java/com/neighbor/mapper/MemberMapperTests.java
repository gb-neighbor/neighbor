package com.neighbor.mapper;

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
public class MemberMapperTests {

    @Autowired
    MemberMapper memberMapper;

    /*전체조회*/
    @Test
    public void selectAllTest() {
//        List<MemberDTO> listMember = memberMapper.selectAll();
//        log.info(String.valueOf(listMember));
    }

    /*회원관리 멤버 삭제*/
    @Test
    public void deleteTest() {
        MemberVO memberVO = new MemberVO();
        memberVO.setMemberId(4L);
        memberMapper.delete(4L);
    }

    @Test
    public void testInsert(){
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
        memberMapper.insert(memberVO);
    }

}
