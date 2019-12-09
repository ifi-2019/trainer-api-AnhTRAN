package com.ifi.trainer_api.controller;

import com.ifi.trainer_api.repository.Trainer;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TrainerControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private TrainerController controller;

    @Value("${spring.security.user.name}")
    private String username;

    @Value("${spring.security.user.password}")
    private String password;

    @Test
    void trainerController_shouldBeInstanciated(){
        assertNotNull(controller);
    }

    @Test
    void getTrainers_shouldThrowAnUnauthorized(){
        var responseEntity = this.restTemplate
                .getForEntity("http://localhost:" + port + "/trainers/Ash", Trainer.class);
        assertNotNull(responseEntity);
        assertEquals(401, responseEntity.getStatusCodeValue());
    }

    @Test
    void getTrainer_withNameAsh_shouldReturnAsh() {
        var ash = this.restTemplate
                .withBasicAuth(username, password)
                .getForObject("http://localhost:" + port + "/trainers/Ash", Trainer.class);
        assertNotNull(ash);
        assertEquals("Ash", ash.getName());
        assertEquals(1, ash.getTeam().size());

        assertEquals(25, ash.getTeam().get(0).getPokemonType());
        assertEquals(18, ash.getTeam().get(0).getLevel());
    }

    @Test
    void getAllTrainers_shouldReturnAshAndMisty() {
        var trainers = this.restTemplate.withBasicAuth(username, password).getForObject("http://localhost:" + port + "/trainers/", Trainer[].class);
        assertNotNull(trainers);

        var bugCatcher = this.restTemplate.withBasicAuth(username, password).getForObject("http://localhost:" + port + "/trainers/Bug Catcher", Trainer.class);
        if (bugCatcher != null) {
            assertEquals(3, trainers.length);
            assertEquals("Ash", trainers[0].getName());
            assertEquals("Bug Catcher", trainers[1].getName());
            assertEquals("Misty", trainers[2].getName());
        }
        else {
            assertEquals(2, trainers.length);
            assertEquals("Ash", trainers[0].getName());
            assertEquals("Misty", trainers[1].getName());
        }


    }

    @Test
    void createTrainer_shouldReturnBugCatcher() {
        var oldBugCatcher = this.restTemplate.withBasicAuth(username, password).getForObject("http://localhost:" + port + "/trainers/Bug Catcher", Trainer.class);
        assertNull(oldBugCatcher);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String requestJson = "{\"name\": \"Bug Catcher\",\"team\": [{\"pokemonTypeId\": 13, \"level\": 6},{\"pokemonTypeId\": 10, \"level\": 6}]}";
        HttpEntity<String> entity = new HttpEntity<String>(requestJson, headers);
        var newTrainer = this.restTemplate.withBasicAuth(username, password).postForObject("http://localhost:" + port + "/trainers/", entity, Trainer.class);

        var bugCatcher = this.restTemplate.withBasicAuth(username, password).getForObject("http://localhost:" + port + "/trainers/Bug Catcher", Trainer.class);
        assertNotNull(bugCatcher);
        assertEquals("Bug Catcher", bugCatcher.getName());
        assertEquals(newTrainer.getName(), bugCatcher.getName());
        assertEquals(newTrainer.getTeam().size(), bugCatcher.getTeam().size());
        int i = 0;
        while (i < bugCatcher.getTeam().size()) {
            assertEquals(newTrainer.getTeam().get(i).getPokemonType(), bugCatcher.getTeam().get(i).getPokemonType());
            assertEquals(newTrainer.getTeam().get(i).getLevel(), bugCatcher.getTeam().get(i).getLevel());
            i++;
        }
    }

    @Test
    void replaceTrainer_shouldReplaceBugCatcher() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String requestJson = "{\"name\": \"Bug Catcher\",\"team\": [{\"pokemonTypeId\": 13, \"level\": 6},{\"pokemonTypeId\": 10, \"level\": 6}]}";
        HttpEntity<String> entity = new HttpEntity<String>(requestJson, headers);
        this.restTemplate.withBasicAuth(username, password).postForObject("http://localhost:" + port + "/trainers/", entity, Trainer.class);

        var oldBugCatcher = this.restTemplate.withBasicAuth(username, password).getForObject("http://localhost:" + port + "/trainers/Bug Catcher", Trainer.class);
        assertNotNull(oldBugCatcher);
        assertEquals("Bug Catcher", oldBugCatcher.getName());
        assertEquals(2, oldBugCatcher.getTeam().size());
        assertEquals(6, oldBugCatcher.getTeam().get(0).getLevel());
        assertEquals(6, oldBugCatcher.getTeam().get(1).getLevel());

        String requestJson2 = "{\"name\": \"Bug Catcher\",\"team\": [{\"pokemonTypeId\": 13, \"level\": 7},{\"pokemonTypeId\": 10, \"level\": 8}]}";
        HttpEntity<String> entity2 = new HttpEntity<String>(requestJson2, headers);
        this.restTemplate.withBasicAuth(username, password).put("http://localhost:" + port + "/trainers/Bug Catcher", entity2, Trainer.class);

        var newBugCatcher = this.restTemplate.withBasicAuth(username, password).getForObject("http://localhost:" + port + "/trainers/Bug Catcher", Trainer.class);
        assertNotNull(newBugCatcher);
        assertEquals("Bug Catcher", newBugCatcher.getName());
        assertEquals(2, newBugCatcher.getTeam().size());
        assertEquals(7, newBugCatcher.getTeam().get(0).getLevel());
        assertEquals(8, newBugCatcher.getTeam().get(1).getLevel());
    }

    @Test
    void deleteTrainer_shouldReturnBugCatcher() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String requestJson = "{\"name\": \"Bug Catcher\",\"team\": [{\"pokemonTypeId\": 13, \"level\": 6},{\"pokemonTypeId\": 10, \"level\": 6}]}";
        HttpEntity<String> entity = new HttpEntity<String>(requestJson, headers);
        this.restTemplate.withBasicAuth(username, password).postForObject("http://localhost:" + port + "/trainers/", entity, Trainer.class);

        var oldBugCatcher = this.restTemplate.withBasicAuth(username, password).getForObject("http://localhost:" + port + "/trainers/Bug Catcher", Trainer.class);
        assertNotNull(oldBugCatcher);
        assertEquals("Bug Catcher", oldBugCatcher.getName());

        this.restTemplate.withBasicAuth(username, password).delete("http://localhost:" + port + "/trainers/Bug Catcher", Trainer.class);

        var bugCatcher = this.restTemplate.withBasicAuth(username, password).getForObject("http://localhost:" + port + "/trainers/Bug Catcher", Trainer.class);
        assertNull(bugCatcher);
    }
}