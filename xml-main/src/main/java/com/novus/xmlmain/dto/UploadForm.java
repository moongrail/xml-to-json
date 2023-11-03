package com.novus.xmlmain.dto;

import com.novus.xmlmain.validation.NotEmptyMultipartFile;
import com.novus.xmlmain.validation.XmlFileFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UploadForm {
    @NotEmptyMultipartFile
    @XmlFileFormat
    private MultipartFile file;
}
