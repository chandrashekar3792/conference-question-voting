package com.creaza.conferencevoting.controllers;

import com.creaza.conferencevoting.model.dto.ChoiceDto;
import com.creaza.conferencevoting.service.ChoicesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/choices")
@CrossOrigin(origins = "http://localhost:3000")
public class ChoicesController {

    private static final Logger log = LoggerFactory.getLogger(ChoicesController.class);

    private ChoicesService choicesService;

    @Autowired
    public ChoicesController(ChoicesService choicesService) {
        this.choicesService = choicesService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ChoiceDto> getAllChoices() {
        log.info("new fetch request");
        return choicesService.fetchChoices();
    }
}
