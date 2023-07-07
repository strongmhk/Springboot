package com.cos.controllerdemo.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HttpRedirectionController {

    @GetMapping("/home")
    public String home(){
        // 1만줄 가정
        return "home";
    }

    @GetMapping("/away")
    public String away(){
        // 다른코드
        // home의 코드와 같은 1만줄의 코드가 있다고 가정
        return "redirect:/home";
    }
}
