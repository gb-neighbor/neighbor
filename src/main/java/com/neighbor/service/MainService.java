package com.neighbor.service;

import com.neighbor.domain.dao.MainDAO;
import com.neighbor.domain.dao.MemberDAO;
import com.neighbor.domain.dto.MainRecentDTO;
import com.neighbor.domain.dto.MainReplyAvgHighDTO;
import com.neighbor.domain.vo.BoardFileVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MainService {

    private  final MainDAO mainDAO;

    // 지역에 따른 최근 게시물 정보 조회
    public List<MainRecentDTO> findAllByRegion(Integer memberRegion){return mainDAO.selectAllByRegion(memberRegion);}

    // 메인 최근 부분 지역이 전체일 경우
    public List<MainRecentDTO> findAllByRegionAll() {return mainDAO.selectAllByRegionAll();}

    // 메인 별점인기순 부분
    public List<MainReplyAvgHighDTO> findDataForReplyHighAvgList() {return mainDAO.selectDataForReplyHighAvgList();}

    // 메인 랜덤 추천 부분
    public List<BoardFileVO> findRandom() {return mainDAO.selectRandom();}
}
