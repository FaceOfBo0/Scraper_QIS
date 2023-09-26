import OlatAPI.MyCoursesCall;
import OlatAPI.OlatAPIHandler;

import java.io.IOException;


public class MainProgram {

    public static void main(String[] args) {
//        LectureFactory lecFac = new LectureFactory("https://qis.server.uni-frankfurt.de/qisserver/rds?state=verpublish&publishContainer=lectureInstList&publishid=80100");
//        lecFac.createFileFromLectures("QIS23.24.ods",true);
//        WebServer ws = new WebServer(4567);
//        ws.runRoutes();

        try {
            OlatAPIHandler olatClient = new OlatAPIHandler("tim.koenig", "!Cw25.9!");
            MyCoursesCall myCourses = new MyCoursesCall();
            myCourses.getParsedResponseBody().forEach(elem -> System.out.println(elem+"\n"));
            //UserIDByParamCall newUserKey = new UserIDByParamCall("login","jakrebs");
            //AddOwnerCall newOwner = new AddOwnerCall("19157680134","koenigt");
            //System.out.println(newOwner.getStatusCode());

//            System.out.println(olatClient.getStatusCode());
//            System.out.println(olatClient.getIdentityKey());

//            OlatApiCall usersCall = new OlatApiCall(apiBaseUrl+"/users/"+identityKey,
//                    Connection.Method.GET, xOlatToken);
//            System.out.println(usersCall.getStatusCode());
//            System.out.println(usersCall.getResponseBody());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

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