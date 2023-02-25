import data.Lecture;
import data.Lecture_Text_Impl;
import helper.QISParser;

import java.io.IOException;
import java.util.List;

public class MainProgram {

    public static void main(String[] args) throws IOException {
        //ODSFileWriter odsWriter = new ODSFileWriter();
        //odsWriter.createTable();
        QISParser parser = new QISParser("https://qis.server.uni-frankfurt.de/qisserver/rds?state=verpublish&publishContainer=lectureInstList&publishid=80100");
        List<String> linksList = parser.getLecturesLinksList();
        Lecture lectureTest = new Lecture_Text_Impl(parser.getOneLectureText(linksList.get(0)));
        System.out.println(lectureTest.getDay());
        System.out.println(lectureTest.getTime());
    }
}
