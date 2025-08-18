package com.shinhan.sbproject.controllerfinal;

import com.shinhan.sbproject.entityfinal.WebBoardDTO;
import com.shinhan.sbproject.paging.PageRequestDTO;
import com.shinhan.sbproject.paging.PageResultDTO;
import com.shinhan.sbproject.entityfinal.WebBoardEntity;
import com.shinhan.sbproject.repositoryfinal.WebBoardRepository;
import com.shinhan.sbproject.servicefinal.WebBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/board")
public class WebBoardRestController {

    @Autowired
    WebBoardService webBoardService;
    @Autowired
    private WebBoardRepository webBoardRepository;


    @GetMapping("/list")
    public List<WebBoardDTO> list() {
        return webBoardService.selectAll();
    }

    @GetMapping("/listPaging")
    public Map<String,Object> listPaging(PageRequestDTO pageRequestDTO) {
        if(pageRequestDTO.getPage() == 0) {
            pageRequestDTO.setPage(1);
            pageRequestDTO.setSize(10);
        }
        PageResultDTO<WebBoardDTO, WebBoardEntity> responseData =  webBoardService.getList(pageRequestDTO);
        Map<String,Object> map = new HashMap<>();
        map.put("boards", responseData);
        map.put("pageRequestDTO", pageRequestDTO);
        return map;
    }

    @GetMapping("/{boardNo}")
    public WebBoardDTO board(@PathVariable("boardNo") Long boardNo) {
        return webBoardService.selectById(boardNo);
    }

    @GetMapping("/insert")
    public String insert(Model model) {
        return "html/insert";
    }
    @PostMapping
    public String insert(@ModelAttribute WebBoardDTO webBoardDTO) {
        int result = webBoardService.register(webBoardDTO);
        return result>0?result + "번 입력 성공":"";
    }
    @PostMapping("/update")
    public String update(@ModelAttribute WebBoardDTO webBoardDTO) {
        int result = webBoardService.modify(webBoardDTO);
        return result>0?result + "번 수정 성공":"";
    }
    @GetMapping("/delete")
    public String delete(Long bno) {
        int result = webBoardService.delete(bno);
        return result>0?result + "번 삭제 성공":"";
    }
}
