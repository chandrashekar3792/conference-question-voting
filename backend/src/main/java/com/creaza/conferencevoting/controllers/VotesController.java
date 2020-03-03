package com.creaza.conferencevoting.controllers;

import com.creaza.conferencevoting.model.dto.VoteDto;
import com.creaza.conferencevoting.service.VotesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/votes")
@CrossOrigin(origins = "http://localhost:3000")
public class VotesController {

    private static final Logger log = LoggerFactory.getLogger(VotesController.class);

    private VotesService votesService;

    @Autowired
    public VotesController(VotesService votesService) {
        this.votesService = votesService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<VoteDto> fetchVotes() {
        log.info("fetch votes request");
        return votesService.fetchVotes();
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity voteForQuestion(@RequestParam(value = "questionId") Long questionId, @RequestParam("userId") Long userId, @RequestParam("choiceId") Long choiceId) {
        log.info("new vote request");
        votesService.voteForQuestion(questionId, userId, choiceId);
        return ResponseEntity.created(null).build();
    }
}
