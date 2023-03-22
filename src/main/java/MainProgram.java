import data.Lecture;
import data.LectureFactory;
import data.Lecture_Text_Impl;
import helper.QISParser;

import java.util.List;

public class MainProgram {

    public static void main(String[] args) {
        //ODSFileWriter odsWriter = new ODSFileWriter();
        //odsWriter.createTable();
        LectureFactory lectureFactory = new LectureFactory("https://qis.server.uni-frankfurt.de/qisserver/rds?state=verpublish&publishContainer=lectureInstList&publishid=80100");
        lectureFactory.createSheetFromLectures();
//        List<Lecture> lectures = lectureFactory.getLectures();
//
//
//        lectures.forEach(elem -> {
//            System.out.println(elem.getTextRaw());
//            System.out.println(elem.getTitle());
//            System.out.println(elem.getDay());
//            System.out.println(elem.getTime());
//            System.out.println(elem.getRoom());
//            System.out.println(elem.getLecturersList());
//            System.out.println(elem.getModulesSet());
//            System.out.println(elem.getLink());
//            System.out.println("-------------------");
//        });

    }
}
