package com.neighbor.dao;


import com.neighbor.domain.dao.AskAdminDAO;
import com.neighbor.domain.dto.AskAdminDTO;
import com.neighbor.domain.vo.AskAdminVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class AskAdminDAOTests {

    @Autowired
    private AskAdminDAO askAdminDAO;


    /*관리자페이지 대시보드 문의목록 전체조회*/
    @Test
    public void askAdminListTest() {
        List<AskAdminDTO> askAdminDTOList = askAdminDAO.findAll();
        log.info(String.valueOf(askAdminDTOList));
    }

    /*관리자 페이지 문의 목록 페이지 삭제*/
    @Test
    public void askAdminDeleteTest() {
        AskAdminVO askAdminVO = new AskAdminVO();
        askAdminVO.setAskAdminId(4L);
        askAdminDAO.delete(askAdminVO.getAskAdminId());
    }

}
