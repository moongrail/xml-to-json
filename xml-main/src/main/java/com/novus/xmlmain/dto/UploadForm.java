package com.novus.xmlmain.dto;

import com.novus.xmlmain.validation.XmlFile;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UploadForm {
    @NotNull
    private MultipartFile file;
}
