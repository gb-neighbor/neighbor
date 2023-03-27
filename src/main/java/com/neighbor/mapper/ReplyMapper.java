package com.neighbor.mapper;

import com.neighbor.domain.dto.ReplyDTO;
import com.neighbor.domain.vo.ReplyVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReplyMapper {

//    전체 조회
    public List<ReplyDTO> selectAll();

//    후기 삭제
    public void delete(Long userId);

//    대시보드 부분 조회
    public List<ReplyDTO> selectAllBy();

//    후기목록 전체 글 수
    public Integer countAll();
}
