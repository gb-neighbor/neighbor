package com.neighbor.service;

import com.neighbor.domain.dao.BoardDAO;
import com.neighbor.domain.dao.ReplyDAO;
import com.neighbor.domain.dao.ReplyFileDAO;
import com.neighbor.domain.dto.CriteraForBoard;
import com.neighbor.domain.dto.Criteria;
import com.neighbor.domain.dto.ReplyDTO;
import com.neighbor.domain.vo.ReplyFileVO;
import com.neighbor.domain.vo.ReplyVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReplyService {

    private final ReplyDAO replyDAO;
    private final BoardDAO boardDAO;
    private final ReplyFileDAO replyFileDAO;

    //  후기관리 삭제
    public void remove(List<String> replyIds){
        replyIds.stream().map(replyId -> Long.parseLong(replyId)).forEach(replyDAO::delete);
    }

    public List<ReplyVO> getListByBoardId(Long memberId){
        List<ReplyVO> boardVOList = replyDAO.getAllReplyByBoardId(memberId);
        return boardVOList;
    }

    //  대시보드 후기글 부분 조회
    public List<ReplyDTO> getListBy() {
        return replyDAO.findAllBy();
    }

//    후기글 전체글 수
    public int getCountAll(String keyword) {
        return replyDAO.countAll(keyword);
    }

    //    후기목록 전체 조회 검색포함
    public List<ReplyDTO> getList(Criteria criteria, String keyword) {
        return replyDAO.findAll(criteria, keyword);
    }
    
//    댓글 추가
    public void saveReply(ReplyDTO replyDTO) {
        replyDAO.save(replyDTO);
        Long replyId = replyDAO.getCurrentSeq();

        // ReplyFileVO 객체의 replyId 업데이트
        List<ReplyFileVO> files = replyDTO.getFiles();
        if (files != null) {
            for (ReplyFileVO replyFileVO : replyDTO.getFiles()) {
                replyFileVO.setReplyId(replyId);
            }
            // 업데이트된 파일 리스트를 ReplyDTO에 설정
            replyDTO.setFiles(files);
        }
        for (ReplyFileVO replyFileVO: replyDTO.getFiles()) {
            replyFileDAO.save(replyFileVO);
        }

    }

    public List<ReplyDTO> getAllReplyMemberByBoardId(Long boardId, CriteraForBoard criteraForBoard){
        List<ReplyDTO> replyDTOS =  replyDAO.selectAllReplyMemberByBoardId(boardId, criteraForBoard);
        criteraForBoard.setTotal(getReplyTotal());
        for (ReplyDTO replyDTO : replyDTOS) {
            criteraForBoard.create(replyDAO.getCountAll());
            replyDTO.setFiles(replyFileDAO.getListByReplyId(replyDTO.getReplyId()));
        }
        return replyDTOS;
    }

    public Integer getReplyTotal(){
        return replyDAO.getCountAll();
    }

    public Boolean getCountReplyMember(Long boardId, Long memberId) {
        Integer count = replyDAO.getCountReplyMember(boardId, memberId);
        Boolean result = false;
        if(count < 1){
            result  = true;
        }
        return result;
    }

}
