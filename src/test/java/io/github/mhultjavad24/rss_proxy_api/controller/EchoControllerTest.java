package io.github.mhultjavad24.rss_proxy_api.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EchoController.class)
public class EchoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testEchoEndpoint() throws Exception {
        mockMvc.perform(get("/api/echo"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello World"));
    }
} 