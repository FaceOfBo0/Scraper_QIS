import java.io.IOException;

public class MainProgram {

    public static void main(String[] args) throws IOException {
        ODSFileWriter odsWriter = new ODSFileWriter();
        odsWriter.createTable();
        QISConnector qisConnector = new QISConnector("https://qis.server.uni-frankfurt.de/qisserver/rds?state=verpublish&publishContainer=lectureInstList&publishid=80100");
        System.out.println(qisConnector.getDocSite());
        System.out.println("\n");
        qisConnector.getLectureLinksList();
    }
}
