package com.neighbor.mapper;

import com.neighbor.domain.dto.MemberDTO;
import com.neighbor.domain.vo.MemberVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper {
//    전체 조회
    public List<MemberDTO> selectAll();
    //    일부 조회
    public List<MemberDTO> selectAllBy();
//    멤버 삭제
    public void delete(Long userId);

//    멤버 총 수
    public Integer countAll();

    // 회원가입
    public void insert(MemberVO memberVO);

    // 아이디 중복
    public Long selectByIdentification(String memberIdentification);

    // 닉네임 중복
    public Long selectByNickname(String memberNickname);

    // 이메일 중복
    public Long selectByEmail(String memberEmail);

    // 로그인
    public Long selectById(String memberIdentification, String memberPassword);

    // 아이디 찾기
    public String selectMyIdentification(String memberEmail);

    // 비밀번호 수정
    public void updateMyPassword(String memberIdentification, String memberPassword );

}

