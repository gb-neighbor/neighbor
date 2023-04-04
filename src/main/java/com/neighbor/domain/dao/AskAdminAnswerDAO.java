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


    //   답변해주면 ask 상태 바꿔주기
    public void update(Long askAdminId) {
        askAdminAnswerMapper.update(askAdminId);
    }

}
