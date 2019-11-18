package com.ifi.trainer_api.service;

import com.ifi.trainer_api.repository.Trainer;
import com.ifi.trainer_api.repository.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrainerServiceImpl implements TrainerService {

    @Autowired
    private TrainerRepository trainerRepository;

    public TrainerServiceImpl(TrainerRepository trainerRepository) {
        this.trainerRepository = trainerRepository;
    }

    @Override
    public Iterable<Trainer> getAllTrainers() {
        return trainerRepository.findAll();
    }

    @Override
    public Trainer getTrainer(String name) {
        return trainerRepository.findById(name).orElse(null);
    }

    @Override
    public Trainer createTrainer(Trainer trainer) {
        return trainerRepository.save(trainer);
    }

    @Override
    public Trainer replaceTrainer(String trainerName, Trainer trainer) {
        Trainer oldTrainer = trainerRepository.findById(trainerName).orElse(null);
        if (oldTrainer != null) {
            oldTrainer.replaceTrainer(trainer);
            return trainerRepository.save(oldTrainer);
        }
        return null;
    }

    @Override
    public void deleteTrainer(String name) {
        trainerRepository.deleteById(name);
    }
}