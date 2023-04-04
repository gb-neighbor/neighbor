package com.neighbor.controller;

import com.neighbor.domain.dto.BoardDTO;
import com.neighbor.domain.dto.ReplyDTO;
import com.neighbor.domain.vo.MemberVO;
import com.neighbor.service.BoardService;
import com.neighbor.service.MemberService;
import com.neighbor.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/mypage/*")
public class MypageController {

    private final MemberService memberService;
    private final MessageService messageService;
    private final BoardService boardService;

//  프로필에 올라갈 정보를 가져오는 메소드
    private Model getMemberInfo(Model model, HttpSession session){
//        Long memberId = (Long)session.getAttribute("memberId");
        Long memberId = 3L;
        MemberVO memberVO = memberService.getOneMemberInfo(memberId);
        Integer boardCount = messageService.getCountBoard(memberId);
        Integer replyCount = messageService.getCountReply(memberId);

        model.addAttribute("memberInfo", memberVO);
        model.addAttribute("boardCount", boardCount);
        model.addAttribute("replyCount", replyCount);

        return model;
    }


//   쪽지함, 세션 매개변수로 받기 (쪽지함 이동)
    @GetMapping("message_box")
    public void goToMessageBox(Model model, HttpSession session){
        getMemberInfo(model, session);
    }

//   비밀번호 변경 세션 매개변수로 받기
    @GetMapping("password_update")
    public void goToUpdatePassword(Model model, HttpSession session){
        getMemberInfo(model, session);
    }

//    비번 변경 완료
    @PostMapping("updatePassword")
    public RedirectView updatePassword(String memberPassword, Long memberId){
        messageService.setNewPassword(memberPassword, memberId);
        return new RedirectView("/main");
    }



//   회원탈퇴, 매개변수로 회원 아이디 받기
    @GetMapping("user_leave")
    public void goToUserLeave(Model model, HttpSession session) {
        getMemberInfo(model, session);
    }

//    회원탈퇴 완료
    @PostMapping("leave")
    public String deleteMember(Long memberId){
            memberService.delete(memberId);
        return "redirect:/main";
    }

//     내가 올린 음식
    @GetMapping("upload_food")
    public void goToMyFood(Model model, HttpSession session){
        getMemberInfo(model, session);
    }

// 내가 올린 음식 리스트 조회
    @PostMapping("myFoodList/{memberId}/{page}")
    @ResponseBody
    public List<BoardDTO> getListOfMyFood(@PathVariable("memberId")Long memberId, @PathVariable("page") int page){
        List<BoardDTO> result = messageService.getBoardByMemberId(memberId);
        for(BoardDTO board : result){
            board.setAvgScore(messageService.getAvgScore(board.getBoardId()));
            board.setTotalReply(messageService.getTotalReply(board.getBoardId()));
        }
        return result;
    }

//      프로필 홈
    @GetMapping("profile_home")
    public void getProfileInfo(Model model, HttpSession session){
        getMemberInfo(model, session);
    }

//      후기
    @GetMapping("review")
    public void gotomyReview(Model model, HttpSession session){
        getMemberInfo(model, session);
    }

//    후기목록
    @PostMapping("review/list/{memberId}/{page}")
    @ResponseBody
    public List<ReplyDTO> getReviewList(@PathVariable("memberId") Long memberId, @PathVariable("page") int page) {
        return messageService.getReplyByMemberId(memberId);
    }










}
