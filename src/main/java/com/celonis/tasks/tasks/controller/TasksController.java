package com.celonis.tasks.tasks.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TasksController {

    @RequestMapping("/")
    public @ResponseBody String greeting() {
        return "Hello, World - Checking Tasks APP.";
    }
}
