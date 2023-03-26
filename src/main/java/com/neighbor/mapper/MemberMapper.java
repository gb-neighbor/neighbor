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

}

