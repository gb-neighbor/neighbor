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
    public List<AskAdminDTO> findAll(){return askAdminMapper.selectAll();}

//    관리자페이지 문의 목록 삭제
    public void delete(Long askAdminId){
        askAdminMapper.delete(askAdminId);
    }
}
