package com.neighbor.domain.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class MessageRoomVO {
    private Long messageRoomId;
    private Long sellerId;
    private Long customerId;
    private Long boardId;
}
