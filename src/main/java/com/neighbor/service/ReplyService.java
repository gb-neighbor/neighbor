package com.neighbor.service;

import com.neighbor.domain.dao.ReplyDAO;
import com.neighbor.domain.dto.ReplyDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReplyService {

    private final ReplyDAO replyDAO;

    //    후기글 전체 조회
    public List<ReplyDTO> getList() {
        return replyDAO.findAll();
    }

    //  후기관리 삭제
    public void delete(Long memberId){
        replyDAO.delete(memberId);
    }

    //  대시보드 후기글 부분 조회
    public List<ReplyDTO> getListBy() {
        return replyDAO.findAllBy();
    }

}
