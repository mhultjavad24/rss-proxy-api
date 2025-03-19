package io.github.mhultjavad24.rss_proxy_api.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RssController.class)
public class RssControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testValidateRssFeed() throws Exception {
        String testUrl = "https://example.com/feed.xml";
        
        mockMvc.perform(get("/api/rss/validate")
                .param("url", testUrl))
                .andExpect(status().isOk())
                .andExpect(content().string(testUrl));
    }

    @Test
    public void testValidateRssFeedWithComplexUrl() throws Exception {
        String testUrl = "https://example.com/feed.xml?param1=value1&param2=value2";
        
        mockMvc.perform(get("/api/rss/validate")
                .param("url", testUrl))
                .andExpect(status().isOk())
                .andExpect(content().string(testUrl));
    }
} 