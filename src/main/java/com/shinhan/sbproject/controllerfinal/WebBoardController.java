package com.shinhan.sbproject.controllerfinal;

import com.shinhan.sbproject.entityfinal.WebBoardDTO;
import com.shinhan.sbproject.servicefinal.WebBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/board")
public class WebBoardController {
    @Autowired
    WebBoardService wService;

    @GetMapping("/list.do")
    public void f1(Model model) {
        List<WebBoardDTO> blist =  wService.getList();
        model.addAttribute("boardList",blist);

    }
}
