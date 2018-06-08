import dao.Sql2oTeamDao;
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

        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            List<Team> teams = teamDao.getAll();
            model.put("teams", teams);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        get("/teams/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "newteam-form.hbs");
        }, new HandlebarsTemplateEngine());

        post("/teams/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String name = req.queryParams("name");
            String description = req.queryParams("description");
            //String member1 = req.queryParams("member1");
            //String member2 = req.queryParams("member2");
            Team newTeam = new Team(name, description);
            teamDao.add(newTeam);
            //newTeam.addTeamMember(member1);
            //newTeam.addTeamMember(member2);
            res.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());


        get("/teams/:id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfTeamToFind = Integer.parseInt(req.params("id"));
            Team foundTeam = teamDao.findById(idOfTeamToFind);
            model.put("team", foundTeam);
            return new ModelAndView(model, "team-detail.hbs");
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
            //String member1 = req.queryParams("member1");
            //String member2 = req.queryParams("member2");
            int idOfTeamToEdit = Integer.parseInt(req.params("id"));
            teamDao.update(idOfTeamToEdit, newName, newDescription);
            //editTeam.addTeamMember(member1);
            //editTeam.addTeamMember(member2);
            res.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());



    }
}
