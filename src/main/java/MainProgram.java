import data.Lecture;
import data.LectureFactory;
import data.Lecture_Text_Impl;
import webserver.WebServer;

import java.util.List;

public class MainProgram {

    public static void main(String[] args) {
        LectureFactory lecFac = new LectureFactory("https://qis.server.uni-frankfurt.de/qisserver/rds?state=verpublish&publishContainer=lectureInstList&publishid=80100");
        List<String> lecturesLinks = lecFac.getQisParser().getLecturesLinks();
        String lecText2 = lecFac.getQisParser().getOneLectureText(lecturesLinks.get(1));
        String lecText1 = lecFac.getQisParser().getOneLectureText(lecturesLinks.get(0));
        System.out.println(lecText1);
        System.out.println(lecText2);
        Lecture lec2 = new Lecture_Text_Impl(lecText2, lecturesLinks.get(1));
        Lecture lec1 = new Lecture_Text_Impl(lecText1, lecturesLinks.get(0));
        System.out.println("------");
        System.out.println("lec 1 rawText: "+lec1.getTextRaw());
        System.out.println("lec 1 commentary: "+lec1.getCommentary());
        System.out.println(lec1.getTitle());
        System.out.println(lec1.getLecturersList());
        System.out.println(lec1.getDay());
        System.out.println(lec1.getTime());
        System.out.println(lec1.getRoom());
        System.out.println(lec1.getModulesSet());
        System.out.println("------");
        System.out.println("lec 2 rawText: "+lec2.getTextRaw());
        System.out.println("lec 2 commentary: "+lec2.getCommentary());




//        lecFac.createODSFileFromLectures("results.ods",true);
//        WebServer ws = new WebServer(4567);
//        ws.runRoutes();
    }
}


//List<String> lecturersLinks = lecFac.getQisParser().getLecturesLinks();
//String lecText = lecFac.getQisParser().getOneLectureText(lecturersLinks.get(2));
//System.out.println(lecText);