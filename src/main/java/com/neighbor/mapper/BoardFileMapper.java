package com.neighbor.mapper;

import com.neighbor.domain.dto.BoardDTO;
import com.neighbor.domain.vo.BoardFileVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardFileMapper {
//    메인 파일 추가
    public void insertMain(BoardDTO boardDTO);
//     디테일 파일 추가
    public void insertDetail(List<BoardFileVO> files);

//    마지막에 추가된 보드 아이디 가져오기
    public Long getCurrentSequenceForFile();

//  파일 전체 조회
    public List<BoardFileVO> selectAll(Long boardId);
//    파일 삭제
    public void delete(Long boardId);
//    전일 등록된 파일 조회
    public List<BoardFileVO> selectYesterday();
}
