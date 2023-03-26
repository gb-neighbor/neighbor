package com.neighbor.service;

import com.neighbor.domain.dao.MemberDAO;
import com.neighbor.domain.dto.MemberDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    private  final MemberDAO memberDAO;

    //    대쉬보드 전체조회
    public List<MemberDTO> getList(){
        return memberDAO.findAll();
    }

    //    대쉬보드 일부조회
    public List<MemberDTO> getListBy(){
        return memberDAO.findAllBy();
    }

    //   회원관리 멤버삭제
    public void delete(Long memberId) {
        memberDAO.delete(memberId);
    }


}
