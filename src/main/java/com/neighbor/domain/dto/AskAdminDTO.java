package com.neighbor.domain.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;


/*MemberVO와 AskAdminVO를 합친 것이다.*/
@Component
@Data
public class AskAdminDTO {
    private Long askAdminId;
    private String askAdminTitle;
    private String askAdminContent;
    private String askAdminRegisterDate;
    private String askAdminAnswerRegisterDate;
    private Long memberId;
    private Long askAdminAnswerId;
    private Integer askAdminStatus;
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
    private int askTotal;

    // ajax를 위한 리스트 생성
    private List<AskAdminDTO> askAdminDTOS;
    private PageDTO pageDTO;

    private String askStatus;
    private String[] askStatusArr = {"답변대기", "답변완료"};
    public void statusChange(Integer askAdminStatus) {this.askStatus = this.askStatusArr[askAdminStatus];}
}
