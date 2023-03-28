package com.neighbor.controller;

import com.neighbor.domain.dto.MemberDTO;
import com.neighbor.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@RestController
@RequestMapping("/members/*")
@RequiredArgsConstructor
public class MemberController {

}
