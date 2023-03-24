package com.neighbor.mapper;

import com.neighbor.domain.dto.AskAdminDTO;
import com.neighbor.domain.vo.AskAdminVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AskAdminMapper {

//    전체 조회
    public List<AskAdminDTO> selectAll();

}
