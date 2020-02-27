package com.creaza.conferencevoting.controllers;

import com.creaza.conferencevoting.exception.ResourceNotFoundException;
import com.creaza.conferencevoting.model.Question;
import com.creaza.conferencevoting.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class QuestionsController {

    @Autowired
    QuestionRepository questionRepository;

    @GetMapping("/questions")
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/questions")
    public Question createQuestion(@Valid @RequestBody Question question) {
        question.setDefaultVote();
        return questionRepository.save(question);
    }

    @GetMapping("/questions/{id}")
    public Question getQuestionById(@PathVariable(value = "id") Long questionId) {
        return questionRepository.findById(questionId)
                .orElseThrow(() -> new ResourceNotFoundException("Question", "id", questionId));
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping("/questions/{id}/vote")
    public Question updateQuestion(@PathVariable(value = "id") Long questionId) {

        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new ResourceNotFoundException("Question", "id", questionId));
        question.setUpdatedAt(new Date());
        question.setVotes();

        Question updatedQuestion = questionRepository.save(question);
        return updatedQuestion;
    }

    @DeleteMapping("/questions/{id}")
    public ResponseEntity<?> deleteQuestion(@PathVariable(value = "id") Long questionId) {
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new ResourceNotFoundException("Question", "id", questionId));

        questionRepository.delete(question);

        return ResponseEntity.ok().build();
    }
}