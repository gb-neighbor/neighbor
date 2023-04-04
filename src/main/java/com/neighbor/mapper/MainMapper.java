package com.neighbor.mapper;

import com.neighbor.domain.dto.MainRecentDTO;
import com.neighbor.domain.dto.MainReplyAvgHighDTO;
import com.neighbor.domain.vo.BoardFileVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MainMapper {

    // 메인 최근 부분
    public List<MainRecentDTO> selectAllByRegion(Integer memberRegion);

    // 메인 최근 부분 지역이 전체일 경우
    public List<MainRecentDTO> selectAllByRegionAll();

    // 메인 별점인기순 부분
    public List<MainReplyAvgHighDTO> selectDataForReplyHighAvgList();

    // 메인 랜덤 추천
    public List<BoardFileVO> selectRandom();
}
