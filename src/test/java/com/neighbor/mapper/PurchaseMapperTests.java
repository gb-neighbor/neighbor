package com.neighbor.mapper;

import com.neighbor.domain.vo.PurchaseVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class PurchaseMapperTests {

    @Autowired
    PurchaseMapper purchaseMapper;

    @Test
    public void purchaseDetailTest(){
        if(purchaseMapper.selectOne(1L, 1L) == null){log.info("NUll!!!!");}
        log.info(String.valueOf(purchaseMapper.selectOne(1L, 1L)));
    }
}
