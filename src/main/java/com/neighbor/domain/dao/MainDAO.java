package com.neighbor.domain.dao;

import com.neighbor.domain.dto.MainRecentDTO;
import com.neighbor.domain.dto.MainReplyAvgHighDTO;
import com.neighbor.domain.vo.BoardFileVO;
import com.neighbor.mapper.MainMapper;
import com.neighbor.mapper.MemberMapper;
import com.sun.tools.javac.Main;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MainDAO {
    private final MainMapper mainMapper;

    // 지역에 따른 최근 게시물 정보 조회
    public List<MainRecentDTO> selectAllByRegion(Integer memberRegion){return mainMapper.selectAllByRegion(memberRegion);}

    // 메인 최근 부분 지역이 전체일 경우
    public List<MainRecentDTO> selectAllByRegionAll() {return mainMapper.selectAllByRegionAll();}

    // 메인 별점인기순 부분
    public List<MainReplyAvgHighDTO> selectDataForReplyHighAvgList() {return mainMapper.selectDataForReplyHighAvgList();}

    // 메인 랜덤추천 부분
    public List<BoardFileVO> selectRandom() {return mainMapper.selectRandom();}
}
