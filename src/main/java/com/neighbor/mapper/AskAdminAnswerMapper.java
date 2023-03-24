package com.neighbor.mapper;

import com.neighbor.domain.dto.AskAdminAnswerDTO;
import com.neighbor.domain.vo.AskAdminAnswerVO;
import com.neighbor.domain.vo.AskAdminVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AskAdminAnswerMapper {

//    관리자 페이지 문의사항에 답변하기
    public void insertAskAdminAnswer(AskAdminAnswerVO askAdminAnswerVO);

//    관리자 페이지 문의목록 전체보기
    public List<AskAdminAnswerDTO> selectAll();
}
