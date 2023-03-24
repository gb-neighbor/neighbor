package com.neighbor.mapper;

import com.neighbor.domain.vo.AskAdminVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class AskAdminTests {

    @Autowired
    private AskAdminMapper askAdminMapper;


    @Test
    public void selectAllTest() {
        log.info(askAdminMapper.selectAll().toString());
    }

    @Test
    public void deleteAskTest() {
        AskAdminVO askAdminVO = new AskAdminVO();
        askAdminVO.setAskAdminId(4L);
        askAdminMapper.delete(askAdminVO.getAskAdminId());
    }
}
