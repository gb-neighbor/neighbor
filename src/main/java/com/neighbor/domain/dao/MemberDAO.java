package com.neighbor.domain.dao;

import com.neighbor.domain.dto.MemberDTO;
import com.neighbor.domain.vo.MemberVO;
import com.neighbor.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberDAO {
    private final MemberMapper memberMapper;


//    대쉬보드 전체조회
    public List<MemberDTO> findAll(){return memberMapper.selectAll();}

//    대쉬보드 일부조회
    public List<MemberDTO> findAllBy(){return memberMapper.selectAllBy();}

//    회원관리 멤버삭제
    public void delete(Long memberId) {
        memberMapper.delete(memberId);
    }


    //회원가입
    public void save(MemberVO memberVO){
        memberMapper.insert(memberVO);
    }

    // 아이디 중복
    public Long selectByIdentification(String memberIdentification){
        return memberMapper.selectByIdentification(memberIdentification);
    }

    // 닉네임 중복
    public Long selectByNickname(String memberNickName){
        return memberMapper.selectByNickname(memberNickName);
    }

    // 이메일 중복
    public Long selectByEmail(String memberEmail){
        return memberMapper.selectByEmail(memberEmail);
    }

    // 로그인
    public Long selectById(String memberIdentification, String memberPassword){ return memberMapper.selectById(memberIdentification, memberPassword);}

    //아이디 찾기
    public String selectMyIdentification(String memberEmail){ return memberMapper.selectMyIdentification(memberEmail);}

    //비밀번호 수정
    public void updateMyPassword(String memberIdentification, String memberPassword ){ memberMapper.updateMyPassword(memberIdentification, memberPassword);}
}
