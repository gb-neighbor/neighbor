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

//    맴버아이디로 맴버 정보 전체 조회하기
    public MemberVO getOneMember(Long memberId){
        return memberMapper.selectOne(memberId);
    }

//    대쉬보드 일부조회
    public List<MemberDTO> findAllBy(){return memberMapper.selectAllBy();}

//    회원관리 멤버삭제
    public void delete(Long memberId) {
        memberMapper.delete(memberId);
    }
    
//    회원관리 멤버 총 수
    public Integer countAll() {
        return memberMapper.countAll();
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

    // 휴대폰 중복
    public Long selectByPhone(String memberPhone) { return memberMapper.selectByPhone(memberPhone);}

    // 로그인
    public Long selectById(String memberIdentification, String memberPassword){ return memberMapper.selectById(memberIdentification, memberPassword);}

    // 네이버 로그인
    public Long compareEmail(String memberEmail) {return memberMapper.compareEmail(memberEmail);}

    //아이디 찾기
    public String selectMyIdentification(String memberEmail){ return memberMapper.selectMyIdentification(memberEmail);}

    //비밀번호 수정
    public void updateMyPassword(String memberIdentification, String memberPassword ){ memberMapper.updateMyPassword(memberIdentification, memberPassword);}

    //이메일로 회원정보 조회
    public MemberVO selectInfoByEmail(String memberEmail) {return memberMapper.selectInfoByEmail(memberEmail);}

    //아이디로 회원정보 조회
    public MemberVO selectInfoByIdentification(String memberIdentification) {return memberMapper.selectInfoByIdentification(memberIdentification);}

    //아이디로 랜덤키 찾기
    public String selectRandomKeyByIdentification(String memberIdentification) {return memberMapper.selectRandomKeyByIdentification(memberIdentification);}

    //아이디로 이메일 찾기
    public String selectEmailByIdentification(String memberIdentification) {return memberMapper.selectEmailByIdentification(memberIdentification);}

    // 랜덤키 수정
    public void updateRandomKey(String memberRandomKey, String memberEmail){memberMapper.updateRandomKey(memberRandomKey, memberEmail);};
}
