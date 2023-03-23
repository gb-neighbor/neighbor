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

//    대쉬보드 게시글 관리
    public List<ReplyDTO> list() {
        return replyMapper.selectAll();
    }


}
