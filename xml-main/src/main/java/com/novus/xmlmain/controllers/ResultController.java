package com.novus.xmlmain.controllers;

import com.novus.xmlmain.dto.ResponseForm;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/result")
public class ResultController {
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getResult(Model model) {
        if (!model.containsAttribute("responseForm")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("ResponseForm not found");
        }

        ResponseForm responseForm = (ResponseForm) model.getAttribute("responseForm");

        return ResponseEntity.ok()
                .body(responseForm.getJsonResult());
    }
}
