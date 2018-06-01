package models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Team {
    private boolean published;
    private String name;
    private String description;
    private LocalDateTime createdAt;
    private int id;
    private List<String> teamMembers = new ArrayList<String>();


    private static ArrayList<Team> teamInstances = new ArrayList<>();

    public Team(String name, String description){
        this.name = name;
        this.description = description;
        this.published = false;
        this.createdAt = LocalDateTime.now();
        teamInstances.add(this);
        this.id = teamInstances.size();

    }

    public static Team findById(int id) {
        return teamInstances.get(id-1);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }


    public void setDescription() {
        this.description = description;
    }

    public void setTeamMembers(List<String> teamMembers) {
        this.teamMembers = teamMembers;
    }

    public List<String> getTeamMembers() {
        return teamMembers;
    }

    public static void setTeamInstances(ArrayList<Team> teamInstances){
        Team.teamInstances = teamInstances;
    }


    public static void clearAllTeams(){
        teamInstances.clear();
    }

    public static ArrayList<Team> getTeamInstances() {
        return teamInstances;
    }

    public boolean getPublished() {
        return this.published;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public int getId() {
        return id;
    }
}
