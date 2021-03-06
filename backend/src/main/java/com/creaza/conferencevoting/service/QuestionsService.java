package com.creaza.conferencevoting.service;

import com.creaza.conferencevoting.exception.ResourceNotFoundException;
import com.creaza.conferencevoting.model.dao.Choice;
import com.creaza.conferencevoting.model.dao.Question;
import com.creaza.conferencevoting.model.dto.QuestionCreationDto;
import com.creaza.conferencevoting.model.dto.QuestionUpdationDto;
import com.creaza.conferencevoting.repository.QuestionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class QuestionsService {

    private static final Logger log = LoggerFactory.getLogger(QuestionsService.class);

    private QuestionRepository questionRepository;
    private ChoicesService choicesService;

    @Autowired
    public QuestionsService(QuestionRepository questionRepository, ChoicesService choicesService) {
        this.questionRepository = questionRepository;
        this.choicesService = choicesService;
    }

    public List<Question> getAllQuestions() {
        log.info("fetching a list of all questions");
        return questionRepository.findAll();
    }

    public Question createQuestion(QuestionCreationDto question) {
        log.info("creating a new question - {}", question);

        Question created = questionRepository.save(new Question(question.getTitle(), question.getCategory()));

        question.getChoices().forEach(choice -> {
            created.addChoice(choicesService.createChoice(new Choice(choice.getStatement(), created)));
        });

        return questionRepository.save(created);
    }

    public Question getQuestionById(Long id) {
        log.info("fetching question by id - {}", id);
        return questionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Question", "id", id));
    }

    public Question updateQuestion(QuestionUpdationDto updatedQuestion) {
        Question existing = getQuestionById(updatedQuestion.getId());
        if (StringUtils.hasLength(updatedQuestion.getTitle())) {
            existing.setTitle(updatedQuestion.getTitle());
        }
        if (StringUtils.hasLength(updatedQuestion.getCategory())) {
            existing.setCategory(updatedQuestion.getCategory());
        }
        if (updatedQuestion.getChoices().size() > 0) {
            updatedQuestion.getChoices().forEach(choice -> {
                existing.getChoices().add(choicesService.createChoice(new Choice(choice.getStatement(), existing)));
                questionRepository.save(existing);
            });
        }

        return questionRepository.save(existing);
    }

    public void deleteQuestion(Long id) {
        log.info("deleting question with id - {}", id);
        Question question = getQuestionById(id);
        questionRepository.delete(question);
    }
}
