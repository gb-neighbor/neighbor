package com.neighbor.domain.dao;

import com.neighbor.domain.dto.AskAdminDTO;
import com.neighbor.domain.dto.AskAdminDetailDTO;
import com.neighbor.domain.dto.Criteria;
import com.neighbor.domain.vo.AskAdminAnswerVO;
import com.neighbor.domain.vo.AskAdminVO;
import com.neighbor.mapper.AskAdminMapper;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AskAdminDAO {

    private final AskAdminMapper askAdminMapper;

//   전체 조회
    public List<AskAdminDTO> findAll(Criteria criteria, String keyword){return askAdminMapper.selectAll(criteria, keyword);}

//    문의사항 답변 대기중 조회
    public List<AskAdminDTO> findAskAnswer(Criteria criteria, String keyword) {
        return askAdminMapper.selectWaitAnswer(criteria, keyword);
    }

//    문의사항 전체 질문 수
    public int countAll(String keyword) {
        return askAdminMapper.countAll(keyword);
    }

//    문의사항 답변 대기 중 질문 수
    public int count(String keyword) {
        return askAdminMapper.count(keyword);
    }

//    관리자페이지 문의 목록 삭제
    public void delete(Long askAdminId){
        askAdminMapper.delete(askAdminId);
    }

    //  대시보드 문의사항 부분 조회
    public List<AskAdminDTO> findAllBy(){return askAdminMapper.selectAllBy();}


    //     질문 작성
    public void register(AskAdminVO askAdminVO){
        askAdminMapper.insert(askAdminVO);
    }

    //     내가 작성한 문의 목록
    public List<AskAdminVO> selectListByMemberId(Long memberId, Criteria criteria,String keyword) {
        return askAdminMapper.selectListByMemberId(memberId,criteria,keyword);
    }

    public int countByMemberId(Long memberId, String keyword) {
        return askAdminMapper.countByMemberId(memberId, keyword);
    }

    //    문의 상세페이지
    public AskAdminVO findByAskAdminId(Long askAdminId) {
        return askAdminMapper.selectDetail(askAdminId);
    }

    //    문의 삭제
    public void deleteDetail(Long askAdminId){
        askAdminMapper.deleteDetail(askAdminId);
    }

    //  문의 답글
    public AskAdminAnswerVO findByAskAdminAnswer(Long askAdminId) {return askAdminMapper.selectByAnswer(askAdminId);}

}
