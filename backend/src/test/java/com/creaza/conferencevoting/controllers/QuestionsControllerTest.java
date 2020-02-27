package com.creaza.conferencevoting.controllers;

import com.creaza.conferencevoting.ConferenceVotingApplication;
import com.creaza.conferencevoting.model.Question;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = ConferenceVotingApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class QuestionsControllerTest {
    TestRestTemplate restTemplate = new TestRestTemplate();

    HttpHeaders headers = new HttpHeaders();
    @LocalServerPort
    private int port;

    private Question createdQuestion;

    @Before
    public void init() throws Exception {
        Question question = new Question("How much does the conference ticket cost?", "About");
        HttpEntity<Question> entity = new HttpEntity<Question>(question, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/api/v1/questions"),
                HttpMethod.POST, entity, String.class);
        ObjectMapper mapper = new ObjectMapper();
        createdQuestion = mapper.readValue(response.getBody(), Question.class);
    }

    @Test
    public void addQuestion() {

        Question question = new Question("What does the conference ticket include?", "About");

        HttpEntity<Question> entity = new HttpEntity<Question>(question, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/api/v1/questions"),
                HttpMethod.POST, entity, String.class);

        assertEquals(response.getStatusCodeValue(), 200);
        assertTrue(response.getBody().contains("id"));
        assertTrue(response.getBody().contains("title"));
        assertTrue(response.getBody().contains("category"));

    }
    @Test
    public void getQuestions() throws JSONException {

        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("api/v1/questions"),
                HttpMethod.GET, entity, String.class);

        assertEquals(response.getStatusCodeValue(), 200);
        assertNotNull(response.getBody());
        assertTrue(response.getBody().contains("id"));
        assertTrue(response.getBody().contains("title"));
        assertTrue(response.getBody().contains("category"));
    }

    @Test
    public void voteQuestion() {
        Question question = new Question("Course1", "Spring");
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("api/v1/questions/" + createdQuestion.getId() + "/vote"),
                HttpMethod.PUT, entity, String.class);

        assertEquals(response.getStatusCodeValue(), 200);
        assertNotNull(response.getBody());
        assertTrue(response.getBody().contains("id"));
        assertTrue(response.getBody().contains("title"));
        assertTrue(response.getBody().contains("category"));

    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }

}
