package webserver;

import data.Lecture;
import data.LectureFactory;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapperBuilder;
import helper.DayComparator;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Spark;
import spark.template.freemarker.FreeMarkerEngine;

import java.util.*;

public class WebServer {
    private LectureFactory factory;
    private List<Lecture> lecturesList = new ArrayList<>(0);
    private final Configuration config = new Configuration(Configuration.VERSION_2_3_26);
    private String url;
    private String semester;
    private String semesterText;
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

        Spark.get("/chart", (req, res) -> {
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
            semesterText = this.semester.endsWith("1") ? "SoSe" : "WiSe";
            semesterText = semesterText + " " + this.semester.substring(2,4);
            this.attributesChart.put("semester", semesterText);
            return new ModelAndView(this.attributesChart, "chart.ftl");
        }, new FreeMarkerEngine(this.config));

        Spark.get("/download", (Request req, Response res) -> {
            res.type("application/octet-stream");
            res.header("Content-Disposition", "attachment; filename=Wocheplan "+ this.semesterText +".ods");
            this.factory.createFileFromLectures(res.raw().getOutputStream(), true);
            //Files.copy(Paths.get("myfile.ods"), res.raw().getOutputStream());
            return res.raw();
        });
    }
}
