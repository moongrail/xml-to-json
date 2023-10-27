package com.novus.xmlmain.controllers;

import com.google.gson.Gson;
import com.novus.xmlmain.dto.ResponseForm;
import com.novus.xmlmain.dto.UploadForm;
import com.novus.xmlmain.exception.FormatUploadException;
import com.novus.xmlmain.service.UploadService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("/upload")
@RequiredArgsConstructor
@Slf4j
public class UploadController {
    private final UploadService uploadService;
    private final Gson gson;

    @GetMapping
    public String getUploadForm(Model model) {
        model.addAttribute("uploadForm", new UploadForm());
        return "uploadForm";
    }

    @PostMapping
    public ModelAndView uploadFile(@Valid UploadForm uploadForm) {
        MultipartFile file = uploadForm.getFile();

        if (!file.getOriginalFilename().endsWith(".xml")) {
            throw new FormatUploadException("Upload format exception, not XML file");
        }

        String result = uploadService.convertXmlToJson(file);

        ResponseForm responseForm = ResponseForm.builder()
                .result(gson.toJson(result))
                .build();

        ModelAndView modelAndView = new ModelAndView("result");
        modelAndView.addObject("responseForm", responseForm);
//        model.addAttribute("responseForm", responseForm);
        return modelAndView;
    }
}
