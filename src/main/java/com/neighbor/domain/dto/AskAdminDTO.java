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
    private String memberName;
    private String memberIdentification;
    private String memberPassword;
    private String memberNickname;
    private String birth;
    private String phone;
    private Integer region;
    private String memberEmail;
    private String hiSentence;
    private String memberProfileSystemName;
    private String memberProfileOriginalName;
    private String memberProfilePath;
}
