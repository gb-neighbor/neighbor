package com.neighbor.domain.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class MessageVO {
    private Long messageId;
    private String messageContent;
    private String messageRegisterDate;
    private Long boardId;
    private Long messageSenderId;
    private Long messageGetterId;
}
