package com.neighbor.domain.dao;

import com.neighbor.domain.vo.ReplyFileVO;
import com.neighbor.mapper.ReplyFileMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReplyFileDAO {
    private final ReplyFileMapper replyFileMapper;

    public void save(ReplyFileVO replyFileVO){
        replyFileMapper.insert(replyFileVO);
    }

    public List<ReplyFileVO> getListByReplyId(Long replyId){
        return replyFileMapper.selectAllByReplyId(replyId);
    }
}
