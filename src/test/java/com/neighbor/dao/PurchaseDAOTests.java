package com.neighbor.dao;

import com.neighbor.domain.dao.PurchaseDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class PurchaseDAOTests {

    @Autowired
    PurchaseDAO purchaseDAO;

    public void purchaseDAOTest(){
        log.info(String.valueOf(purchaseDAO.findPurchaseVO(1L, 1L)));
    }

}
