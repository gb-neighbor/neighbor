package com.neighbor.domain.dao;

import com.neighbor.domain.dto.CriteraForBoard;
import com.neighbor.domain.dto.Criteria;
import com.neighbor.domain.dto.ReplyDTO;
import com.neighbor.domain.vo.ReplyVO;
import com.neighbor.mapper.ReplyMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReplyDAO {

    private final ReplyMapper replyMapper;

//  후기관리 삭제
    public void delete(Long memberId){replyMapper.delete(memberId);}

//    후기 가져오기
    public List<ReplyVO> getAllReplyByBoardId(Long boardId){
        return replyMapper.selectAllByBoardId(boardId);
    }

//  대시보드 후기글 부분 조회
    public List<ReplyDTO> findAllBy() {
    return replyMapper.selectAllBy();
}

// 후기글 전체 글 수
    public int countAll(String keyword) {
        return replyMapper.countAll(keyword);
    }

    //    후기목록 검색용 글 조회
    public List<ReplyDTO> findAll(Criteria criteria, String keyword) {
        return replyMapper.selectAll(criteria, keyword);
    }

//    후기 작성
    public void save(ReplyDTO replyDTO){
        replyMapper.insert(replyDTO);
    }

//    방금 쓴 replyid가져오기
    public Long getCurrentSeq(){
        return replyMapper.getCurrentSeq();
    }

//    reply다 가져오기
    public List<ReplyDTO> selectAllReplyMemberByBoardId(Long boardId, CriteraForBoard criteraForBoard){
        return replyMapper.selectAllReplyMemberByBoardId(boardId, criteraForBoard);
    }

//    reply총 갯수
    public int getCountAll(){
        return replyMapper.getCountAll();
    }
    
//    댓글 제한을 위한 쿼리
    public Integer getCountReplyMember(Long boardId, Long memberId){
        return replyMapper.countReplyForRestrict(boardId, memberId);
    };
}
