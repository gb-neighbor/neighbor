package com.neighbor.domain.dao;

import com.neighbor.domain.vo.PurchaseVO;
import com.neighbor.mapper.PurchaseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PurchaseDAO {

    private final PurchaseMapper purchaseMapper;

    public PurchaseVO findPurchaseVO(Long boardId, Long customerId){return purchaseMapper.selectOne(boardId, customerId);}

}
