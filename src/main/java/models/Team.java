package models;

import java.util.ArrayList;
import java.util.List;

public class Team {
    private String name;
    private String description;
    private List<String> teamMembers = new ArrayList<String>();


    private static ArrayList<Team> teamInstances = new ArrayList<>();

    public Team(String name, String description){
        this.name = name;
        this.description = description;
        teamInstances.add(this);

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
        return null;
    }
}
