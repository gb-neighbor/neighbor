package com.neighbor.domain.dto;


import com.neighbor.domain.vo.ReplyFileVO;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

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
    private String memberIdentification;
    private String memberPassword;
    private String memberName;
    private String memberNickname;
    private String memberBirth;
    private String memberPhone;
    private Integer memberRegion;
    private String memberEmail;
    private String memberHiSentence;
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
    private Integer replyTotal;
    private List<ReplyFileVO> files;

    private String replyRegionKo;
    private String[] replyRegionArr = {"전체", "강남구", "강동구", "강북구", "강서구", "관악구", "광진구", "구로구",
            "금천구", "노원구", "도봉구", "동대문구", "동작구", "마포구", "서대문구", "서초구",
            "성동구", "성북구", "송파구", "양천구", "영등포구", "용산구", "은평구", "종로구",
            "중구", "중랑구"};
//    ajax를 위한 리스트 생성
    private List<ReplyDTO> replyDTOS;
    private PageDTO pageDTO;

    public void change(Integer index){
        this.replyRegionKo = this.replyRegionArr[index];
    }

}
