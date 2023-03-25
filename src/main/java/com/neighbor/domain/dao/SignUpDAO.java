package com.neighbor.domain.dao;


import com.neighbor.domain.vo.MemberVO;
import com.neighbor.mapper.MemberMapper;
import com.neighbor.mapper.SignUpMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SignUpDAO {
    private final SignUpMapper signUpMapper;

    //회원가입
    public void save(MemberVO memberVO){
        signUpMapper.insert(memberVO);
    }
}
