package com.neighbor.service;

import com.neighbor.domain.dao.SignUpDAO;
import com.neighbor.domain.vo.MemberVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignUpService {
    private final SignUpDAO signUpDAO;

    // 회원가입
    public void signUp(MemberVO memberVO){
        signUpDAO.save(memberVO);
    }
}
