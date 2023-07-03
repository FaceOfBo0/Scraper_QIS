package webserver;

import data.LectureFactory;
import freemarker.template.Configuration;
import spark.ModelAndView;
import spark.Spark;
import spark.template.freemarker.FreeMarkerEngine;

import java.util.HashMap;
import java.util.Map;

public class WebServer {
    private LectureFactory factory;
    private final Configuration config = new Configuration(Configuration.VERSION_2_3_26);

    public WebServer(int pPort){
        Spark.port(pPort);
        this.config.setClassLoaderForTemplateLoading(ClassLoader.getSystemClassLoader(), "templates");
    }

    public void runRoutes(){
    Spark.get("/","text/html", (req, res) -> {
        Map<String, Object> attributes = new HashMap<>(0);
        return new ModelAndView(attributes, "root.ftl");
    }, new FreeMarkerEngine(this.config));
    }
}
