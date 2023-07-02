import data.LectureFactory;
import webserver.WebServer;

public class MainProgram {

    public static void main(String[] args) {
//        LectureFactory lecFac = new LectureFactory("https://qis.server.uni-frankfurt.de/qisserver/rds?state=verpublish&publishContainer=lectureInstList&publishid=80100","2023.1");
//        lecFac.createODSFileFromLectures("results.ods",true);
        WebServer ws = new WebServer(4567);
        ws.runRoutes();
    }
}


//List<String> lecturersLinks = lecFac.getQisParser().getLecturesLinks();
//String lecText = lecFac.getQisParser().getOneLectureText(lecturersLinks.get(2));
//System.out.println(lecText);