package com.neighbor.service;

import com.neighbor.domain.dao.ReplyDAO;
import com.neighbor.domain.dto.Criteria;
import com.neighbor.domain.dto.ReplyDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReplyService {

    private final ReplyDAO replyDAO;

    //    후기글 전체 조회
    public List<ReplyDTO> getList(Criteria criteria) {
        return replyDAO.findAll(criteria);
    }

    //  후기관리 삭제
    public void delete(Long memberId){
        replyDAO.delete(memberId);
    }

    //  대시보드 후기글 부분 조회
    public List<ReplyDTO> getListBy() {
        return replyDAO.findAllBy();
    }

//    후기글 전체글 수
    public Integer getCountAll() {
        return replyDAO.countAll();
    }

    //    후기목록 검색용 글 조회
    public List<ReplyDTO> getSearchList(Criteria criteria, ReplyDTO replyDTO) {
        return replyDAO.findSearch(criteria, replyDTO);
    }
}
