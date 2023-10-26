package com.novus.xmlmain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class UploadServiceImpl implements UploadService {
//    private final JsonRepository jsonRepository;

    @Override
    public String convertXmlToJson(MultipartFile file) {
        return "Kek";
    }
}
