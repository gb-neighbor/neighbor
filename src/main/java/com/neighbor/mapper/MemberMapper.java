package com.neighbor.mapper;

import com.neighbor.domain.dto.Criteria;
import com.neighbor.domain.dto.MemberDTO;
import com.neighbor.domain.vo.MemberVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MemberMapper {
//    관리자 페이지 회원 관리 전체 조회
    public List<MemberDTO> selectAll(@Param("cri") Criteria criteria, @Param("keyword") String keyword);
    //    일부 조회
    public List<MemberDTO> selectAllBy();
//    맴버 아이디로 맴버 정보 조회
    public MemberVO selectOne(Long memberId);
//    멤버 삭제
    public void delete(Long memberId);

//    멤버 총 수
    public int countAll(@Param("keyword") String keyword);

    // 회원가입
    public void insert(MemberVO memberVO);

    // 아이디 중복
    public Long selectByIdentification(String memberIdentification);

    // 닉네임 중복
    public Long selectByNickname(String memberNickname);

    // 이메일 중복
    public Long selectByEmail(String memberEmail);

    // 휴대폰 중복
    public Long selectByPhone(String memberPhone);

    // 로그인
    public Long selectById(String memberIdentification, String memberPassword);

    // 네이버 로그인
    public Long compareEmail(String memberEmail);

    // 이메일로 회원정보 조회
    public MemberVO selectInfoByEmail(String memberEmail);

    // 아이디로 회원정보 조회
    public MemberVO selectInfoByIdentification(String memberIdentification);

    // 아이디로 랜덤키 찾기
    public String selectRandomKeyByIdentification(String memberIdentification);

    // 아이디로 랜덤키 찾기
    public String selectEmailByIdentification(String memberIdentification);

    // 아이디 찾기
    public String selectMyIdentification(String memberEmail);

    // 비밀번호 수정
    public void updateMyPassword(String memberIdentification, String memberPassword );

    // 랜덤키 수정
    public void updateRandomKey(String memberRandomKey, String memberEmail);

}

