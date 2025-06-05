package com.studentmanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FallbackController {
    @RequestMapping("/**")
    public String fallback() {
        return "not-found"; // a Thymeleaf template showing 404 message
    }
}
