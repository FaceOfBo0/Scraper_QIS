package webserver;

import data.LectureFactory;
import freemarker.template.Configuration;
import spark.Spark;

import java.io.File;
import java.io.IOException;

public class WebServer {
    private LectureFactory factory;
    private static final Configuration config = new Configuration(Configuration.VERSION_2_3_26);

    public WebServer(int pPort){
        Spark.port(pPort);
        Spark.staticFileLocation("templates/javascript");
        Spark.externalStaticFileLocation("templates/javascript");
        Spark.staticFiles.location("templates/javascript");
        Spark.staticFiles.externalLocation("templates/javascript");
        try {
            config.setDirectoryForTemplateLoading(new File("templates/"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void runRoutes(){

    }
}
