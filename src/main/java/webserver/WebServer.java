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

    public void runRoutes(){
        Spark.get("/","text/html", (req, res) -> {
            String load = req.queryParams().contains("load") ? req.queryParams("load") : "";
            String url = req.queryParams().contains("url") ? req.queryParams("url").replace("<amp>","&") : "";
            String semester = req.queryParams().contains("semester") ? req.queryParams("semester") : "";
            Map<String, Object> attributes = new HashMap<>(0);

            if (Objects.equals(load, "1")) {
                if (!Objects.equals(url, "") && !Objects.equals(semester, "")) {
                    if (this.lecturesList.size()==0) {
                        this.factory = new LectureFactory(url, semester);
                        this.lecturesList = this.factory.getLectures();
                        this.lecturesList.sort(new DayComparator());
                        attributes.put("lectures", this.lecturesList);
                        System.out.println("Lectueres successfully loaded!");
                    }
                    else System.out.println("Lectures already loaded!");
                }
                else throw new InvalidParameterException("''url' or 'semester' not found!");
            }
            if (this.lecturesList.size()!=0)
                attributes.put("loaded","1");
            else attributes.put("loaded","0");
            return new ModelAndView(attributes, "root.ftl");
        }, new FreeMarkerEngine(this.config));
    }
}
