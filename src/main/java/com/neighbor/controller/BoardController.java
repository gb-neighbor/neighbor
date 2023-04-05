package com.neighbor.controller;

import com.neighbor.domain.dto.BoardDTO;
import com.neighbor.domain.dto.CriteraForBoard;
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

import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/board/*")
public class BoardController {

    private final BoardService boardService;
    private final BoardFileService boardFileService;
    private final ReplyService replyService;
    private final MemberService memberService;

    @GetMapping("write")
    public String goWrite(){

        return "board/product-registration";
    }


    @GetMapping("main")
    public String goMain(){
        return "main/main";
    }


    @PostMapping("save")
    public RedirectView writeBoard(BoardDTO boardDTO, HttpSession httpSession){
        Long memberId = (Long) httpSession.getAttribute("memberVO");
        boardDTO.setMemberId(memberId);

        boardDTO.setBoardId(boardService.write(boardDTO));
        if(boardDTO.getFileMainName() != null || boardDTO.getFiles() != null){
            boardFileService.upload(boardDTO);
        }
        log.info(String.valueOf(boardDTO));
        return new RedirectView("list/region");
    }

    @GetMapping("list/region")
    public String goList(Model model, CriteraForBoard criteraForBoard, @RequestParam(value = "keyword", required = false) String keyword, @RequestParam(value = "gugun",required = false)String gugun){
        criteraForBoard.setPage(1);
//      맴버와 보드를 합친 DTO를 가져옴
        List<BoardDTO> boardDTOList = boardService.getAllMemberBoard(criteraForBoard, null,keyword,gugun);
        Map<Long, List<ReplyVO>> replyVOMap = new HashMap<>();
        int avgScore = 0;
        int sum = 0;

        for(BoardDTO boardDTO:boardDTOList){
            boardDTO.change(boardDTO.getBoardRegion());
            boardDTO.setFiles(boardFileService.getAllFile(boardDTO.getBoardId()));

            // 게시물에 대한 댓글 가져오기
            List<ReplyVO> replyVOList = replyService.getListByBoardId(boardDTO.getBoardId());
            replyVOMap.put(boardDTO.getBoardId(), replyVOList);

            // 해당 게시물에 대한 댓글의 평균 점수 구하기

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

/* 리스트 무한스크롤 이벤트로 가져오기 */
@PostMapping("lists/regions")
@ResponseBody
public List<BoardDTO> getNextPage(@RequestBody CriteraForBoard criteraForBoard, @RequestParam(value = "keyword", required = false) String keyword, @RequestParam(value = "gugun",required = false)String gugun){
    log.info("==============================================================="+keyword);
    //      맴버와 보드를 합친 DTO를 가져옴
    List<BoardDTO> boardDTOList = boardService.getAllMemberBoard(criteraForBoard, null, keyword, gugun);
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
    log.info(String.valueOf(criteraForBoard));
    return boardDTOList;
}



    @GetMapping("list/member/{memberId}")
    public String goMember(@PathVariable("memberId") Long memberId, Model model, CriteraForBoard criteraForBoard) {
        criteraForBoard.setPage(1);
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
        if(size != 0){
            avgScore = totalScore/size;
        }
        model.addAttribute("memberVO", memberVO);
        model.addAttribute("boardVOList", boardVOList);
        model.addAttribute("replySize", size);
        model.addAttribute("avgScore", avgScore);
        model.addAttribute("boardSize", boardVOList.size());
        model.addAttribute("mainFile", mainFile);
        return "list/list-by-member";
    }

    @GetMapping("lists/members/{memberId}")
    @ResponseBody
    public List<BoardDTO> getALlBoard(CriteraForBoard criteraForBoard, @PathVariable("memberId") Long memberId, @RequestParam(value = "keyword", required = false) String keyword, @RequestParam(value = "gugun",required = false)String gugun){
        List<BoardDTO> boardDTOList = boardService.getAllMemberBoard(criteraForBoard, memberId,keyword,gugun);
        Map<Long, List<ReplyVO>> replyVOMap = new HashMap<>();
        int avgScore = 0;

        for(BoardDTO boardDTO:boardDTOList){
            boardDTO.change(boardDTO.getBoardRegion());
            BoardFileVO boardFileVO = boardFileService.getMainFile(boardDTO.getBoardId());

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
        log.info(String.valueOf(criteraForBoard));
        return boardDTOList;
    }

    @GetMapping("detail/{boardId}")
    public String goList(@PathVariable("boardId") Long boardId, CriteraForBoard criteraForBoard, Model model){
        criteraForBoard.setPage(1);
        BoardDTO boardDTO = boardService.getInfoForDetail(criteraForBoard, boardId);
        model.addAttribute(boardDTO);
        log.info(String.valueOf(boardDTO));
        log.info(String.valueOf(criteraForBoard));
        return "board/product-detail2";
    }


    @GetMapping("sell/{boardId}")
    @ResponseBody
    public void sell(@PathVariable Long boardId){
        boardService.sell(boardId);
    }



}