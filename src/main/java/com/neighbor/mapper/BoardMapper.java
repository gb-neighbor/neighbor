package com.neighbor.mapper;

import com.neighbor.domain.dto.BoardDTO;
import com.neighbor.domain.vo.BoardVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
//   게시판 글 추가
    public void insert(BoardVO boardVO);

//    마지막에 추가된 boardId 가져오기
    public Long getLastBoardId();

//    게시글 조회
    public BoardDTO select(Integer boardRegion);
//    게시글 전체 조회
    public List<BoardDTO> selectAll();

//    게시글 삭제
    public void delete(Long boardId);

}
