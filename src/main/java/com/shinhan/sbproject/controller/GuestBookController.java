package com.shinhan.sbproject.controller;

import com.shinhan.sbproject.dto.GuestBookDTO;
import com.shinhan.sbproject.service.GuestBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GuestBookController {

    @Autowired
    GuestBookService guestBookService;
    // TODO: 추후 수정 예정
    @GetMapping("/guest")
    public List<GuestBookDTO> selectAll() {
        return guestBookService.selectAll();
    }
}
