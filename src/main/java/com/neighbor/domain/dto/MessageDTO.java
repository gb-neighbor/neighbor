package com.neighbor.domain.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class MessageDTO {
    private Long messageRoomId;
    private String latestRegisterDate;
    private String boardTitle;
    private Long boardId;
    private Long targetId;
    private String targetNickname;
    private String targetProfileUuid;
    private String targetProfilePath;
    private String targetProfileOriginalName;
}
