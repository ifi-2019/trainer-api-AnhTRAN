package com.ifi.trainer_api.service;

import com.ifi.trainer_api.repository.Trainer;
import com.ifi.trainer_api.repository.TrainerRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class TrainerServiceImplTest {
    @Test
    void getAllTrainers_shouldCallTheRepository() {
        var trainerRepo = Mockito.mock(TrainerRepository.class);
        var trainerService = new TrainerServiceImpl(trainerRepo);

        trainerService.getAllTrainers();

        // Verify if the method findAll has been called by trainerRepo
        Mockito.verify(trainerRepo).findAll();
    }

    @Test
    void getTrainer_shouldCallTheRepository() {
        var trainerRepo = Mockito.mock(TrainerRepository.class);
        var trainerService = new TrainerServiceImpl(trainerRepo);

        trainerService.getTrainer("Ash");

        Mockito.verify(trainerRepo).findById("Ash");
    }

    @Test
    void createTrainer_shouldCallTheRepository() {
        var trainerRepo = Mockito.mock(TrainerRepository.class);
        var trainerService = new TrainerServiceImpl(trainerRepo);

        var ash = new Trainer();
        trainerService.createTrainer(ash);

        Mockito.verify(trainerRepo).save(ash);
    }
}
