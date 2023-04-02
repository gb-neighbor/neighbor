package com.neighbor.service;

import com.neighbor.domain.dao.BoardDAO;
import com.neighbor.domain.dao.ReplyDAO;
import com.neighbor.domain.dto.Criteria;
import com.neighbor.domain.dto.ReplyDTO;
import com.neighbor.domain.vo.BoardVO;
import com.neighbor.domain.vo.ReplyVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReplyService {

    private final ReplyDAO replyDAO;
    private final BoardDAO boardDAO;

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

    public void saveReply(ReplyVO replyVO){
        replyDAO.save(replyVO);
    }
}
