package com.novus.xmlmain.service;

import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class UploadServiceImplTest {

    private UploadServiceImpl uploadService;

    @BeforeEach
    void setUp() {
        uploadService = new UploadServiceImpl();
    }

    @Test
    @SneakyThrows
    void testConvertXmlToJson() {
        String xml = "<root>\n" +
                " <nodeA>Node 1</nodeA>\n" +
                " <nodeB>\n" +
                "  <nodeC>Node 2</nodeC>\n" +
                "  <nodeD>Node 3</nodeD>\n" +
                " </nodeB>\n" +
                "</root>";


        MockMultipartFile multipartFile = new MockMultipartFile("file",
                "test.xml",
                "application/xml",
                xml.getBytes());

        String result = uploadService.convertXmlToJson(multipartFile);


        assertEquals("{\"root\":{\"nodeA\":\"Node 1\",\"nodeB\":{\"nodeC\":\"Node 2\",\"nodeD\":\"Node 3\"}}}", result);
    }
}