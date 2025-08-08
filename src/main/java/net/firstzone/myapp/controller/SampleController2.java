package net.firstzone.myapp.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController2 {

    @GetMapping("/scan2")
    public String f1() {
        String s = "scan succ....";
        return s;
    }
}
