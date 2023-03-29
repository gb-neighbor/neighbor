package com.neighbor.domain.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class AskAdminDetailDTO {
    private Long askAdminId;
    private String askAdminTitle;
    private String askAdminContent;
    private String askAdminRegisterDate;
    private Long memberId;
    private Boolean askAdminStatus;
    private Long askAdminAnswerId;
    private String askAdminAnswerContent;
    private String askAdminAnswerRegisterDate;
}
