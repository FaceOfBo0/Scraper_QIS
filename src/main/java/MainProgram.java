import data.LectureFactory;

import java.util.List;

public class MainProgram {

    public static void main(String[] args) {

        LectureFactory lecFac = new LectureFactory("https://qis.server.uni-frankfurt.de/qisserver/rds?state=verpublish&publishContainer=lectureInstList&publishid=80100","2023.1");
        //List<String> lecturersLinks = lecFac.getQisParser().getLecturesLinks();
        //String lecText = lecFac.getQisParser().getOneLectureText(lecturersLinks.get(2));
        //System.out.println(lecText);
        lecFac.createODSFileFromLectures("results.ods",true);

    }
}
