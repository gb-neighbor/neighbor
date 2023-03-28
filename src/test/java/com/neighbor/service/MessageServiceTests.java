package com.neighbor.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class MessageServiceTests {

    @Autowired
    MessageService messageService;

    @Test
    public void testGettingMessage(){
        messageService.showMessage(messageService.getMessageRoomId(1L, 1L)).forEach(messageVO -> log.info(String.valueOf(messageVO)));
    }

}
