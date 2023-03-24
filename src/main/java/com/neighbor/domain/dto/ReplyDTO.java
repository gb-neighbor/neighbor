package com.neighbor.domain.dto;


import lombok.Data;
import org.springframework.stereotype.Component;

/*ReplyVO와 MemberVO를 조인*/

@Component
@Data
public class ReplyDTO {
    private Long replyId;
    private String replyTitle;
    private String replyContent;
    private Integer replyScore;
    private String replyRegisterDate;
    private String replyUpdateDate;
    private Long memberId;
    private Long boardId;
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
}
