package com.novus.xmlmain.controllers;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.novus.xmlmain.dto.UploadForm;
import com.novus.xmlmain.exception.FormatUploadException;
import com.novus.xmlmain.service.UploadService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
@RequestMapping("/upload")
//@RequiredArgsConstructor
@Slf4j
public class UploadController {
//    private final UploadService uploadService;

    @GetMapping
    public String getUploadForm(Model model){
        model.addAttribute("uploadForm", new UploadForm());
        return "uploadForm";
    }

    @PostMapping
    public ModelAndView uploadFile(UploadForm uploadForm) {
        MultipartFile file = uploadForm.getFile();

        if (!file.getOriginalFilename().endsWith(".xml")) {
            throw new FormatUploadException("Upload format exception, not XML file");
        }

        String result = uploadedFile(file);
        uploadForm.setResult(result);

        ModelAndView modelAndView = new ModelAndView("result");
        modelAndView.addObject("uploadForm", uploadForm);
        return modelAndView;
    }

    private String uploadedFile(MultipartFile file) {
        //TODO: сделать нормальный парсинг и перенести в сервис.
        String xml = null;
        String json = convertXmlToJson(xml);
        return json;
    }

    private String convertXmlToJson(String xml) {
         Gson gson = new Gson();
         JsonObject jsonObject = gson.fromJson(xml, JsonObject.class);
         String json = gson.toJson(jsonObject);
        return json;
    }

    @ExceptionHandler(FormatUploadException.class)
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    public ModelAndView  uploadException(HttpServletRequest req, FormatUploadException ex){
        //TODO: сделать другой вывод ошибки.
        log.error("Request: " + req.getRequestURL() + " raised " + ex);

        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", ex);
        mav.addObject("url", req.getRequestURL());
        mav.setViewName("error");
        return mav;
    }
}
