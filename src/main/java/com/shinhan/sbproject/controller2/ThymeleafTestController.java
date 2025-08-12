package com.shinhan.sbproject.controller2;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ThymeleafTestController {

    @GetMapping("/html/sample")
    public void f1(Model model){
        model.addAttribute("greeting", "hello~");
    }
}
