package com.neighbor.dao;


import com.neighbor.domain.dao.AskAdminDAO;
import com.neighbor.domain.dto.AskAdminDTO;
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

    @Test
    public void askAdminListTest() {
        List<AskAdminDTO> askAdminDTOList = askAdminDAO.list();
        log.info(String.valueOf(askAdminDTOList));
    }

}
