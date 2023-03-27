package com.neighbor.domain.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class MessageDTO {
    private Long target_id;
    private String message_content;
    private String message_register_date;
    private String board_title;
    private String target_nickname;
    private String target_profile_uuid;
    private String target_profile_path;
}
