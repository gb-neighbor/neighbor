package com.neighbor.domain.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class MemberVO {
    private Long memberId;
    private String memberIdentification;
    private String memberPassword;
    private String memberName;
    private String memberNickname;
    private String birth;
    private String phone;
    private Integer region;
    private String memberEmail;
    private String hiSentence;
    private String memberProfileSize;
    private String memberProfileOriginalName;
    private String memberProfileUuid;
    private String memberProfilePath;
}
