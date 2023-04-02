package com.neighbor.domain.dao;

import com.neighbor.domain.dto.MainRecentDTO;
import com.neighbor.domain.dto.MainReplyAvgHighDTO;
import com.neighbor.mapper.MainMapper;
import com.neighbor.mapper.MemberMapper;
import com.sun.tools.javac.Main;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MainDAO {
    private final MainMapper mainMapper;

    // 지역에 따른 최근 게시물 정보 조회
    public MainRecentDTO selectAllByRegion(Integer memberRegion){return mainMapper.selectAllByRegion(memberRegion);}

    // 메인 최근 부분 지역이 전체일 경우
    public MainRecentDTO selectAllByRegionAll() {return mainMapper.selectAllByRegionAll();}

    // 메인 별점인기순 부분
    public MainReplyAvgHighDTO selectDataForReplyHighAvgList() {return mainMapper.selectDataForReplyHighAvgList();}
}
