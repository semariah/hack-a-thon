
import java.util.HashMap;
import java.util.Map;

import models.Team;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import static spark.Spark.*;



public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");

        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        post("/teams/new", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            String name = request.queryParams("name");
            String description = request.queryParams("description");
            String memberToAdd = request.queryParams("memberToAdd");
            Team newTeam = new Team(name, description, memberToAdd);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());
    }
}
