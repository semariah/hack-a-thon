package dao;

import models.Member;
import models.Team;

import java.util.List;

public interface TeamDao {

    //Retrieve all members
    List<Member> getAllMembersByTeamId(int teamId);

    // Add
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
