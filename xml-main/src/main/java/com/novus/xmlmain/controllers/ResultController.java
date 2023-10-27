package com.novus.xmlmain.controllers;

import org.apache.commons.text.StringEscapeUtils;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;


@RestController
public class ResultController {

    @GetMapping("/result")
    public String getResult(Model model) {
//        StringEscapeUtils
//                .unescapeJava(Objects
//                        .requireNonNull(model.
//                                getAttribute("responseForm")).toString());

        return "result";
    }
}
