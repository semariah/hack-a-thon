
import java.util.*;

import models.Team;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import static spark.Spark.*;



public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");

        get("/teams/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "newteam-form.hbs");
        }, new HandlebarsTemplateEngine());

        post("/teams/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String name = request.queryParams("name");
            String description = request.queryParams("description");
            String member1 = request.queryParams("member1");
            String member2 = request.queryParams("member2");
            Team newTeam = new Team(name, description);
            newTeam.addTeamMember(member1);
            newTeam.addTeamMember(member2);
            model.put("team", newTeam);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());


        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            ArrayList<Team> teams = Team.getTeamInstances();
            model.put("teams", teams);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());



        get("/teams/:id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfTeamToFind = Integer.parseInt(req.params("id"));
            Team foundTeam = Team.findById(idOfTeamToFind);
            model.put("team", foundTeam);
            return new ModelAndView(model, "team-detail.hbs");
        }, new HandlebarsTemplateEngine());

        get("/teams/:id/update", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfTeamToEdit = Integer.parseInt(req.params("id"));
            Team editTeam = Team.findById(idOfTeamToEdit);
            model.put("editTeam", editTeam);
            return new ModelAndView(model, "newteam-form.hbs");
        }, new HandlebarsTemplateEngine());

        post("/teams/:id/update", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String newName = req.queryParams("name");
            String newDescription = req.queryParams("description");
            String member1 = req.queryParams("member1");
            String member2 = req.queryParams("member2");
            int idOfTeamToEdit = Integer.parseInt(req.params("id"));
            Team editTeam = Team.findById(idOfTeamToEdit);
            editTeam.update(newName, newDescription);
            editTeam.addTeamMember(member1);
            editTeam.addTeamMember(member2);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        get("/teams/:id/delete", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfTeamToDelete = Integer.parseInt(req.params("id"));
            Team deleteTeam = Team.findById(idOfTeamToDelete);
            deleteTeam.deleteTeam();
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());



    }
}
