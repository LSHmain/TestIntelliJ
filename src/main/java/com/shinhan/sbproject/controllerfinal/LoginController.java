package com.shinhan.sbproject.controllerfinal;

import com.shinhan.sbproject.entity.MemberEntity;
import com.shinhan.sbproject.servicefinal.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Controller
@RequestMapping("/auth")
public class LoginController {

    @GetMapping("/login")
    public void login() {

    }
    @GetMapping("/loginSuccess")
    public void loginSuccess() {

    }
    @GetMapping("/logout")
    public void logout() {

    }
    @GetMapping("/accessDenined")
    public void accessDenined() {

    }
    //사용자등록페이지 보여주기
    @GetMapping("/signup")
    public String joinForm() {
        return "auth/joinForm";
    }

    @Autowired
    MemberService memberService;

    @ResponseBody
    @PostMapping("/joinProc")
    public String joinProc(MemberEntity member) {
        MemberEntity regiMember = memberService.joinUser(member);
        return regiMember!=null && Objects.equals(member.getMid(), regiMember.getMid()) ?"register OK":"register fail";
    }

    //---------------------------------------------------------//
    //로그인 post
    //회원가입 signup

//    @Autowired
//    AuthServiceLogin authService;
//
//    @PostMapping(value = "/login" )
//    @ResponseBody
//    public ResponseEntity<TokenDTO> getMemberProfile(@RequestBody MemberEntity request) {
//        System.out.println(request);
//
//        String token = authService.login(request);
//        TokenDTO dto = TokenDTO.builder().login(request.getMid()).token(token).build();
//        return new ResponseEntity<>(dto, HttpStatus.OK);
//    }

    @PostMapping("/signup")
    @ResponseBody
    public ResponseEntity<MemberEntity> f7(@RequestBody MemberEntity member) {
        System.out.println("signup:" + member);
        MemberEntity newMember = memberService.joinUser(member);
        return new ResponseEntity<>(newMember, HttpStatus.OK);
    }



}



