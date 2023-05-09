package com.example.testconsul;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsulController {

    @GetMapping("/health")
    private String healthCheck() {
        return "HEALTH CHECK OK";
    }

}
