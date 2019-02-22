import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
        ProcessBuilder process = new ProcessBuilder();
        Integer port;
        if (process.environment().get("PORT") != null) {
            port = Integer.parseInt(process.environment().get("PORT"));
        } else {
            port = 4567;
        }
  
        setPort(port);

        staticFileLocation("/public");
        String layout = "templates/layout.vtl";
             
        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("template", "templates/index.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        get("/main", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("template", "templates/main.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        get("/donor", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("template", "templates/donor-form.vtl");
            return new ModelAndView(model, layout);
          }, new VelocityTemplateEngine());

          get("/recepient", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("template", "templates/recepient-form.vtl");
            return new ModelAndView(model, layout);
          }, new VelocityTemplateEngine());

          post("/donors", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            String name = request.queryParams("name");
            String email = request.queryParams("email");
            String condition = request.queryParams("condition");
            String age = request.queryParams("age");
            String bgroup = request.queryParams("bgroup");
            Donor newDonor = new Donor (name,email,condition,age,bgroup);
            newDonor.save();
            model.put("template", "templates/donor-success.vtl");
            return new ModelAndView(model, layout);
          }, new VelocityTemplateEngine());

          get("/donors", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("donor", Donor.all());
            model.put("template", "templates/donor.vtl");
            return new ModelAndView(model, layout);
          }, new VelocityTemplateEngine());

          post("/recipients", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            String name = request.queryParams("name");
            String email = request.queryParams("email");
            String condition = request.queryParams("condition");
            String age = request.queryParams("age");
            String bgroup = request.queryParams("bgroup");
            Recipient newRecipient = new Recipient (name,email,condition,age,bgroup);
            newRecipient.save();
            model.put("template", "templates/recipient-success.vtl");
            return new ModelAndView(model, layout);
          }, new VelocityTemplateEngine());

          get("/recipients", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("recipient", Recipient.all());
            model.put("template", "templates/recipient.vtl");
            return new ModelAndView(model, layout);
          }, new VelocityTemplateEngine());

          get("/bookSuccess", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("template", "templates/bookSuccess.vtl");
            return new ModelAndView(model, layout);
          }, new VelocityTemplateEngine());
        

    }
}