package com.neighbor.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board-file/*")
@RequiredArgsConstructor
public class BoardFileController {


    @GetMapping("register")
    public String goRegister(){
        return "kjp/product-registration";
    }

}
