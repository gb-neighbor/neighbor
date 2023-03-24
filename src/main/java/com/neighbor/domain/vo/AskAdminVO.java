package com.neighbor.domain.vo;


import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class AskAdminVO {
    private Long askAdminId;
    private String askAdminTitle;
    private String askAdminContent;
    private String askAdminRegisterDate;
    private Long memberId;
    private Boolean askAdminStatus;
}
