package com.neighbor.mapper;

import com.neighbor.domain.vo.ReplyFileVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReplyFileMapper {
    /* 사진 추가 */
    public void insert(ReplyFileVO replyFileVO);
    /* reply아이디로 모든 파일 가져오기 */
    public List<ReplyFileVO> selectAllByReplyId(Long replyId);
}
