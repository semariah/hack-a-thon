package models;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Team {
    private String name;
    private String description;
    private static List<String> members = new ArrayList<String>();

    public Team(String name, String description, ArrayList<String> membersToAdd){
        this.name = name;
        this.description = description;
        this.members.addAll(membersToAdd);
    }
}
