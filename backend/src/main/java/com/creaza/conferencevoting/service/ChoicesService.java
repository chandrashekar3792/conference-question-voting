package com.creaza.conferencevoting.service;

import com.creaza.conferencevoting.exception.ResourceNotFoundException;
import com.creaza.conferencevoting.model.dao.Choice;
import com.creaza.conferencevoting.model.dto.ChoiceDto;
import com.creaza.conferencevoting.repository.ChoiceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChoicesService {

    private static final Logger log = LoggerFactory.getLogger(ChoicesService.class);

    private ChoiceRepository choiceRepository;

    @Autowired
    public ChoicesService(ChoiceRepository choiceRepository) {
        this.choiceRepository = choiceRepository;
    }

    public List<ChoiceDto> fetchChoices() {
        log.info("fetching all choices");
        return choiceRepository.findAll()
                .stream()
                .map(choice -> new ChoiceDto(choice.getId(), choice.getQuestion().getId(), choice.getVoteCount()))
                .collect(Collectors.toList());
    }

    public Choice createChoice(Choice choice) {
        log.info("persisting a new choice");
        return choiceRepository.save(choice);
    }

    public Choice getChoiceById(Long id) {
        log.info("fetching choice by id");
        return choiceRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Choice", "id", id));
    }
}
