package com.neighbor.controller;

import com.neighbor.service.MainService;
import com.neighbor.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/main/*")
@RestController
@Slf4j
@RequiredArgsConstructor
public class MainRestController {

    private final MainService mainService;

    @GetMapping("region-recent")
    public void changeRecentByRegion(Integer region ,Model model){
        model.addAttribute("regionSelectRecentLists", mainService.findAllByRegion(region));
    }
}
