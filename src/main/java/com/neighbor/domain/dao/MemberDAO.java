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
    
//    회원관리 멤버 총 수
    public Integer countAll() {
        return memberMapper.countAll();
    }


}
