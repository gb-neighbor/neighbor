package com.neighbor.controller;

import com.neighbor.domain.vo.BoardFileVO;
import com.neighbor.domain.vo.BoardVO;
import com.neighbor.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public RedirectView writeBoard(BoardVO boardVO, Model model, @RequestBody List<BoardFileVO> /*js에서는 이름을 files로 지어야함*/ files){
        model.addAttribute(boardVO.getBoardContent());
        model.addAttribute(boardVO.getBoardRegion());
        model.addAttribute(boardVO.getBoardTitle());
        /*맴버 아이디는 세션에 저장된 값을 사용해야함*/
//        model.addAttribute(boardVO.getMemberId());
        log.info(files.toString());



        return new RedirectView( "main");
    }




}
