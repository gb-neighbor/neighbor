package com.neighbor.domain.dao;

import com.neighbor.domain.dto.AskAdminDTO;
import com.neighbor.mapper.AskAdminMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AskAdminDAO {

    private final AskAdminMapper askAdminMapper;

//    대쉬보드 전체 조회
    public List<AskAdminDTO> list(){return askAdminMapper.selectAll();}

}
