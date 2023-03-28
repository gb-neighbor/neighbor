package com.neighbor.domain.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class MessageRoomDTO {
    private Long messageRoomId;
    private Long targetId;
    private Long boardId;
}
