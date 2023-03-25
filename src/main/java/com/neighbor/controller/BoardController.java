package com.neighbor.controller;

import com.neighbor.domain.vo.BoardFileVO;
import com.neighbor.domain.vo.BoardVO;
import com.neighbor.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/boards/*")
public class BoardController {

    private final BoardService boardService;

    @GetMapping("write")
    public String goWrite(){
        return "kjp/product-registration";
    }

    @GetMapping("main")
    public String goMain(){
        return "kdh/main";
    }

    @PostMapping("write")
    public RedirectView writeBoard(BoardVO boardVO,  List<BoardFileVO> files){

        return new RedirectView( "main");
    }




}
