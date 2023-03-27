package com.neighbor.mapper;

import com.neighbor.domain.vo.PurchaseVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PurchaseMapper {

    public PurchaseVO selectOne(Long boardId, Long customerId);

}
