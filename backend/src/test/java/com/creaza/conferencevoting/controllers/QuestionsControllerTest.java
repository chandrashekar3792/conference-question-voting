package com.creaza.conferencevoting.controllers;

import com.creaza.conferencevoting.ConferenceVotingApplication;
import com.creaza.conferencevoting.model.dao.Question;
import com.creaza.conferencevoting.model.dto.ChoiceDto;
import com.creaza.conferencevoting.model.dto.QuestionCreationDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ConferenceVotingApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class QuestionsControllerTest {
    TestRestTemplate restTemplate = new TestRestTemplate();

    HttpHeaders headers = new HttpHeaders();
    @LocalServerPort
    private int port;

    @Before
    public void init() throws Exception {
        createNewQuestion();
    }

    @Test
    public void addQuestion() {
        ChoiceDto choice = new ChoiceDto("The Batman");
        QuestionCreationDto question = new QuestionCreationDto("Who is the best superhero?", "Superhero", Collections.singletonList(choice));

        HttpEntity<QuestionCreationDto> entity = new HttpEntity<>(question, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/api/v1/questions"),
                HttpMethod.POST, entity, String.class);

        assertEquals(201, response.getStatusCodeValue());
        assertTrue(response.getBody().contains("id"));
        assertTrue(response.getBody().contains("title"));
        assertTrue(response.getBody().contains("category"));

    }

    @Test
    public void getQuestions() {

        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/api/v1/questions"),
                HttpMethod.GET, entity, String.class);

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().contains("id"));
        assertTrue(response.getBody().contains("title"));
        assertTrue(response.getBody().contains("category"));
    }

    @Test
    public void voteQuestion() throws Exception {
        Question newQuestion = createNewQuestion();
        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/api/v1/questions/" + newQuestion.getId() + "/vote?choiceId=" + newQuestion.getId()),
                HttpMethod.PUT, entity, String.class);

        assertEquals(200, response.getStatusCodeValue());
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }

    private Question createNewQuestion () throws Exception {
        ChoiceDto choice = new ChoiceDto("MS Dhoni");
        QuestionCreationDto question = new QuestionCreationDto("Who is the best cricketer?", "Cricket", Collections.singletonList(choice));
        HttpEntity<QuestionCreationDto> entity = new HttpEntity<>(question, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/api/v1/questions"),
                HttpMethod.POST, entity, String.class);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(response.getBody(), Question.class);
    }
}
