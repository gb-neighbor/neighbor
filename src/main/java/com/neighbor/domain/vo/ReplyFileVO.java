package com.neighbor.domain.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class ReplyFileVO {
    private Long replyFileId;
    private String replyFileUuid;
    private String replyFileOriginalName;
    private String replyFilePath;
    private Long replyId;
}
