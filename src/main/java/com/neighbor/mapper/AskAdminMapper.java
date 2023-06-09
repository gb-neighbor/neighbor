package com.neighbor.mapper;

import com.neighbor.domain.dto.AskAdminDTO;
import com.neighbor.domain.dto.AskAdminDetailDTO;
import com.neighbor.domain.dto.Criteria;
import com.neighbor.domain.vo.AskAdminAnswerVO;
import com.neighbor.domain.vo.AskAdminVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AskAdminMapper {

//    전체 조회
    public List<AskAdminDTO> selectAll(@Param("cri") Criteria criteria, @Param("keyword") String keyword);

//    답변 대기 중 리스트
    public List<AskAdminDTO> selectWaitAnswer(@Param("cri") Criteria criteria, @Param("keyword") String keyword);

//    삭제
    public void delete(Long askAdminId);

// 전체 질문 수
    public int countAll(@Param("keyword") String keyword);

//    답변 대기 중인 질문 수
    public int count(@Param("keyword") String keyword);

//    대시보드 문의사항 부분조회
    public List<AskAdminDTO> selectAllBy();


    //    작성
    public void insert(AskAdminVO askAdminVO);

    //    내가 작성한 문의 조회
    public List<AskAdminVO> selectListByMemberId(Long memberId, Criteria criteria, String keyword);

    public Integer countByMemberId(Long memberId, String keyword);

    //  문의 상세페이지
    public AskAdminVO selectDetail(Long askAdminId);

    public AskAdminAnswerVO selectByAnswer(Long askAdminId);

    //   문의 삭제
    public void deleteDetail(Long askAdminId);

}
