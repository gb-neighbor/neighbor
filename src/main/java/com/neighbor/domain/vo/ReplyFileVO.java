package com.neighbor.domain.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class ReplyFileVO {
    private Long replyFileId;
    private String replyFileSystemName;
    private String replyFileOriginalName;
    private String filePath;
    private Long replyId;
}
