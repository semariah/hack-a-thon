package dao;

import models.Member;

import java.util.List;

public interface MemberDao {

    List<Member> getAll();

    // CREATE
    void add(Member member);

    // READ
    Member findById(int id);

    // UPDATE
    void update(int id, String name, int teamId);

    // DELETE
    void deleteById(int id);
    void clearAllMembers();
}
