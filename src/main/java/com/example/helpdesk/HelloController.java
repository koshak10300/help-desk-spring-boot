package com.example.helpdesk;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/")
    public String hello() {
        return "Hello, Help Desk!";
    }

    @GetMapping("/status")
    public String status() {
        return "Application is running";
    }

    @GetMapping("/about")
    public String about() {
        return "Help Desk project by Zakharchenko Konstantin";
    }
}
