package com.neighbor.dao;


import com.neighbor.domain.dao.ReplyDAO;
import com.neighbor.domain.dto.ReplyDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class ReplyDAOTests {

    @Autowired
    private ReplyDAO replyDAO;

    @Test
    public void dashBoardReplyListTest() {
        List<ReplyDTO> replyDTOList = replyDAO.list();
        log.info(String.valueOf(replyDTOList));
    }

}
