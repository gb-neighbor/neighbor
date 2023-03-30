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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.lang.reflect.Array;
import java.util.*;

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
        boardDTO.setBoardId(boardService.write(boardDTO));
        boardFileService.upload(boardDTO);

        return new RedirectView("list/region");
    }

    @GetMapping("list/region")
    public String goList(Model model){

//      맴버와 보드를 합친 DTO를 가져옴
        List<BoardDTO> boardDTOList = boardService.getAllMemberBoard();
        Map<Long, List<ReplyVO>> replyVOMap = new HashMap<>();
        int avgScore = 0;

        for(BoardDTO boardDTO:boardDTOList){
            boardDTO.change(boardDTO.getBoardRegion());
            boardDTO.setFiles(boardFileService.getAllFile(boardDTO.getBoardId()));

            // 게시물에 대한 댓글 가져오기
            List<ReplyVO> replyVOList = replyService.getListByBoardId(boardDTO.getBoardId());
            replyVOMap.put(boardDTO.getBoardId(), replyVOList);

            // 해당 게시물에 대한 댓글의 평균 점수 구하기
            int sum = 0;
            for (ReplyVO reply : replyVOList) {
                sum += reply.getReplyScore();
            }
            int avg = replyVOList.size() > 0 ? sum / replyVOList.size() : 0;

            // 평균 점수 더하기
            boardDTO.setAvgScore(avg);
        }


        log.info(String.valueOf(boardDTOList));
        model.addAttribute("boardDTOList", boardDTOList);
        return "list/list-by-region";
    }

    @GetMapping("list/member/{memberId}")
    public String goMember(@PathVariable("memberId") Long memberId, Model model) {
        MemberVO memberVO = memberService.getOneMemberInfo(memberId);
        List<BoardVO> boardVOList = boardService.getBoardInfo(memberId);
        BoardFileVO boardFileVO = new BoardFileVO();
        Map<Long, List<ReplyVO>> replyVOMap = new HashMap<>();
        List<BoardFileVO> mainFile = new ArrayList<>();
        memberVO.change(memberVO.getMemberRegion());
        int totalScore = 0;
        int avgScore = 0;
        int size = 0;

        for (BoardVO boardVO : boardVOList) {
            Long boardId = boardVO.getBoardId();
            List<ReplyVO> replyVOList = replyService.getListByBoardId(boardId);
            boardFileVO = boardFileService.getMainFile(boardId);
            mainFile.add(boardFileVO);
            replyVOMap.put(boardId, replyVOList);
            List<ReplyVO> replies = replyVOMap.get(boardId); // boardId에 해당하는 댓글 리스트 가져오기
            for (ReplyVO reply : replies) {
                totalScore += reply.getReplyScore(); // 각 댓글의 replyScore를 더해주기
            }
        }

        size = replyVOMap.values().stream().mapToInt(Collection::size).sum();
        avgScore = totalScore/size;

        log.info(String.valueOf(String.valueOf(replyVOMap)));

        model.addAttribute("memberVO", memberVO);
        model.addAttribute("boardVOList", boardVOList);
        model.addAttribute("replySize", size);
        model.addAttribute("avgScore", avgScore);
        model.addAttribute("boardSize", boardVOList.size());
        model.addAttribute("mainFile", mainFile);
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