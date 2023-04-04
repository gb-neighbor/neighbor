package com.neighbor.controller;


import com.neighbor.domain.dto.*;
import com.neighbor.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/admin/*")
public class AdminController {

    private final MemberService memberService;
    private final AskAdminService askAdminService;
    private final AskAdminAnswerService askAdminAnswerService;
    private final BoardService boardService;
    private final ReplyService replyService;
//
//    @GetMapping("form")
//    public void form(Model model, int number) {
//        model.addAttribute("number",number);
//    }
//

    /*AskAdmin 시작*/

    //   문의목록 문의사항 삭제
//    @DeleteMapping("ask-admin-delete")
//    public void askAdminDelete(@PathVariable Long askAdminId) {
//        askAdminService.delete(askAdminId);
//    }


    // 문의목록 문의사항 답변대기중 조회
//    @GetMapping("ask-admin-wait-list")
//    public String askAdminShowList(Criteria criteria, Model model) {
//        if(criteria.getPage() == 0){
//            criteria.create(1,6);
//        }
//
//        model.addAttribute("Asks", askAdminService.getListAll(criteria.create(criteria.getPage(), criteria.getAmount()))); // 업데이트된 Criteria 객체 전달
//        model.addAttribute("pagination", new PageDTO().createPageDTO(criteria, askAdminService.getCountAll()));
//
//        return "admin/manage-qna";
//    }
//
//
//    //    문의목록 총 질문 수
//    @GetMapping("ask-admin-countAll")
//    public Integer getAskAdminTotal() {
//        return askAdminService.getCountAll();
//    }
//
//    //    문의목록 답변 대기 중인 질문 수
//    @GetMapping("ask-admin-count")
//    public Integer getWaitAnswerTotal() {
//        return askAdminService.getCount();
//    }

    /*AskAdmin 끝*/

    /*member 시작*/

    /*처음 클릭했을때 화면으로 가는 일반 컨트롤러*/
    @GetMapping("member/list")
    public String memberShowList() {
        return "admin/manage-user";
    }

    /* 후기작성 ajax로 리스트를 불러오는 컨트롤러 */

    @GetMapping("member/page/keyword")
    @ResponseBody
    public MemberDTO memberShowList(@RequestParam(value = "page") int page, Criteria criteria, @RequestParam(value="keyword",required = false) String keyword) {
        criteria = criteria.create(page, 6);
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setMemberTotal(memberService.getCountAll(keyword));
        memberDTO.setMemberDTOS(memberService.getList(criteria,keyword));
        memberDTO.setPageDTO(new PageDTO().createPageDTO(criteria, memberService.getCountAll(keyword)));

        for (MemberDTO dto : memberDTO.getMemberDTOS()) {
            dto.change(dto.getMemberRegion());
        }

        return memberDTO;
    }

    //  회원관리 삭제
    @DeleteMapping("member/delete")
    @ResponseBody
    public void memberDelete(@RequestParam("checkedMemberId") String memberId){
        Long result = Long.parseLong(memberId);
        memberService.delete(result);
    }


    /*member 끝*/

    /*reply 시작*/

    /*처음 클릭했을때 화면으로 가는 일반 컨트롤러*/
    @GetMapping("reply/list")
    public String replyShowList() {
        return "admin/manage-reply";
    }

    /* 후기작성 ajax로 리스트를 불러오는 컨트롤러 */

    @GetMapping("reply/page/keyword")
    @ResponseBody
    public ReplyDTO replyShowList(@RequestParam(value = "page") int page, Criteria criteria, @RequestParam(value="keyword",required = false) String keyword) {
        criteria = criteria.create(page, 6);
        ReplyDTO replyDTO = new ReplyDTO();
        replyDTO.setReplyTotal(replyService.getCountAll(keyword));
        replyDTO.setReplyDTOS(replyService.getList(criteria,keyword));
        replyDTO.setPageDTO(new PageDTO().createPageDTO(criteria, replyService.getCountAll(keyword)));

        for (ReplyDTO dto : replyDTO.getReplyDTOS()) {
            dto.change(dto.getBoardRegion());

        }

        return replyDTO;
    }

    //  후기관리 삭제
    @DeleteMapping("reply/delete")
    @ResponseBody
    public void replyDelete(@RequestParam("checkedIds[]") List<String> checkIds){
        replyService.remove(checkIds);
    }

//    /*reply 끝*/

    /*board 시작*/

    /*처음 클릭했을때 화면으로 가는 일반 컨트롤러*/
    @GetMapping("board/list")
    public String boardShowList() {
        return "admin/manage-board";
    }

    /* 후기작성 ajax로 리스트를 불러오는 컨트롤러 */

    @GetMapping("board/page/keyword")
    @ResponseBody
    public BoardDTO boardShowList(@RequestParam(value = "page") int page, Criteria criteria, @RequestParam(value="keyword",required = false) String keyword) {
        criteria = criteria.create(page, 6);
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setBoardTotal(boardService.getCountAll(keyword));
        boardDTO.setBoardDTOS(boardService.getList(criteria,keyword));
        boardDTO.setPageDTO(new PageDTO().createPageDTO(criteria, boardService.getCountAll(keyword)));
        for (BoardDTO dto : boardDTO.getBoardDTOS()) {
            dto.change(dto.getBoardRegion());
            dto.saleChange(dto.getBoardStatus());
        }

        return boardDTO;
    }

//    판매대기중 보여주기
    @GetMapping("board/page/keyword/status")
    @ResponseBody
    public BoardDTO boardShowListStatus(@RequestParam(value = "page") int page, Criteria criteria, @RequestParam(value="keyword",required = false) String keyword) {
        log.info("페이지번호" + page);
        criteria = criteria.create(page, 6);
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setBoardTotal(boardService.getCount(keyword));
        boardDTO.setBoardDTOS(boardService.getWaitList(criteria,keyword));
        boardDTO.setPageDTO(new PageDTO().createPageDTO(criteria, boardService.getCount(keyword)));
        for (BoardDTO dto : boardDTO.getBoardDTOS()) {
            dto.change(dto.getBoardRegion());
            dto.saleChange(dto.getBoardStatus());
        }
        return boardDTO;
    }

    //  게시판관리 삭제
    @DeleteMapping("board/delete")
    @ResponseBody
    public void boardDelete(@RequestParam("checkedId") String boardId){
        Long result = Long.parseLong(boardId);
        boardService.delete(result);
    }


    /*board 끝*/

    /*ask-admin 시작*/
    
    
    @GetMapping("ask/list")
    public String askShowList() {
        return "admin/manage-qna";
    }

    /* 문의사항 ajax로 리스트를 불러오는 컨트롤러 */

    @GetMapping("ask/page/keyword")
    @ResponseBody
    public AskAdminDTO askShowList(@RequestParam(value = "page") int page, Criteria criteria, @RequestParam(value="keyword",required = false) String keyword) {
        criteria = criteria.create(page, 6);
        AskAdminDTO askAdminDTO = new AskAdminDTO();
        askAdminDTO.setAskTotal(askAdminService.getCountAll(keyword));
        askAdminDTO.setAskAdminDTOS(askAdminService.getListAll(criteria,keyword));
        log.info(askAdminDTO.getAskAdminDTOS().toString());
        askAdminDTO.setPageDTO(new PageDTO().createPageDTO(criteria, askAdminService.getCountAll(keyword)));
        for (AskAdminDTO dto : askAdminDTO.getAskAdminDTOS()) {
            dto.statusChange(dto.getAskAdminStatus());
        }

        return askAdminDTO;
    }

    //  답변대기중 보여주기
    @GetMapping("ask/page/keyword/status")
    @ResponseBody
    public AskAdminDTO askShowListStatus(@RequestParam(value = "page") int page, Criteria criteria, @RequestParam(value="keyword",required = false) String keyword) {
        criteria = criteria.create(page, 6);
        AskAdminDTO askAdminDTO = new AskAdminDTO();
        askAdminDTO.setAskTotal(askAdminService.getCount(keyword));
        askAdminDTO.setAskAdminDTOS(askAdminService.getList(criteria,keyword));
        askAdminDTO.setPageDTO(new PageDTO().createPageDTO(criteria, askAdminService.getCount(keyword)));
        for (AskAdminDTO dto : askAdminDTO.getAskAdminDTOS()) {
            dto.statusChange(dto.getAskAdminStatus());
        }

        return askAdminDTO;
    }

    //  게시판관리 삭제
    @DeleteMapping("ask/delete")
    @ResponseBody
    public void askDelete(@RequestParam("checkedId") String askAdminId){
        Long result = Long.parseLong(askAdminId);
        askAdminService.delete(result);
    }

    //    대시보드 전체조회
    @GetMapping("dash-board-listAll")
    public String dashBoardShowList(Model model){
        List<BoardDTO> boardDTOS = new ArrayList<>();
        List<MemberDTO> memberDTOS = new ArrayList<>();

        memberDTOS = memberService.getListBy();
        for(MemberDTO memberDTO:memberDTOS){
            memberDTO.change(memberDTO.getMemberRegion());
        };
        model.addAttribute("members", memberDTOS);

        boardDTOS = boardService.getListBy();
        for(BoardDTO boardDTO:boardDTOS){
            boardDTO.change(boardDTO.getBoardRegion());
            boardDTO.saleChange(boardDTO.getBoardStatus());
        };
        model.addAttribute("boards", boardDTOS);

        model.addAttribute("asks", askAdminService.getListBy());
        model.addAttribute("replys", replyService.getListBy());
        return "admin/dash-board";
    }

    /*ask-admin 끝*/

    /*ask-admin-answer 시작*/
    
//    관리자가 답변할 시 답변을 insert 해주고 답변여부를 답변완료로 바꾼다.
    @PostMapping("ask/answer")
    @ResponseBody
    public AskAdminAnswerDTO askAdminAnswer(@RequestBody AskAdminAnswerDTO askAdminAnswerDTO) {
        askAdminAnswerService.insertAnswer(askAdminAnswerDTO);
        askAdminAnswerService.updateStatus(askAdminAnswerDTO.getAskAdminId());

        return askAdminAnswerDTO;
    }

    /*ask-admin-answer 끝*/
}
