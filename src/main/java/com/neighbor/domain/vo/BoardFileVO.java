package com.neighbor.domain.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class BoardFileVO {

    private Long boardFileId;
    private String boardFileDetailName;
    private String boardFileDetailUuid;
    private String boardFileDetailPath;
    private String boardFileDetailSize;
    private Long boardId;

}
