package io.github.mhultjavad24.rss_proxy_api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class EchoController {

    @GetMapping("/echo")
    public ResponseEntity<String> echo() {
        return ResponseEntity.ok("Hello World");
    }
} 