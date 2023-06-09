package com.neighbor.domain.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class AskAdminAnswerDTO {
    private Long memberId;
    private String memberIdentification;
    private String memberPassword;
    private String memberName;
    private String memberNickname;
    private String memberBirth;
    private String memberPhone;
    private Integer memberRegion;
    private String memberEmail;
    private String memberHiSentence;
    private String memberProfileSize;
    private String memberProfileOriginalName;
    private String memberProfileUuid;
    private String memberProfilePath;
    private Long askAdminAnswerId;
    private String askAdminAnswerContent;
    private String askAdminAnswerRegisterDate;
    private Long askAdminId;
    private String askAdminTitle;
    private String askAdminContent;
    private String askAdminRegisterDate;
    private Boolean askAdminStatus;
}
