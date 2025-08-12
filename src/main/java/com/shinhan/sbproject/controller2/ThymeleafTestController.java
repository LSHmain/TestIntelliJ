package com.shinhan.sbproject.controller2;


import com.shinhan.sbproject.dto.GuestBookDTO;
import com.shinhan.sbproject.entity.MemberEntity;
import com.shinhan.sbproject.entity.MemberRole;
import com.shinhan.sbproject.repository2.FreeBoardRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/html")
public class ThymeleafTestController {

    @Autowired
    FreeBoardRepository fRepo;

    @GetMapping("/layout1")
    public String f_layout1(){

        return "layout/exLayout1";
    }


    @GetMapping("/layout2")
    public String f_template1(){

        return "layout/exTemplate";
    }


    @GetMapping("/test")
    public String f_test(Model model){
        model.addAttribute("hello","hello");
        model.addAttribute("price",1234);
        model.addAttribute("title","titititit");
        model.addAttribute("option", List.of());
        return "html/test";
    }


    @GetMapping("/freeboard")
    public void f_selectAll(Model model, HttpSession session){
        GuestBookDTO guest = GuestBookDTO.builder()
                .title("title")
                .writer("writer")
                .content("content")
                .build();
        model.addAttribute("greeting", "hello~");
        model.addAttribute("book",guest);
        model.addAttribute("blist",fRepo.findAll());
        model.addAttribute("searchNo",23);

        MemberEntity user = MemberEntity.builder()
                .mid("10번")
                .mname("10번회원")
                .mpassword("10번비밀번호")
                .mrole(MemberRole.MANAGER)
                .build();
        session.setAttribute("loginUser",user);
    }

    @GetMapping("/sample")
    public void f1(Model model){
        GuestBookDTO guest = GuestBookDTO.builder()
                .title("title")
                .writer("writer")
                .content("content")
                .build();
        model.addAttribute("greeting", "hello~");
        model.addAttribute("book",guest);
    }
}
