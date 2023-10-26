package com.novus.xmlmain.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UploadForm {
    private MultipartFile file;
    private String result;
}
