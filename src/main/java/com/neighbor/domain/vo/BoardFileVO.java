package com.neighbor.domain.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class BoardFileVO {
    private Long boardFileId;
    private String boardFileOriginalName;
    private String boardFileSystemName;
    private String boardFilePath;
    private Long boardId;

}
