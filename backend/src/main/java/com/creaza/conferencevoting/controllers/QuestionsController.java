package com.creaza.conferencevoting.controllers;

import com.creaza.conferencevoting.model.dao.Question;
import com.creaza.conferencevoting.model.dto.QuestionCreationDto;
import com.creaza.conferencevoting.model.dto.QuestionUpdationDto;
import com.creaza.conferencevoting.service.QuestionsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/questions")
@CrossOrigin(origins = "http://localhost:3000")
public class QuestionsController {

    private static final Logger log = LoggerFactory.getLogger(QuestionsController.class);

    private QuestionsService questionsService;

    @Autowired
    public QuestionsController(QuestionsService questionsService) {
        this.questionsService = questionsService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Question> getAllQuestions() {
        log.info("new fetch request");
        return questionsService.getAllQuestions();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity createQuestion(@RequestBody @Valid QuestionCreationDto question) {
        log.info("new creation request");
        Question created = questionsService.createQuestion(question);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity getQuestionById(@PathVariable(value = "id") Long questionId) {
        log.info("new fetch by id request");
        Question question = questionsService.getQuestionById(questionId);
        return ResponseEntity.ok(question);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity updateQuestion(@RequestBody @Valid QuestionUpdationDto question) {
        log.info("new update request");
        Question updated = questionsService.updateQuestion(question);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteQuestion(@PathVariable(value = "id") Long questionId) {
        log.info("new delete request");
        questionsService.deleteQuestion(questionId);
        return ResponseEntity.ok().build();
    }
}