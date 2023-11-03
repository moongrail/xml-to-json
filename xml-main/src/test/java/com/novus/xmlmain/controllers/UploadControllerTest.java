package com.novus.xmlmain.controllers;

import com.novus.xmlmain.service.UploadService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = UploadController.class)
class UploadControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UploadService uploadService;

    @Test
    @SneakyThrows
    void testUploadPage_whenGetPage_thenReturnUploadPageAndStatusOk() {
        mockMvc.perform(get("/upload"))
                .andExpect(status().isOk())
                .andExpect(view().name("uploadForm"));
    }

    @Test
    @SneakyThrows
    void testUploadFile_whenNonXmlFileUploaded_thenReturnUploadPageAndHasError() {
        MockMultipartFile file = new MockMultipartFile("file", "test.txt", "text/plain", "test data".getBytes());

        mockMvc.perform(multipart("/upload").file(file))
                .andExpect(status().isOk())
                .andExpect(model().hasErrors())
                .andExpect(model().attributeHasErrors("uploadForm"));

        verifyNoInteractions(uploadService);
    }

    @Test
    @SneakyThrows
    void testUploadFile_whenNullFileUploaded_thenReturnUploadPageAndHasError() {
        mockMvc.perform(multipart("/upload"))
                .andExpect(status().isOk())
                .andExpect(model().hasErrors())
                .andExpect(model().attributeHasErrors("uploadForm"));

        verifyNoInteractions(uploadService);
    }


    @Test
    public void testUploadFile_whenValidFile_thenReturnRedirectToResult() throws Exception {
        MockMultipartFile file = new MockMultipartFile("file", "test.xml",
                "text/xml", "<xml>Test</xml>".getBytes());

        when(uploadService.convertXmlToJson(any())).thenReturn("jsonResult");

        mockMvc.perform(multipart("/upload")
                        .file(file))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/result"))
                .andExpect(flash().attributeExists("responseForm"))
                .andExpect(flash().attribute("responseForm", hasProperty("jsonResult", is("jsonResult"))));

        verify(uploadService, times(1)).convertXmlToJson(any());
    }
}