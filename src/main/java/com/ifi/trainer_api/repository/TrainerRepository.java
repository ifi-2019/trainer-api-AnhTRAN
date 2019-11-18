package com.ifi.trainer_api.repository;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface TrainerRepository extends CrudRepository<Trainer, String> {
    List<Trainer> findAll();
    Optional<Trainer> findById(String name);
    Trainer save(Trainer trainer);
}
