package com.neighbor.mapper;

import com.neighbor.domain.dto.AskAdminAnswerDTO;
import com.neighbor.domain.vo.AskAdminAnswerVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class AskAdminAnswerMapperTests {

    @Autowired
    private AskAdminAnswerMapper askAdminAnswerMapper;


    /*관리자 페이지 문의 글에 답변 했을 때 답변 글 insert 테스트*/
    @Test
    public void askAdminAnswerInsertTest(){
        AskAdminAnswerDTO askAdminAnswerDTO = new AskAdminAnswerDTO();
        askAdminAnswerDTO.setAskAdminAnswerContent("아니");
        askAdminAnswerDTO.setAskAdminAnswerId(1L);
        askAdminAnswerDTO.setAskAdminId(1L);
        askAdminAnswerDTO.setMemberId(2L);
        askAdminAnswerMapper.insertAskAdminAnswer(askAdminAnswerDTO);
    }


    /*관리자 페이지 문의 글 목록 전체 조회 테스트*/
/*    @Test
    public void askAdminAnswerSelectAll() {
        log.info(askAdminAnswerMapper.selectAll().toString());
    }*/

}
