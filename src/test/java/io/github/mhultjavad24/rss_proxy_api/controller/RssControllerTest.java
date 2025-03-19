package io.github.mhultjavad24.rss_proxy_api.controller;

import io.github.mhultjavad24.rss_proxy_api.model.Episode;
import io.github.mhultjavad24.rss_proxy_api.service.RssService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RssController.class)
public class RssControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RssService rssService;

    @Test
    public void testGetEpisodes_Success() throws Exception {
        // Sample data
        String testUrl = "https://example.com/feed.xml";
        List<Episode> mockEpisodes = Arrays.asList(
                new Episode("Episode 1", "https://example.com/ep1", "Description 1", new Date(), "guid1"),
                new Episode("Episode 2", "https://example.com/ep2", "Description 2", new Date(), "guid2")
        );
        
        // Mock service response
        when(rssService.fetchAndParseRssFeed(anyString())).thenReturn(mockEpisodes);
        
        // Test endpoint
        mockMvc.perform(get("/api/rss/episodes")
                .param("url", testUrl)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Episode 1"))
                .andExpect(jsonPath("$[0].link").value("https://example.com/ep1"))
                .andExpect(jsonPath("$[1].title").value("Episode 2"))
                .andExpect(jsonPath("$[1].link").value("https://example.com/ep2"));
    }

    @Test
    public void testGetEpisodes_Error() throws Exception {
        // Mock an exception
        when(rssService.fetchAndParseRssFeed(anyString())).thenThrow(new RuntimeException("Invalid feed"));
        
        // Test endpoint error handling
        mockMvc.perform(get("/api/rss/episodes")
                .param("url", "https://invalid-url.com/feed.xml")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
} 