package com.neighbor.controller;


import com.neighbor.domain.dto.*;
import com.neighbor.service.AskAdminService;
import com.neighbor.service.BoardService;
import com.neighbor.service.MemberService;
import com.neighbor.service.ReplyService;
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
@RequestMapping("/admins/*")
public class AdminController {

    private final MemberService memberService;
    private final AskAdminService askAdminService;
    private final BoardService boardService;
    private final ReplyService replyService;


    @GetMapping("form")
    public void form(Model model, int number) {
        model.addAttribute("number",number);
    }


    /*AskAdmin 시작*/

    //   문의목록 문의사항 삭제
    @DeleteMapping("ask-admin-delete")
    public void askAdminDelete(@PathVariable Long askAdminId) {
        askAdminService.delete(askAdminId);
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

    // 문의목록 문의사항 답변대기중 조회
    @GetMapping("ask-admin-wait-list")
    public String askAdminShowList(Criteria criteria, Model model) {
        if(criteria.getPage() == 0){
            criteria.create(1,6);
        }

        model.addAttribute("Asks", askAdminService.getListAll(criteria.create(criteria.getPage(), criteria.getAmount()))); // 업데이트된 Criteria 객체 전달
        model.addAttribute("pagination", new PageDTO().createPageDTO(criteria, askAdminService.getCountAll()));

        return "admin/manage-qna";
    }


    //    문의목록 총 질문 수
    @GetMapping("ask-admin-countAll")
    public Integer getAskAdminTotal() {
        return askAdminService.getCountAll();
    }

    //    문의목록 답변 대기 중인 질문 수
    @GetMapping("ask-admin-count")
    public Integer getWaitAnswerTotal() {
        return askAdminService.getCount();
    }

    /*AskAdmin 끝*/

    /*member 시작*/

    //    대쉬보드 전체조회 (PostMan까지 끝냄.)
    @GetMapping("dashboard-list")
    public List<MemberDTO> memberShowList(){
        return memberService.getList();
    }

    //   회원관리 멤버삭제 (PostMan까지 끝냄.)
    @DeleteMapping("member-delete")
    public void memberDelete(@PathVariable Long memberId) {
        memberService.delete(memberId);
    }

    /*member 끝*/

    /*reply 시작*/

    /*처음 클릭했을때 화면으로 가는 일반 컨트롤러*/
    @GetMapping("reply/list")
    public String replyShowList(Criteria criteria, Model model) {
        if (criteria.getPage() == 0) {
            criteria = criteria.create(1, 6);
        }

        ReplyDTO replyDTO = new ReplyDTO();
//        전체 수
        replyDTO.setReplyTotal(replyService.getCountAll());
        List<ReplyDTO> replyDTOS = replyService.getList(criteria);

        for (ReplyDTO dto : replyDTOS) {
            dto.change(dto.getBoardRegion());
        }
        model.addAttribute("replys", replyDTOS);

        return "admin/manage-reply";
    }

    /* 후기작성 ajax로 리스트를 불러오는 컨트롤러 */

    @PostMapping("reply/list")
    @ResponseBody
    public ReplyDTO showList() {
        Criteria criteria = new Criteria();
        criteria = criteria.create(3, 6);
        ReplyDTO replyDTO = new ReplyDTO();
        replyDTO.setReplyTotal(replyService.getCountAll());
        replyDTO.setReplyDTOS(replyService.getList(criteria));
        replyDTO.setPageDTO(new PageDTO().createPageDTO(criteria, replyService.getCountAll()));

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



}
