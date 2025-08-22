package com.shinhan.sbproject.controllerfinal;


import com.shinhan.sbproject.entityfinal.WebReplyDTO;
import com.shinhan.sbproject.servicefinal.WebReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class WebReplyRestController {
    final WebReplyService replyService;

    //특정 Board의 댓글 조회
    @GetMapping("/board/{bno}/replies")
    public List<WebReplyDTO> f_selectAll(@PathVariable("bno") Long bno) {
        List<WebReplyDTO> replyList = replyService.getList(bno);
        return replyList;
    }

    //댓글 상세보기
    @GetMapping("/replies/{rno}")
    public WebReplyDTO f_detail(@PathVariable("rno") Long rno) {
        return replyService.selectById(rno);
    }

    //댓글 신규 입력
    // TODO: 오늘할거
    @PostMapping("/board/{bno}/replies")
    public String f_insert(@RequestBody WebReplyDTO reply,
                           @PathVariable("bno") Long bno) {
        reply.setBno(bno);
        int rno = replyService.register(reply);
        return bno + "번 댓글 신규입력";
    }

    //댓글 수정
    @PutMapping("/replies/{rno}")
    public String f_update(@RequestBody WebReplyDTO reply,@PathVariable("rno") Long rno) {
        reply.setRno(rno);
        int updateRno = replyService.modify(reply);
        return updateRno + "댓글수정";
    }

    //댓글 삭제
    @DeleteMapping("/replies/{rno}")
    public String f_delete() {
        return "댓글삭제";
    }

}
