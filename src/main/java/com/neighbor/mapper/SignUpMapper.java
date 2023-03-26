package com.neighbor.mapper;

import com.neighbor.domain.vo.MemberVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SignUpMapper {
    // 회원가입
    public void insert(MemberVO memberVO);
}
