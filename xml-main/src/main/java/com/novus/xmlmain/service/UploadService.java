package com.novus.xmlmain.service;

import org.springframework.web.multipart.MultipartFile;

public interface UploadService {
    String convertXmlToJson(MultipartFile file);
}
