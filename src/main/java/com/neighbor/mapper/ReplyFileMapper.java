package com.neighbor.mapper;

import com.neighbor.domain.vo.ReplyFileVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReplyFileMapper {
    public void insert(ReplyFileVO replyFileVO);
}
