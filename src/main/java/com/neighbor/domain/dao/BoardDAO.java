package com.neighbor.domain.dao;

import com.neighbor.domain.dto.BoardDTO;
import com.neighbor.domain.dto.CriteraForBoard;
import com.neighbor.domain.dto.Criteria;
import com.neighbor.domain.vo.BoardVO;
import com.neighbor.domain.vo.MemberVO;
import com.neighbor.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardDAO {

    private final BoardMapper boardMapper;

    //    게시글 추가
    public void save(BoardDTO boardDTO){boardMapper.insert(boardDTO);}

    //    마지막에 추가된 BoardId 가져오기
    public Long getBoardId(){
        return boardMapper.getLastBoardId();
    }

    //    맴버, 보드, 보드파일의 정보 가져오기
    public List<BoardDTO> findAllBoardMember(CriteraForBoard criteraForBoard, Long memberId, String keyword, String gugun){return boardMapper.selectAllBoardMember(criteraForBoard, memberId, keyword,gugun);}

    // 보드아이디로 모든 정보 가져오기
    public BoardDTO findAllBoardMemberByBoard(CriteraForBoard criteraForBoard, Long boardId){
        return boardMapper.selectAllBoardMemberByBoard(criteraForBoard, boardId);
    }

    //페이징 처리를 위해 모든 보드 갯수 가져오기
    public int getTotal(){return boardMapper.getTotal();}
    
    // 맴버아이디로 보드의 모든 정보 조회
    public List<BoardVO> getBoardByMemberId(Long memberId){return boardMapper.selectOne(memberId);}

    //판매종료 버튼
    public void sell(Long boardId) {
        boardMapper.updateBoardStatus(boardId);
    }

    //    관리자 페이지 판매목록 게시글 전체 조회
    public List<BoardDTO> findAll(Criteria criteria, String keyword){return boardMapper.selectAll(criteria, keyword);}

    //    관리자 페이지 판매 대기중 게시글
    public List<BoardDTO> findWait(Criteria criteria, String keyword) {
        return boardMapper.selectWait(criteria, keyword);
    }

    //    관리자 페이지 판매 목록 전체 글 수
    public int countAll(String keyword) {
        return boardMapper.countAll(keyword);
    }

    //    관리자 페이지 판매 목록 판매 대기중인 글 수
    public int count(String keyword) {
        return boardMapper.count(keyword);
    }

    //    대시보드 게시글 부분 조회
    public List<BoardDTO> findAllBy(){return boardMapper.selectAllBy();}

    //    게시판 목록 삭제
    public void delete(Long boardId) {
        boardMapper.delete(boardId);
    }

    //    게시글 제목
    public String findBoardTitle(Long boardId){return boardMapper.selectBoardTitle(boardId);}

    //    보드아이디로 맴버정보 가져오기
    public MemberVO findMemberInfoByBoardId(Long boardId){return boardMapper.getMemberInfoByBoardId(boardId);}
}
