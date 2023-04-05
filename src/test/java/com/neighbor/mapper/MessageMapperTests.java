package com.neighbor.mapper;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class MessageMapperTests {

    @Autowired
    MessageMapper messageMapper;

    @Test
    public void messageListTest(){
//        messageMapper.selectAll(1L).forEach(messageRoomDTO -> log.info(String.valueOf(messageRoomDTO)));
    }

    @Test
    public void messageDetailTest(){
//        messageMapper.selectMessage(1L).forEach(messageVO -> log.info(String.valueOf(messageVO)));
    }

    @Test
    public void messageCountTest(){

    }





}
