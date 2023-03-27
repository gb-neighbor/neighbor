package com.neighbor.domain.dao;

import com.neighbor.domain.dto.AskAdminAnswerDTO;
import com.neighbor.mapper.AskAdminAnswerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AskAdminAnswerDAO {

    private final AskAdminAnswerMapper askAdminAnswerMapper;

    /*관리자페이지에서 문의사항에 관리자가 답변하기*/
    public void insert(AskAdminAnswerDTO askAdminAnswerDTO) {
        askAdminAnswerMapper.insertAskAdminAnswer(askAdminAnswerDTO);
    }

}
