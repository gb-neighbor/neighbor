package com.neighbor.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class TestController {

    @GetMapping("0")
    public String test(){
        return "/admin/dash-board";
    }

    @GetMapping("1")
    public String productdetail(){
        return "/board/product-detail2";
    }


    @GetMapping("2")
    public String listbymember(){
        return "/list/list-by-member";
    }

    @GetMapping("3")
    public String listByRegion(){
        return "/list/list-by-region";
    }

    @GetMapping("4")
    public String findid(){
        return "/login/find-my-id";
    }

    @GetMapping("5")
    public String join(){
        return "/login/join";
    }
    @GetMapping("6")
    public String login(){
        return "/login/login";
    }
    @GetMapping("7")
    public String updatepassword(){
        return "/login/update-password";
    }
    @GetMapping("8")
    public String main(){
        return "/main/main";
    }
    @GetMapping("9")
    public String faq(){
        return "mypage/faq";
    }
    @GetMapping("10")
    public String inquirytoadmin(){
        return "mypage/inquiry_to_admin";
    }
    @GetMapping("11")
    public String detail(){
        return "mypage/inquiry_to_admin_detail";
    }
    @GetMapping("12")
    public String write(){
        return "mypage/inquiry_to_admin_write";
    }
    @GetMapping("13")
    public String messagebox(){
        return "mypage/message_box";
    }
    @GetMapping("14")
    public String update(){
        return "mypage/password_update";
    }

    @GetMapping("15")
    public String home(){
        return "mypage/profile_home";
    }
    @GetMapping("16")
    public String review(){
        return "mypage/review";
    }
    @GetMapping("17")
    public String upload(){
        return "mypage/upload_food";
    }
    @GetMapping("18")
    public String userleave(){
        return "mypage/user_leave";
    }

    @GetMapping("19")
    public String manageBoard(){
        return "admin/manage-board";
    }

    @GetMapping("20")
    public String manageQna(){
        return "admin/manage-qna";
    }

    @GetMapping("21")
    public String manageReply(){
        return "admin/manage-reply";
    }

    @GetMapping("22")
    public String manageUser(){
        return "admin/manage-user";
    }

}