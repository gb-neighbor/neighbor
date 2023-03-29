package com.neighbor.controller;

import com.neighbor.domain.dto.BoardDTO;
import com.neighbor.domain.vo.BoardFileVO;
import com.neighbor.domain.vo.BoardVO;
import com.neighbor.domain.vo.MemberVO;
import com.neighbor.domain.vo.ReplyVO;
import com.neighbor.service.BoardFileService;
import com.neighbor.service.BoardService;
import com.neighbor.service.MemberService;
import com.neighbor.service.ReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/boards/*")
public class BoardController {

    private final BoardService boardService;
    private final BoardFileService boardFileService;
    private final ReplyService replyService;
    private final MemberService memberService;

    @GetMapping("write")
    public String goWrite(Model model){

        return "kjp/product-registration";
    }


    @GetMapping("main")
    public String goMain(){
        return "main/main";
    }


    @PostMapping("save")
    public RedirectView writeBoard(BoardDTO boardDTO){
//        나중에 session에서 Id값 가져오기
        boardDTO.setMemberId(1L);
        boardService.write(boardDTO);
        if(boardDTO.getFileMainName() != null){
            boardFileService.upload(boardDTO);
        }
        return new RedirectView("list/region");
    }

    @GetMapping("list/region")
    public String goList(Model model){

//      맴버와 보드를 합친 DTO를 가져옴
        List<BoardDTO> boardDTOList = boardService.getAllMemberBoard();
        for(BoardDTO boardDTO:boardDTOList){
            boardDTO.change(boardDTO.getBoardRegion());
            boardDTO.setFiles(boardFileService.getAllFile(boardDTO.getBoardId()));
        }

        model.addAttribute("boardDTOList", boardDTOList);
        log.info(String.valueOf(boardDTOList));
//      보드 파일의 모든 정보 조회
        return "list/list-by-region";
    }

    @GetMapping("list/member/{memberId}")
    public String goMember(@PathVariable("memberId") Long memberId, Model model) {
        MemberVO memberVOList = memberService.getOneMemberInfo(memberId);
        List<BoardVO> boardVOList = boardService.getBoardInfo(memberId);
        BoardFileVO boardFileVO = new BoardFileVO();
        Map<Long, BoardFileVO> mainFile = new HashMap<>();
        Map<Long, List<ReplyVO>> replyVOListMap = new HashMap<>();

        for (BoardVO boardVO : boardVOList) {
            Long boardId = boardVO.getBoardId();
            List<ReplyVO> replyVOList = replyService.getListByBoardId(boardId);
            boardFileVO = boardFileService.getMainFile(boardId);
            mainFile.put(boardId, boardFileVO);
            replyVOListMap.put(boardId, replyVOList);
        }


        model.addAttribute("memberVO", memberVOList);
        model.addAttribute("boardVOList", boardVOList);
        model.addAttribute("replyVOListMap", replyVOListMap);
        model.addAttribute("replySize", replyVOListMap.size());
        model.addAttribute("boardSize", boardVOList.size());
        model.addAttribute("mainFile", mainFile);
        log.info(String.valueOf(memberVOList));
        log.info(String.valueOf(replyVOListMap));
        log.info(String.valueOf(boardVOList));
        log.info(String.valueOf(mainFile));
        return "list/list-by-member";
    }

    @GetMapping("detail")
    public String goDetail(){
        return "kjp/product-detail";
    }

    @GetMapping("detail/{boardId}")
    public String goList(@PathVariable("boardId") Long boardId){
        return "";
    }



}