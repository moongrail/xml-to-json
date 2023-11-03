package com.novus.xmlmain.controllers;

import com.novus.xmlmain.dto.ResponseForm;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ResultController.class)
class ResultControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @SneakyThrows
    void testGetResult_whenModelDoesNotContainResponseForm_thenRedirectUpload() {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/result");

        mockMvc.perform(requestBuilder)
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/upload"));
    }


    @Test
    @SneakyThrows
    void testGetResultWhenModelContainsResponseFormThenResultStatusOk() {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/result")
                .flashAttr("responseForm", new ResponseForm());

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(view().name("result"));
    }
}