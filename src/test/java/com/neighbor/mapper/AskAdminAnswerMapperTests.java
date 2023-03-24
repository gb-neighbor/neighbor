package com.neighbor.mapper;

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
        AskAdminAnswerVO askAdminAnswerVO = new AskAdminAnswerVO();
        askAdminAnswerVO.setAskAdminAnswerContent("아니");
        askAdminAnswerVO.setAskAdminAnswerId(1L);
        askAdminAnswerVO.setAskAdminId(1L);
        askAdminAnswerVO.setMemberId(2L);
        askAdminAnswerMapper.insertAskAdminAnswer(askAdminAnswerVO);
    }


    /*관리자 페이지 문의 글 목록 전체 조회 테스트*/
    @Test
    public void askAdminAnswerSelectAll() {
        log.info(askAdminAnswerMapper.selectAll().toString());
    }

}
