package com.neighbor.domain.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class ReplyVO {
    private Long replyId;
    private String replyContent;
    private Integer replyScore;
    private String replyRegisterDate;
    private String replyUpdateDate;
    private Long memberId;
    private Long boardId;
}
