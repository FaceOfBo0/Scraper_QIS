import data.Lecture;
import data.Lecture_Text_Impl;
import helper.QISParser;

import java.util.List;

public class MainProgram {

    public static void main(String[] args) {
        //ODSFileWriter odsWriter = new ODSFileWriter();
        //odsWriter.createTable();
        QISParser qisParser = new QISParser("https://qis.server.uni-frankfurt.de/qisserver/rds?state=verpublish&publishContainer=lectureInstList&publishid=80100");
        List<Lecture> lectures = qisParser.getLectures();

//        linksList.forEach(elem -> {
//            Lecture lectureTest;
//            lectureTest = new Lecture_Text_Impl(qisParser.getOneLectureText(elem));
//            lectureTest.setLink(elem);
//
//            System.out.println(lectureTest.getTextRaw());
//            System.out.println(lectureTest.getTitle());
//            System.out.println(lectureTest.getDay());
//            System.out.println(lectureTest.getTime());
//            System.out.println(lectureTest.getRoom());
//            System.out.println(lectureTest.getLecturersList());
//            System.out.println(lectureTest.getModulesSet());
//            System.out.println(lectureTest.getLink());
//            System.out.println("-------------------");
//        });

    }
}
