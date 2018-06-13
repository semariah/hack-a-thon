package models;

import java.time.LocalDateTime;
import java.util.Objects;

public class Team {
    private String name;
    private String description;
    private LocalDateTime createdAt;
    private int id;



    public Team(String name, String description){
        this.name = name;
        this.description = description;
        this.createdAt = LocalDateTime.now();
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public static void clearAllTeams() {

    }

    public LocalDateTime getCreatedAt() {
        return createdAt;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return id == team.id &&
                Objects.equals(name, team.name) &&
                Objects.equals(description, team.description);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, description, id);
    }

}
