package com.novus.xmlmain.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/result")
public class ResultController {
    @GetMapping
    public String getResult(final Model model) {

        if (!model.containsAttribute("responseForm")) {
            return "redirect:/upload";
        }

        model.getAttribute("responseForm");

        return "result";
    }
}
