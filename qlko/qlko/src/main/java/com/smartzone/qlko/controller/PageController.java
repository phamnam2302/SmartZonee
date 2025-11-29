package com.smartzone.qlko.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    // 1. Khi gõ http://localhost:8080/login -> Chuyển sang file login.html
    @GetMapping("/login")
    public String loginPage() {
        return "redirect:/login.html";
    }

}