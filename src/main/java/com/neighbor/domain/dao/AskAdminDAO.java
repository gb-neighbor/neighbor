package com.neighbor.domain.dao;

import com.neighbor.domain.dto.AskAdminDTO;
import com.neighbor.domain.dto.Criteria;
import com.neighbor.domain.vo.AskAdminVO;
import com.neighbor.mapper.AskAdminMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AskAdminDAO {

    private final AskAdminMapper askAdminMapper;

//   전체 조회
    public List<AskAdminDTO> findAll(Criteria criteria){return askAdminMapper.selectAll(criteria);}

//    문의사항 답변 대기중 조회
    public List<AskAdminDTO> findAskAnswer() {
        return askAdminMapper.selectWaitAnswer();
    }

//    문의사항 전체 질문 수
    public Integer countAll() {
        return askAdminMapper.countAll();
    }

//    문의사항 답변 대기 중 질문 수
    public Integer count() {
        return askAdminMapper.count();
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
    public List<AskAdminVO> findById(Long memberId) {
        return askAdminMapper.selectOne(memberId);
    }

    //    문의 상세페이지
    public AskAdminVO findByAskAdminId(Long askAdminId) {
        return askAdminMapper.selectDetail(askAdminId);
    }

    //    문의 삭제
    public void deleteDetail(Long askAdminId){
        askAdminMapper.deleteDetail(askAdminId);
    }

}
