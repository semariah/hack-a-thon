import dao.Sql2oMemberDao;
import dao.Sql2oTeamDao;
import models.Member;
import models.Team;
import org.sql2o.Sql2o;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;


public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");
        String connectionString = "jdbc:h2:~/hack-a-thon.db;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        Sql2oTeamDao teamDao = new Sql2oTeamDao(sql2o);
        Sql2oMemberDao memberDao = new Sql2oMemberDao(sql2o);

        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            List<Team> teams = teamDao.getAll();
            model.put("teams", teams);
            List<Member> members = memberDao.getAll();
            model.put("members", members);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());


        get("/teams/delete", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            teamDao.clearAllTeams();
            res.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());

        get("/teams/:id/delete", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfTeamToDelete = Integer.parseInt(req.params("id"));
            teamDao.deleteById(idOfTeamToDelete);
            res.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());


        get("/teams/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "newteam-form.hbs");
        }, new HandlebarsTemplateEngine());

        post("/teams/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String name = req.queryParams("name");
            String description = req.queryParams("description");
            Team newTeam = new Team(name, description);
            teamDao.add(newTeam);
            model.put("team", newTeam);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());


        post("/teams/:id/members/add", (req, res) -> {
            int memberTeamId = Integer.parseInt(req.params("id"));
            String newMember1 = req.queryParams("member1");
            String newMember2 = req.queryParams("member2");
            Member member = new Member(newMember1, memberTeamId);
            Member otherMember = new Member(newMember2, memberTeamId);
            memberDao.add(member);
            memberDao.add(otherMember);
            res.redirect("/teams/" + memberTeamId);
            return null;
        }, new HandlebarsTemplateEngine());


        get("/teams/:teamId/members/:id/update", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int teamId = Integer.parseInt(req.params("teamId"));
            int memberId = Integer.parseInt(req.params("id"));
            Team team = teamDao.findById(teamId);
            Member member = memberDao.findById(memberId);
            model.put("team", team);
            model.put("member", member);
            return new ModelAndView (model, "member-form.hbs");
        }, new HandlebarsTemplateEngine());


        get("/members/delete", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            memberDao.clearAllMembers();
            res.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());


        get("/teams/:teamId/members/:id/delete", (request, response) -> {
            int teamId = Integer.parseInt(request.params("teamId"));
            int memberId = Integer.parseInt(request.params("id"));
            memberDao.deleteById(memberId);
            response.redirect("/teams/" + teamId);
            return null;
        }, new HandlebarsTemplateEngine());


        post("/teams/:teamId/members/:memberId/update", (req, res) -> {
            int teamId = Integer.parseInt(req.params("teamId"));
            int memberId = Integer.parseInt(req.params("memberId"));
            String member = req.queryParams("member");
            memberDao.update(memberId, member, teamId);
            res.redirect("/teams/" + teamId );
            return null;
        }, new HandlebarsTemplateEngine());


        get("/teams/:id/update", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfTeamToEdit = Integer.parseInt(req.params("id"));
            Team editTeam = teamDao.findById(idOfTeamToEdit);
            model.put("editTeam", editTeam);
            return new ModelAndView(model, "newteam-form.hbs");
        }, new HandlebarsTemplateEngine());


        post("/teams/:id/update", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String newName = req.queryParams("name");
            String newDescription = req.queryParams("description");
            int idOfTeamToEdit = Integer.parseInt(req.params("id"));
            teamDao.update(idOfTeamToEdit, newName, newDescription);
            res.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());


        get("/teams/:id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfTeamToFind = Integer.parseInt(req.params("id"));
            Team foundTeam = teamDao.findById(idOfTeamToFind);
            model.put("team", foundTeam);
            List<Member> members = teamDao.getAllMembersByTeamId(idOfTeamToFind);
            model.put("members", members);
            return new ModelAndView(model, "team-detail.hbs");
        }, new HandlebarsTemplateEngine());

    }
}
