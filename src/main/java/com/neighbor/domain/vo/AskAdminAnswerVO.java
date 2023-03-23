package com.neighbor.domain.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class AskAdminAnswerVO {
    private Long askAdminAnswerId;
    private String askAdminAnswerContent;
    private String askAdminAnswerRegisterDate;
    private Long askAdminId;
    private Long memberId;
}
