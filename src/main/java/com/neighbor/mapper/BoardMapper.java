package com.neighbor.mapper;

import com.neighbor.domain.dto.BoardDTO;
import com.neighbor.domain.dto.Critera2;
import com.neighbor.domain.dto.Criteria;
import com.neighbor.domain.vo.BoardVO;
import com.neighbor.domain.vo.MemberVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BoardMapper {
    //   게시판 글 추가
    public void insert(BoardDTO boardDTO);

    //  맴버아이디로  게시글 전체 조회
    public List<BoardDTO> selectAllBoardMember(@Param("cri2") Critera2 critera2, @Param("memberId") Long memberId);

    // 보드아이디로 게시글과 맴버 정보 전체 조회
    public BoardDTO selectAllBoardMemberByBoard(@Param("cri2") Critera2 critera2, @Param("boardId") Long boardId);

    // 페이징 처리를 위해서 모든 정보 가져오기
    public int getTotal ();

    //    마지막에 추가된 boardId 가져오기
    public Long getLastBoardId();

    //    보드아이디로 맴버정보 가져오기
    public MemberVO getMemberInfoByBoardId(Long boardId);
    
    // 맴버 아이디로 보드정보 가져오기
    public List<BoardVO> selectOne(Long memberId);

    //     지역으로 게시글 조회
    public BoardDTO select(Integer boardRegion);
    
    //  관리자 페이지 판매 게시글 관리 판매 대기중목록 불러오기
    public List<BoardDTO> selectWait(@Param("cri") Criteria criteria, @Param("keyword") String keyword);

    //    관리자 페이지 판매 게시글 관리 전체 글 수
    public int countAll(@Param("keyword") String keyword);

    //    관리자 페이지 판매 게시글 판매 대기중 글 수
    public int count(@Param("keyword") String keyword);

    //    관리자 페이지 게시글 전체 조회
    public List<BoardDTO> selectAll(@Param("cri") Criteria criteria, @Param("keyword") String keyword);

    //  대시보드  게시글 부분 조회
    public List<BoardDTO> selectAllBy();

    //    게시글 삭제
    public void delete(Long boardId);

    //    보드 제목
    public String selectBoardTitle(Long boardId);
}
