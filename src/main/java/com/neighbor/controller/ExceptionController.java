package com.neighbor.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class ExceptionController implements ErrorController {

//    @RequestMapping("/error")
//    public RedirectView handleError(HttpServletRequest request, HttpServletResponse response) {
//        RedirectView redirectView = new RedirectView();
//        redirectView.setUrl("/main"); // 리다이렉트할 페이지 URL 설정
//        redirectView.setExposeModelAttributes(false); // 모델 속성 노출하지 않음
//
//        // 예외 처리 코드
//        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
//        if (status != null) {
//            int statusCode = Integer.valueOf(status.toString());
//            if (statusCode == HttpStatus.NOT_FOUND.value()) { // 404 에러 처리
//                return redirectView;
//            } /*else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) { // 500 에러 처리
//                return redirectView;
//            }*/
//        }
//
//        // 다른 예외 발생 시도 메인 페이지로 리다이렉트
//        return redirectView;
//    }
}