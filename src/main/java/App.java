import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App {
    public static void main(String[] args) {

        staticFileLocation("/public");
        String layout = "templates/layout.vtl";     

        get("/main", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("template", "templates/main.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

    }
}