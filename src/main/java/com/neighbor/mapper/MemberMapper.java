package com.neighbor.mapper;

import com.neighbor.domain.vo.MemberVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper {
//    전체 조회
    public List<MemberVO> selectAll();


}

