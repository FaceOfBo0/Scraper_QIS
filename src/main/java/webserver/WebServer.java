package webserver;

import data.Lecture;
import data.LectureFactory;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapperBuilder;
import helper.DayComparator;
import spark.ModelAndView;
import spark.Spark;
import spark.template.freemarker.FreeMarkerEngine;

import java.util.*;

public class WebServer {
    private LectureFactory factory;
    private List<Lecture> lecturesList = new ArrayList<>(0);
    private final Configuration config = new Configuration(Configuration.VERSION_2_3_26);
    private String url;
    private String semester;
    private Map<String, Object> attributesChart = new HashMap<>(0);

    public WebServer(int pPort){
        Spark.port(pPort);
        DefaultObjectWrapperBuilder dfob = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_26);
        dfob.setIterableSupport(true);
        this.config.setClassLoaderForTemplateLoading(ClassLoader.getSystemClassLoader(), "templates");
        this.config.setObjectWrapper(dfob.build());
    }

    public void runRoutes() {
        Spark.get("/", (req, res) -> new ModelAndView(null, "root.ftl"),
                new FreeMarkerEngine(this.config));

        Spark.post("/chart", (req, res) -> {
            String tempUrl = req.queryParams("url");
            String tempSemester = req.queryParams("semester");

            if (!Objects.equals(tempUrl, this.url) || !Objects.equals(tempSemester, this.semester)) {
                if (!Objects.equals(semester, ""))
                    this.factory = new LectureFactory(tempUrl, tempSemester);
                else this.factory = new LectureFactory(tempUrl);
                this.lecturesList = this.factory.getLectures();
                this.lecturesList.sort(new DayComparator());
                this.attributesChart.clear();
                this.attributesChart.put("lecturesList", this.lecturesList);
                this.url = tempUrl;
                this.semester = tempSemester;
                System.out.println("Lectures successfully loaded!");
            }
            else System.out.println("Lectures already loaded!");

            this.url = req.queryParams("url");
            this.semester = req.queryParams("semester");
            return new ModelAndView(this.attributesChart, "chart.ftl");
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
