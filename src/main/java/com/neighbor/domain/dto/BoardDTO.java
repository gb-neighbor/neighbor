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

    private String boardRegionKo;
    private String[] boardRegionArr = {"전체", "강남구", "강동구", "강북구", "강서구", "관악구", "광진구", "구로구",
            "금천구", "노원구", "도봉구", "동대문구", "동작구", "마포구", "서대문구", "서초구",
            "성동구", "성북구", "송파구", "양천구", "영등포구", "용산구", "은평구", "종로구",
            "중구", "중랑구"};

    public void Change(Integer index){
        this.boardRegionKo = this.boardRegionArr[index];
    }
}
