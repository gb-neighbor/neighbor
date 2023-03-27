package com.neighbor.domain.dao;

import com.neighbor.domain.dto.ReplyDTO;
import com.neighbor.mapper.ReplyMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReplyDAO {

    private final ReplyMapper replyMapper;

//  전체 후기글 조회
    public List<ReplyDTO> findAll() {
        return replyMapper.selectAll();
    }

//  후기관리 삭제
    public void delete(Long memberId){replyMapper.delete(memberId);}

//  대시보드 후기글 부분 조회
    public List<ReplyDTO> findAllBy() {
    return replyMapper.selectAllBy();
}

// 후기글 전체 글 수
    public Integer countAll() {
        return replyMapper.countAll();
    }
}
