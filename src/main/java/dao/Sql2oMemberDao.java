package dao;

import models.Member;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oMemberDao implements MemberDao{
    private final Sql2o sql2o;

    public Sql2oMemberDao(Sql2o sql2o){
        this.sql2o = sql2o;
    }

    @Override
    public void add(Member member) {
        String sql = "INSERT INTO members (name, teamId) VALUES (:name, :teamId)";
        try (Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql, true)
                    .bind(member)
                    .executeUpdate()
                    .getKey();
            member.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }

    }

    @Override
    public List<Member> getAll() {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM members")
                    .executeAndFetch(Member.class);
        }
    }

    @Override
    public Member findById(int id) {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM members WHERE id = :id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(Member.class);
        }
    }

    @Override
    public void update(int id, String name) {
        String sql = "UPDATE members SET (name) = (:name) WHERE id=:id";
        try(Connection con = sql2o.open()){
            con.createQuery(sql)
                    .addParameter("name", name)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }

    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE from members WHERE id=:id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }

    }

    @Override
    public void clearAllMembers() {
        String sql = "DELETE from members";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    }

