package dao;

import models.Team;

import java.util.List;

public interface TeamDao {

    List<Team> getAll();

    // CREATE
    void add(Team team);

    // READ
    Team findById(int id);

    // UPDATE
     void update(int id, String name, String description);

    // DELETE
     void deleteById(int id);
     void clearAllTeams();
}
