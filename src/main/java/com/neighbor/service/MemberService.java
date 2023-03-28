package com.neighbor.service;

import com.neighbor.domain.dao.MemberDAO;
import com.neighbor.domain.dto.MemberDTO;
import com.neighbor.domain.vo.MailVO;
import com.neighbor.domain.vo.MemberVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    private  final MemberDAO memberDAO;

    @Autowired
    private JavaMailSender mailSender;

    //    대쉬보드 전체조회
    public List<MemberDTO> getList(){
        return memberDAO.findAll();
    }

    //    대쉬보드 일부조회
    public List<MemberDTO> getListBy(){
        return memberDAO.findAllBy();
    }

    //   회원관리 멤버삭제
    public void delete(Long memberId) {
        memberDAO.delete(memberId);
    }

<<<<<<< HEAD
//    회원관리 멤버 총 수
    public Integer getCountAll() {
        return memberDAO.countAll();
=======
    // 회원가입
    public void signUp(MemberVO memberVO){
        memberDAO.save(memberVO);
>>>>>>> mine
    }

    // 아이디 중복
    public Long checkId(String memberIdentification){
        return memberDAO.selectByIdentification(memberIdentification);
    }

    // 닉네임 중복
    public Long checkNickname(String memberNickName){
        return memberDAO.selectByNickname(memberNickName);
    }

    // 이메일 중복
    public Long checkEmail(String memberEmail){
        return memberDAO.selectByEmail(memberEmail);
    }

    // 로그인
    public Long login(String memberIdentification, String memberPassword){ return memberDAO.selectById(memberIdentification, memberPassword);}

    // 아이디 찾기
    public String findIdentification(String memberEmail){ return memberDAO.selectMyIdentification(memberEmail);}

    // 비밀번호 변경
    public void updatePassword(String memberIdentification, String memberPassword ) { memberDAO.updateMyPassword(memberIdentification, memberPassword);}

    // 메일 보내기
    public void sendMail(MailVO mail) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mail.getAddress());
//        message.setFrom(""); from 값을 설정하지 않으면 application.yml의 username값이 설정됩니다.
        message.setSubject(mail.getTitle());
        message.setText(mail.getMessage());

        mailSender.send(message);
    }
}
