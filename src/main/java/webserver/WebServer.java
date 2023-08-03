package webserver;

import data.Lecture;
import data.LectureFactory;
import freemarker.template.Configuration;
import helper.DayComparator;
import spark.ModelAndView;
import spark.Spark;
import spark.template.freemarker.FreeMarkerEngine;

import java.security.InvalidParameterException;
import java.util.*;

public class WebServer {
    private LectureFactory factory;
    private List<Lecture> lecturesList = new ArrayList<>(0);
    private final Configuration config = new Configuration(Configuration.VERSION_2_3_26);

    public WebServer(int pPort){
        Spark.port(pPort);
        this.config.setClassLoaderForTemplateLoading(ClassLoader.getSystemClassLoader(), "templates");
    }

    public void runRoutes() {
        Spark.get("/", (req, res) -> {
            return new ModelAndView(null, "root.ftl");
        }, new FreeMarkerEngine(this.config));

        Spark.post("/submit", (req, res) -> {
            System.out.println(req.queryParams("url"));
            System.out.println(req.queryParams("semester"));
            return new ModelAndView(null, "chart.ftl");
        }, new FreeMarkerEngine(this.config));
    }
//        Spark.get("/","text/html", (req, res) -> {
//            String load = req.queryParams().contains("load") ? req.queryParams("load") : "";
//            String url = req.queryParams().contains("url") ? req.queryParams("url").replace("<amp>","&") : "";
//            String semester = req.queryParams().contains("semester") ? req.queryParams("semester") : "";
//            Map<String, Object> attributes = new HashMap<>(0);
//
//            if (Objects.equals(load, "1")) {
//                if (!Objects.equals(url, "")) {
//                    if (this.lecturesList.isEmpty()) {
//                        if (!Objects.equals(semester, ""))
//                            this.factory = new LectureFactory(url, semester);
//                        else this.factory = new LectureFactory(url);
//                        this.lecturesList = this.factory.getLectures();
//                        this.lecturesList.sort(new DayComparator());
//                        attributes.put("lecturesList", this.lecturesList);
//                        System.out.println("Lectures successfully loaded!");
//                    }
//                    else System.out.println("Lectures already loaded!");
//                }
//                else throw new InvalidParameterException("'url' or 'semester' not found!");
//            }
//            if (!this.lecturesList.isEmpty())
//                attributes.put("loaded", "1");
//            else attributes.put("loaded", "0");
//            return new ModelAndView(attributes, "root.ftl");
//        }, new FreeMarkerEngine(this.config));
}
