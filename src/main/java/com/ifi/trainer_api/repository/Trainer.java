package com.ifi.trainer_api.repository;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

@Entity
public class Trainer {
    @Id
    private String name;

    @ElementCollection
    private List<Pokemon> team;

    @Column
    private String password;

    public Trainer() {
    }

    public Trainer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Pokemon> getTeam() {
        return team;
    }

    public void setTeam(List<Pokemon> team) {
        this.team = team;
    }

    public Trainer orElse(Object o) {
        if (o instanceof  Trainer) return ((Trainer) o);
        return null;
    }

    public void replaceTrainer(Trainer trainer) {
        this.name = trainer.getName();
        this.team = trainer.getTeam();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
