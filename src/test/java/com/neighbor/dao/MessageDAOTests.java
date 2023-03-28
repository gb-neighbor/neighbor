package com.neighbor.dao;

import com.neighbor.domain.dao.MessageDAO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class MessageDAOTests {

    @Autowired
    MessageDAO messageDAO;

    @Test
    public void messageListTest(){
    }

    @Test
    public void messageDetailTest(){
    }

}
