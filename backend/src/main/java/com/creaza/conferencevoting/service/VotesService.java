package com.creaza.conferencevoting.service;

import com.creaza.conferencevoting.exception.IncorrectChoiceException;
import com.creaza.conferencevoting.model.dao.Choice;
import com.creaza.conferencevoting.model.dao.Question;
import com.creaza.conferencevoting.model.dao.User;
import com.creaza.conferencevoting.model.dao.Vote;
import com.creaza.conferencevoting.model.dto.VoteDto;
import com.creaza.conferencevoting.repository.VoteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VotesService {

    private static final Logger log = LoggerFactory.getLogger(VotesService.class);

    private VoteRepository voteRepository;
    private QuestionsService questionsService;
    private ChoicesService choicesService;
    private UsersService usersService;

    @Autowired
    public VotesService(VoteRepository voteRepository, QuestionsService questionsService, ChoicesService choicesService, UsersService usersService) {
        this.voteRepository = voteRepository;
        this.questionsService = questionsService;
        this.choicesService = choicesService;
        this.usersService = usersService;
    }

    public void voteForQuestion(Long questionId, Long userId, Long choiceId) {
        log.info("user - {} voting for a question - {} with choice - {}", userId, questionId, choiceId);
        Question question = questionsService.getQuestionById(questionId);
        User user = usersService.getUserById(userId);
        Choice choice = choicesService.getChoiceById(choiceId);
        if (choice.getQuestion().getId() == questionId) {
            voteRepository.save(new Vote(user, choice));
        } else {
            throw new IncorrectChoiceException(questionId, choiceId);
        }
    }

    public List<VoteDto> fetchVotes() {
        log.info("fetching all the votes");
        return voteRepository.fetchVotes();
    }
}
