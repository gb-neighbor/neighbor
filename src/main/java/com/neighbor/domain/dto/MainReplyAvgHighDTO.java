package com.neighbor.domain.dto;


import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class MainReplyAvgHighDTO {

    private Long memberId;
    private String memberNickname;
    private Integer memberRegion;
    private String memberProfileSystemName;
    private String memberProfileOriginalName;
    private String memberProfileUuid;
    private String memberProfilePath;
    private Long boardId;
    private String boardTitle;
    private Integer boardPrice;
    private Long replyId;
    private Integer avgReplyScore;
}
