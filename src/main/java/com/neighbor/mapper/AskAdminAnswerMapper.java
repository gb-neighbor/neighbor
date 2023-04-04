package com.neighbor.mapper;

import com.neighbor.domain.dto.AskAdminAnswerDTO;
import com.neighbor.domain.dto.AskAdminDTO;
import com.neighbor.domain.vo.AskAdminAnswerVO;
import com.neighbor.domain.vo.AskAdminVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AskAdminAnswerMapper {

//    관리자 페이지 문의사항에 답변하기
    public void insertAskAdminAnswer(AskAdminAnswerDTO askAdminAnswerDTO);

//   답변해주면 ask 상태 바꿔주기
    public void update(Long askAdminId);
}
