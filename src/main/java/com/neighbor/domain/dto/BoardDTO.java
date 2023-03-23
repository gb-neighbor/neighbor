package com.neighbor.domain.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class BoardDTO {
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
    private String memberProfileSystemName;
    private String memberProfileOriginalName;
    private String memberProfilePath;
    private Long boardId;
    private String boardTitle;
    private String boardContent;
    private String boardRegisterDate;
    private String boardUpdateDate;
    private Boolean boardStatus;
    private Integer boardRegion;
}
