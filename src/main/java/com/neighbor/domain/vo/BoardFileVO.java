package com.neighbor.domain.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class BoardFileVO {

    private Long boardFileId;
    private String fileDetailName;
    private String fileDetailUuid;
    private String fileDetailPath;
    private String fileDetailSize;
    private Long boardId;

}
