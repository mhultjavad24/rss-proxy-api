package io.github.mhultjavad24.rss_proxy_api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/rss")
public class RssController {

    @GetMapping("/validate")
    public ResponseEntity<String> validateRssFeed(@RequestParam("url") String url) {
        return ResponseEntity.ok(url);
    }
} 