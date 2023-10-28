package com.novus.xmlmain.controllers;

import com.novus.xmlmain.dto.ResponseForm;
import com.novus.xmlmain.dto.UploadForm;
import com.novus.xmlmain.exception.FormatUploadException;
import com.novus.xmlmain.service.UploadService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/upload")
@RequiredArgsConstructor
@Slf4j
public class UploadController {
    private final UploadService uploadService;

    @GetMapping
    public String getUploadForm(Model model) {
        model.addAttribute("uploadForm", new UploadForm());
        return "uploadForm";
    }


    @PostMapping
    public String uploadFile(@Valid UploadForm uploadForm, RedirectAttributes redirectAttributes) {
        MultipartFile file = uploadForm.getFile();

        if (!file.getOriginalFilename().endsWith(".xml")) {
            throw new FormatUploadException("Upload format exception, not XML file");
        }

        String result = uploadService.convertXmlToJson(file);

        ResponseForm responseForm = ResponseForm.builder()
                .jsonResult(result)
                .build();

        redirectAttributes.addFlashAttribute("responseForm", responseForm);

        return "redirect:/result";
    }
}
