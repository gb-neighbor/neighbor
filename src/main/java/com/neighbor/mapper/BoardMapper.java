package com.neighbor.mapper;

import com.neighbor.domain.dto.BoardDTO;
import com.neighbor.domain.vo.BoardVO;
import com.neighbor.domain.vo.MemberVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
//   게시판 글 추가
    public void insert(BoardDTO boardDTO);

//    게시글 전체 조회
    public List<BoardDTO> selectAllBoardMember();

//    마지막에 추가된 boardId 가져오기
    public Long getLastBoardId();
    
//    보드아이디로 맴버정보 가져오기
    public MemberVO getMemberInfoByBoardId(Long boardId);

//     지역으로 게시글 조회
    public BoardDTO select(Integer boardRegion);

//  관리자 페이지 판매 게시글 관리 판매 대기중목록 불러오기
    public List<BoardDTO> selectWait();

//    관리자 페이지 판매 게시글 관리 전체 글 수
    public Integer countAll();

//    관리자 페이지 판매 게시글 판매 대기중 글 수
    public Integer count();

//    관리자 페이지 게시글 전체 조회
    public List<BoardDTO> selectAll();

//  대시보드  게시글 부분 조회
    public List<BoardDTO> selectAllBy();

//    게시글 삭제
    public void delete(Long boardId);

//    보드 제목
    public String selectBoardTitle(Long boardId);
}
