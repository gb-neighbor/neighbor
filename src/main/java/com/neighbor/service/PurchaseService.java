package com.neighbor.service;

import com.neighbor.domain.dao.PurchaseDAO;
import com.neighbor.domain.vo.PurchaseVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PurchaseService {

    private final PurchaseDAO purchaseDAO;

    public PurchaseVO getPurchaseVO(Long boardId, Long customerId){return purchaseDAO.findPurchaseVO(boardId, customerId);}

}
