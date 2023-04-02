package com.neighbor.mapper;

import com.neighbor.domain.dto.MainRecentDTO;
import com.neighbor.domain.dto.MainReplyAvgHighDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MainMapper {

    // 메인 최근 부분
    public MainRecentDTO selectAllByRegion(Integer memberRegion);

    // 메인 최근 부분 지역이 전체일 경우
    public MainRecentDTO selectAllByRegionAll();

    // 메인 별점인기순 부분
    public MainReplyAvgHighDTO selectDataForReplyHighAvgList();

}
