package io.github.mhultjavad24.rss_proxy_api.controller;

import io.github.mhultjavad24.rss_proxy_api.model.Episode;
import io.github.mhultjavad24.rss_proxy_api.service.RssService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/rss")
public class RssController {

    private final RssService rssService;

    public RssController(RssService rssService) {
        this.rssService = rssService;
    }

    @GetMapping("/episodes")
    public ResponseEntity<?> getEpisodes(@RequestParam("url") String url) {
        try {
            List<Episode> episodes = rssService.fetchAndParseRssFeed(url);
            return ResponseEntity.ok(episodes);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error fetching or parsing RSS feed: " + e.getMessage());
        }
    }
} 