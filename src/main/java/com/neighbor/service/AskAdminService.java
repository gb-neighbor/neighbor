package com.neighbor.service;

import com.neighbor.domain.dao.AskAdminDAO;
import com.neighbor.domain.dto.AskAdminDTO;
import com.neighbor.domain.dto.AskAdminDetailDTO;
import com.neighbor.domain.dto.Criteria;
import com.neighbor.domain.vo.AskAdminAnswerVO;
import com.neighbor.domain.vo.AskAdminVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AskAdminService {

    private final AskAdminDAO askAdminDAO;


    //   전체 조회
    public List<AskAdminDTO> getListAll(Criteria criteria, String keyword){return askAdminDAO.findAll(criteria, keyword);}


//    문의사항 답변 대기 중인 질문 조회

    public List<AskAdminDTO> getList(Criteria criteria, String keyword){
        return askAdminDAO.findAskAnswer(criteria, keyword);
    }
//  문의사항 답변 대기 중인 질문 수

    public int getCount(String keyword) {
        return askAdminDAO.count(keyword);
    }

//    문의사항 전체 질문 수

    public int getCountAll(String keyword) {

        return askAdminDAO.countAll(keyword);
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
    public List<AskAdminVO> listByMemberIdWithPaging(Long memberId, Criteria criteria, String keyword) {
        return askAdminDAO.selectListByMemberId(memberId, criteria,keyword);
    }
    public int getCountByMemberId(Long memberId, String keyword) {
        return askAdminDAO.countByMemberId(memberId, keyword);
    }

    //    문의 상세 페이지
    public AskAdminVO detail(Long askAdminId){
        return askAdminDAO.findByAskAdminId(askAdminId);
    }

    //    문의 삭제
    public void deleteDetail(Long askAdminId){
        askAdminDAO.deleteDetail(askAdminId);
    }

    //    문의 답글
    public AskAdminAnswerVO answerDetail(Long askAdminId){return askAdminDAO.findByAskAdminAnswer(askAdminId);}

}
