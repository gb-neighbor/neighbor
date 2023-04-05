package com.neighbor.controller;

import com.neighbor.domain.dto.MainRecentDTO;
import com.neighbor.domain.vo.BoardVO;
import com.neighbor.service.MainService;
import com.neighbor.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/main/*")
@RestController
@Slf4j
@RequiredArgsConstructor
public class MainRestController {

    private final MainService mainService;

    @GetMapping("region-recent")
    public List<MainRecentDTO> changeRecentByRegion(@RequestParam Integer memberRegion , Model model){
        List<MainRecentDTO> regionSelectRecentLists = mainService.findAllByRegion(memberRegion);
        return regionSelectRecentLists;
    }
}
