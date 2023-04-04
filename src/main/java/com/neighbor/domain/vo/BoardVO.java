package com.neighbor.domain.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class BoardVO {
    private Long boardId;
    private String boardTitle;
    private String boardContent;
    private String boardRegisterDate;
    private String boardUpdateDate;
    private Integer boardStatus;
    private Integer boardPrice;
    private Long memberId;
    private Integer boardRegion;
    private Long sellerId;


}
