package com.neighbor.mapper;

import com.neighbor.domain.vo.ReplyFileVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReplyFileMapper {
    public void insert(ReplyFileVO replyFileVO);
}
