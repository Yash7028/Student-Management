package com.studentmanagement.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;

public class CustomErrorController implements ErrorController {
    @RequestMapping("/error")
    public String handleError() {
        // Return custom error view name
        return "not-found";
    }

}
