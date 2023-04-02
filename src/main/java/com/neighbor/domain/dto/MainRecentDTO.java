package com.neighbor.domain.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class MainRecentDTO {

    private Long memberId;
    private String memberNickname;
    private String memberProfileSystemName;
    private String memberProfileOriginalName;
    private String memberProfileUuid;
    private String memberProfilePath;
    private Long boardId;
    private String boardTitle;
    private Long boardFileId;
    private String boardFileOriginalName;
    private String boardFileUuid;
    private String boardFilePath;
    private String boardFileSize;
}
