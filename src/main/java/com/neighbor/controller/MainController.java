package com.neighbor.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/main/*")
@RequiredArgsConstructor
@Slf4j
public class MainController {

    //메인으로 이동
    @GetMapping("main")
    public String goMain(){
        return "/main/main";
    }

}
