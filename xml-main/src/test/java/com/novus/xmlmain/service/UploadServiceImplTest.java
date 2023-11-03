package com.novus.xmlmain.service;

import com.novus.xmlmain.exception.ConvertErrorException;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UploadServiceImplTest {

    public static final String CONVERT_ERROR_MESSAGE = "Ошибка конвертации: Файл повреждён или XML невалидный.";
    public static final String DEFAULT_XML = "<root><child>10</child></root>";
    @InjectMocks
    private UploadServiceImpl uploadService;

    @Mock
    private MultipartFile file;

    @BeforeEach
    void setUp() throws IOException {
        when(file.getBytes()).thenReturn(DEFAULT_XML.getBytes());
    }

    @Test
    void testConvertXmlToJson_whenValidXml_thenReturnJson() {

        String json = uploadService.convertXmlToJson(file);
        assertNotNull(json);
        assertTrue(json.contains("{\"root\":{\"value\":\"10.0\",\"child\":{\"value\":\"10\"}}}"));
    }

    @Test
    @SneakyThrows
    void testConvertXmlToJson_whenInvalidXml_thenThrowException() {
        when(file.getBytes()).thenReturn("<root><child>10</child>".getBytes());
        ConvertErrorException convertErrorException = assertThrows(ConvertErrorException.class,
                () -> uploadService.convertXmlToJson(file));
        assertEquals(CONVERT_ERROR_MESSAGE, convertErrorException.getMessage());
    }

    @Test
    @SneakyThrows
    void testConvertXmlToJson_whenEmptyXml_thenReturnJsonWithZeroValue() {
        when(file.getBytes()).thenReturn("".getBytes());
        String json = uploadService.convertXmlToJson(file);
        assertNotNull(json);
        assertTrue(json.contains("\"value\":\"0\""));
    }
}