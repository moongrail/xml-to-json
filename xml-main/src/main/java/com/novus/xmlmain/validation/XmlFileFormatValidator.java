package com.novus.xmlmain.validation;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class XmlFileFormatValidator implements ConstraintValidator<XmlFileFormat, MultipartFile> {
    @Override
    public boolean isValid(MultipartFile value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty()) {
            return true; // Skip validation if no file is provided
        }

        return value.getOriginalFilename().endsWith(".xml");
    }
}
