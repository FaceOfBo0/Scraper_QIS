import data.Lecture;
import data.LectureFactory;
import data.Lecture_Text_Impl;
import helper.QISParser;

import java.util.List;

public class MainProgram {

    public static void main(String[] args) {

        LectureFactory lectureFactory = new LectureFactory("https://qis.server.uni-frankfurt.de/qisserver/rds?state=verpublish&publishContainer=lectureInstList&publishid=80100");
        lectureFactory.createODSFileFromLectures("results.ods",true);

    }
}
