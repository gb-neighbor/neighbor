package com.neighbor.domain.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class MailVO {
    private String address;
    private String title;
    private String message;
}
