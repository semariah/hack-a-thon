package models;

import java.util.Objects;

public class Member {
    private String name;
    private int id;
    private int teamId;

    public Member(String name, int teamId) {
        this.name = name;
        this.teamId = teamId;
    }

    public String getName() {
        return name;
    }



    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Member member = (Member) o;
        return id == member.id &&
                teamId == member.teamId &&
                Objects.equals(name, member.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, id, teamId);
    }
}
