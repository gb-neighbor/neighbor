package com.neighbor.service;

import com.neighbor.domain.dao.AskAdminDAO;
import com.neighbor.domain.dto.AskAdminDTO;
import com.neighbor.domain.dto.Criteria;
import com.neighbor.domain.vo.AskAdminVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AskAdminService {

    private final AskAdminDAO askAdminDAO;


    //   전체 조회
    public List<AskAdminDTO> getListAll(Criteria criteria){return askAdminDAO.findAll(criteria);}


//    문의사항 답변 대기 중인 질문 조회

    public List<AskAdminDTO> getList(Criteria criteria){
        return askAdminDAO.findAskAnswer();
    }
//  문의사항 답변 대기 중인 질문 수

    public Integer getCount() {
        return askAdminDAO.count();
    }
//    문의사항 전체 질문 수

    public Integer getCountAll() {

        return askAdminDAO.countAll();
    }

    //    관리자페이지 문의 목록 삭제
    public void delete(Long askAdminId){
        askAdminDAO.delete(askAdminId);
    }

    //   대시보드 문의사항 부분조회
    public List<AskAdminDTO> getListBy(){return askAdminDAO.findAllBy();}


    //    문의 작성
    public void write(AskAdminVO askAdminVO){
        askAdminDAO.register(askAdminVO);
    }

    //    내가 작성한 글 리스트
    public List<AskAdminVO> listOne(Long memberId){
        return askAdminDAO.findById(memberId);
    }


}
