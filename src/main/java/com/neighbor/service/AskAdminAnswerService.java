package com.neighbor.service;

import com.neighbor.domain.dao.AskAdminAnswerDAO;
import com.neighbor.domain.dao.AskAdminDAO;
import com.neighbor.domain.dto.AskAdminAnswerDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AskAdminAnswerService {
    private final AskAdminAnswerDAO askAdminAnswerDAO;

//    문의사항에 대한 관리자의 답변 추가
    public void insertAnswer(AskAdminAnswerDTO askAdminAnswerDTO) {
        askAdminAnswerDAO.insert(askAdminAnswerDTO);
    }

//    답변 추가로 인한 status를 update한다.
    public void updateStatus(Long askAdminId) {
        askAdminAnswerDAO.update(askAdminId);
    }

}
