package com.shinhan.sbproject.upload;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UploadTestController {

    @GetMapping("/upload/uploadEx")
    public String uploadEx() {
        return "html/uploadEx";
    }
}
