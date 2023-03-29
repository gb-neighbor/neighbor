package com.neighbor.mapper;

import com.neighbor.domain.dto.Criteria;
import com.neighbor.domain.dto.ReplyDTO;
import com.neighbor.domain.vo.ReplyVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReplyMapper {


//    후기 삭제
    public void delete(Long userId);

//    보드아이디로 후기 가져오기
    public List<ReplyVO> selectAllByBoardId(Long boardId);

//    대시보드 부분 조회
    public List<ReplyDTO> selectAllBy();

//    후기목록 전체 글 수
    public Integer countAll();

//    후기목록 전체조회 검색포함
    public List<ReplyDTO> selectAll(Criteria criteria, @Param("keyword") String keyword);
}
