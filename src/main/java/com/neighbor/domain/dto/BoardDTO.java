package com.neighbor.domain.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class BoardDTO {
    private Long boardId;
    private String boardTitle;
    private String boardContent;
    private String boardRegisterDate;
    private String boardUpdateDate;
    private Boolean boardStatus;
    private Long memberId;
    private Integer boardRegion;
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
    private Long boardFileId;
    private String boardFileOriginalName;
    private String boardFileUuid;
    private String boardFilePath;
    private String boardFileSize;
    private Integer countTotalBoards;
}
