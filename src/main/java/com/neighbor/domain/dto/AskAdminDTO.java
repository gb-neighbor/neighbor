package com.neighbor.domain.dto;

import lombok.Data;
import org.springframework.stereotype.Component;


/*MemberVO와 AskAdminVO를 합친 것이다.*/
@Component
@Data
public class AskAdminDTO {
    private Long askAdminId;
    private String askAdminTitle;
    private String askAdminContent;
    private String askAdminRegisterDate;
    private Long memberId;
    private Boolean askAdminStatus;
    private String memberName;
    private String memberIdentification;
    private String memberPassword;
    private String memberNickname;
    private String memberBirth;
    private String memberPhone;
    private Integer memberRegion;
    private String memberEmail;
    private String memberHiSentence;
    private String memberProfileSystemName;
    private String memberProfileOriginalName;
    private String memberProfilePath;
}
