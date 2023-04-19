package com.neighbor.controller;


import com.neighbor.domain.dto.MainReplyAvgHighDTO;
import com.neighbor.service.MainService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MainController {

    private final MainService mainService;

    //메인으로 이동
    @GetMapping("main")
    public String goMain(Model model){
        model.addAttribute("regionAllRecentLists", mainService.findAllByRegionAll());
        model.addAttribute("replyHighAvgLists", mainService.findDataForReplyHighAvgList());
        model.addAttribute("randomRecommendLists", mainService.findRandom());
        log.info(String.valueOf(model.getAttribute("regionAllRecentLists")));
        log.info(String.valueOf(model.getAttribute("replyHighAvgLists")));
        log.info(String.valueOf(model.getAttribute("randomRecommendLists")));
        return "main/main";
    }

}
