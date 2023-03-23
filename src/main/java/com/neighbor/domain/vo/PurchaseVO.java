package com.neighbor.domain.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class PurchaseVO {
    private Long purchaseId;
    private Long boardId;
    private Long customerId;
}
