package com.shinhan.sbproject.controllerfinal;

import com.querydsl.core.types.Predicate;
import com.shinhan.sbproject.entity.MemberEntity;
import com.shinhan.sbproject.entityfinal.WebBoardDTO;
import com.shinhan.sbproject.entityfinal.WebBoardEntity;
import com.shinhan.sbproject.paging.PageRequestDTO;
import com.shinhan.sbproject.paging.PageResultDTO;
import com.shinhan.sbproject.repositoryfinal.WebBoardRepository;
import com.shinhan.sbproject.repositoryfinal.WebReplyRepository;
import com.shinhan.sbproject.servicefinal.WebBoardService;
import jakarta.servlet.http.HttpSession;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestClient;

import java.security.Principal;
import java.util.List;
import java.util.function.Function;

@Controller
@RequestMapping("/board")
public class WebBoardController {
    @Autowired
    WebBoardService wService;

    @Autowired
    WebBoardRepository wbRepo;

    @Autowired
    WebReplyRepository wrRepo;
    @Autowired
    private WebBoardService webBoardService;

    @GetMapping("/update.do")
    public String f_update(WebBoardDTO dto){
        webBoardService.modify(dto);
        return "redirect:/board/list.do";
    }


    @GetMapping("/insert.do")
    public void f_insertGet(HttpSession httpSession,@AuthenticationPrincipal UserDetails securituUser, Principal principal, Authentication auth, Model model){
        MemberEntity member = (MemberEntity) httpSession.getAttribute("loginMember");
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        String mid = securituUser.getUsername();
        model.addAttribute("loginMember", member);
        model.addAttribute("loginId", mid);

    }

    @PostMapping("/insert.do")
    public String f_insert(WebBoardDTO dto){
        webBoardService.register(dto);
        return "redirect:/board/list.do";
    }


    @GetMapping("/delete.do")
    public String f_delete(@RequestParam Long bno){
        webBoardService.delete(bno);
        return "redirect:/board/list.do";
    }


    @GetMapping("/detail.do")
    public void f_detail(Model model, Long bno){
        WebBoardDTO detail = webBoardService.selectById(bno);
        model.addAttribute("board",detail);
    }


    @GetMapping("/list.do")
    public void f1_list(Model model, PageRequestDTO pageDTO){
        if(pageDTO.getPage() == 0){
            pageDTO = PageRequestDTO.builder()
                    .page(1)
                    .size(5)
                    .build();
        }
        PageResultDTO<WebBoardDTO, WebBoardEntity> responseResult = webBoardService.getList(pageDTO);
        model.addAttribute("boardList",responseResult);
        model.addAttribute("pageInfo", pageDTO);
    }
}
