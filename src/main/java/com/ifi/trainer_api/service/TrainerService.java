package com.ifi.trainer_api.service;

import com.ifi.trainer_api.repository.Trainer;

public interface TrainerService {

    Iterable<Trainer> getAllTrainers();
    Trainer getTrainer(String name);
    Trainer createTrainer(Trainer trainer);
    Trainer replaceTrainer(String trainerName, Trainer trainer);
    void deleteTrainer(String name);
}