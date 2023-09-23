import helper.OlatApiCall;
import helper.OlatApiConnector;
import org.jsoup.Connection;
import webserver.WebServer;
import data.LectureFactory;

import java.io.IOException;
import java.util.Map;

public class MainProgram {

    public static void main(String[] args) {
        LectureFactory lecFac = new LectureFactory("https://qis.server.uni-frankfurt.de/qisserver/rds?state=verpublish&publishContainer=lectureInstList&publishid=80100");
        lecFac.createODSFileFromLectures("QIS23.24.ods",true);
//        WebServer ws = new WebServer(4567);
//        ws.runRoutes();
//        String apiBaseUrl = "https://olat-ce.server.uni-frankfurt.de/olat/restapi";
//        OlatApiConnector olatClient = new OlatApiConnector(apiBaseUrl, "tim.koenig", "!Cw25.9!");
//        try {
//            olatClient.connect();
//            String xOlatToken = olatClient.getResponse().headers().get("X-OLAT-TOKEN");
//
//            OlatApiCall courseCreate = new OlatApiCall(apiBaseUrl+"/repo/entries/search?myentries=true", Connection.Method.GET, xOlatToken);
//            System.out.println(courseCreate.getStatusCode());
//            System.out.println(courseCreate.getResponseBody());
//            System.out.println();
//            //courseCreate.getResponse().headers().forEach((key, value) -> System.out.println(key+": "+value));
//
////            OlatApiCall usersCall = new OlatApiCall(apiBaseUrl+"/users/"+identityKey,
////                    Connection.Method.GET, xOlatToken);
////            System.out.println(usersCall.getStatusCode());
////            System.out.println(usersCall.getResponseBody());
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

    }
}

//        List<String> lecturesLinks = lecFac.getQisParser().getLecturesLinks();
//        String lecText2 = lecFac.getQisParser().getOneLectureText(lecturesLinks.get(1));
//        String lecText1 = lecFac.getQisParser().getOneLectureText(lecturesLinks.get(0));
//        System.out.println(lecText1);
//        System.out.println(lecText2);
//        Lecture lec2 = new Lecture_Text_Impl(lecText2, lecturesLinks.get(1));
//        Lecture lec1 = new Lecture_Text_Impl(lecText1, lecturesLinks.get(0));
//        System.out.println("------");
//        System.out.println(lec1.getTitle());
//        System.out.println(lec1.getLecturersList());
//        System.out.println(lec1.getDay());
//        System.out.println(lec1.getTime());
//        System.out.println(lec1.getRoom());
//        System.out.println(lec1.getModulesSet());
//        System.out.println("lec 1 commentary: "+lec1.getCommentary());
//        System.out.println("lec 1 flags: "+lec1.getFlags());
//        System.out.println("------");
//        System.out.println("lec 2 commentary: "+lec2.getCommentary());
//        System.out.println("lec 2 flags: "+lec2.getFlags());

//List<String> lecturersLinks = lecFac.getQisParser().getLecturesLinks();
//String lecText = lecFac.getQisParser().getOneLectureText(lecturersLinks.get(2));
//System.out.println(lecText);