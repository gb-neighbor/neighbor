package com.neighbor.mapper;

import com.neighbor.domain.vo.BoardFileVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardFileMapper {
//    파일 추가
    public void insert(BoardFileVO boardFileVO);
//  파일 전체 조회
    public List<BoardFileVO> selectAll(Long boardId);
//    파일 삭제
    public void delete(Long boardId);
//    전일 등록된 파일 조회
    public List<BoardFileVO> selectYesterday();
}
