package com.neighbor.controller;

import com.neighbor.domain.dto.BoardDTO;
import com.neighbor.domain.vo.BoardFileVO;
import com.neighbor.domain.vo.BoardVO;
import com.neighbor.service.BoardFileService;
import com.neighbor.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/boards/*")
public class BoardController {

    private final BoardService boardService;
    private final BoardFileService boardFileService;

    @GetMapping("write")
    public String goWrite(Model model){
        return "kjp/product-registration";
    }

    @GetMapping("main")
    public String goMain(){
        return "main/main";
    }


    //    boardDTO 실패
//    boardVO랑 파일 List로 받아오는거 실패
    @PostMapping("save")
    public RedirectView writeBoard(BoardDTO boardDTO){
//        나중에 session에서 Id값 가져오기
        boardDTO.setMemberId(1L);
        boardService.write(boardDTO);
        boardFileService.upload(boardDTO);

        return new RedirectView("list");
    }

    @GetMapping("list")
    public void goList(){
        
    }




}