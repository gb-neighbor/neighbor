package com.neighbor.mapper;

import com.neighbor.domain.dto.AskAdminDTO;
import com.neighbor.domain.dto.Criteria;
import com.neighbor.domain.vo.AskAdminVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AskAdminMapper {

//    전체 조회
    public List<AskAdminDTO> selectAll(Criteria criteria);

//    답변 대기 중 리스트
    public List<AskAdminDTO> selectWaitAnswer();

//    삭제
    public void delete(Long askAdminId);

// 전체 질문 수
    public Integer countAll();

//    답변 대기 중인 질문 수
    public Integer count();

//    대시보드 문의사항 부분조회
    public List<AskAdminDTO> selectAllBy();

}
