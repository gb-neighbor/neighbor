package com.neighbor.service;

import com.neighbor.domain.vo.AskAdminVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class AskAdminServiceTests {

    @Autowired
    AskAdminService askAdminService;

    @Test
    public void countAllTest() {
        askAdminService.getCountAll();
    }

    @Test
    public void countTest() {
        askAdminService.getCount();
    }

//    @Test
//    public void  AskAdminGetListTest(){
//        askAdminService.getList(criteria);
//    }

//    @Test
//    public void AskAdminListAllTest() {
//        askAdminService.getListAll();
//    }

    @Test
    public void askAdminDeleteTest() {
        AskAdminVO askAdminVO = new AskAdminVO();
        askAdminVO.setAskAdminId(3L);
        askAdminService.delete(askAdminVO.getAskAdminId());
    }

}
