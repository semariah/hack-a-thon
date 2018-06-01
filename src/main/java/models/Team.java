package models;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Team {
    private String name;
    private String description;
    private static ArrayList<String> members = new ArrayList<String>();

    public Team(String name, String description, String memberToAdd){
        this.name = name;
        this.description = description;
        this.members.add(memberToAdd);
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public static ArrayList<String> getAll() {
        return null;
    }
}
