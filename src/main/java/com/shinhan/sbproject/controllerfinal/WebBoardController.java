package com.shinhan.sbproject.controllerfinal;

import com.shinhan.sbproject.entity.MemberEntity;
import com.shinhan.sbproject.entityfinal.WebBoardDTO;
import com.shinhan.sbproject.entityfinal.WebBoardEntity;
import com.shinhan.sbproject.paging.PageRequestDTO;
import com.shinhan.sbproject.paging.PageResultDTO;
import com.shinhan.sbproject.repositoryfinal.WebBoardRepository;
import com.shinhan.sbproject.repositoryfinal.WebReplyRepository;
import com.shinhan.sbproject.servicefinal.WebBoardService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

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

    @Value("${com.shinhan.sbproject.upload.path}")
    private String uploadPath;

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
    public String f_insert(WebBoardDTO dto,
                           @RequestParam("uploadFiles") MultipartFile[] uploadFiles,
                           @RequestParam("mid") String mid) {

        String imageUrl = null;

        for (MultipartFile uploadFile : uploadFiles) {
            if (uploadFile.isEmpty()) continue;

            // 이미지 업로드 처리 (UploadController에서 따로 메서드로 분리해도 좋음)
            try {
                String originalName = uploadFile.getOriginalFilename();
                String uuid = UUID.randomUUID().toString();
                String fileName = uuid + "_" + originalName;

                String folderPath = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")).replace("/", File.separator);
                File uploadDir = new File(uploadPath, folderPath);
                if (!uploadDir.exists()) uploadDir.mkdirs();

                File saveFile = new File(uploadDir, fileName);
                uploadFile.transferTo(saveFile);

                imageUrl = "/display?fileName=" + folderPath + "/" + fileName;

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        dto.setImageUrl(imageUrl);  // DTO에 이미지 URL 설정
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

    @GetMapping("/{bno}/repliesPage.do")
    public String showReplyPage(@PathVariable("bno") Long bno, Model model) {
        model.addAttribute("bno", bno); // JavaScript에서 댓글 요청 시 사용
        return "board/replies"; // templates/board/replies.html 로 이동
    }

//    @GetMapping("/{bno}/repliesPage.do")
//    @ResponseBody
//    public List<WebReplyDTO> showReplyPage(@PathVariable("bno") Long bno) {
//        // 댓글 서비스에서 bno에 해당하는 댓글 목록 가져오기
//        List<WebReplyDTO> replies = wrRepo.findByBno(bno)   // Repository 메서드 필요
//                .stream()
//                .map(WebReplyDTO::fromEntity) // DTO 변환
//                .toList();
//        return replies;
//    }
}
