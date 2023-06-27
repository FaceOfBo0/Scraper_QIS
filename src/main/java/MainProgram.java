import data.LectureFactory;

public class MainProgram {

    public static void main(String[] args) {

        LectureFactory lectureFactory = new LectureFactory("https://qis.server.uni-frankfurt.de/qisserver/rds?state=verpublish&publishContainer=lectureInstList&publishid=80100&k_semester.semid=20231&idcol=k_semester.semid&idval=20231&purge=n&getglobal=semester&text=Sommer+2023");
        lectureFactory.createODSFileFromLectures("results.ods",true);

    }
}
