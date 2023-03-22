package helper;

import data.Lecture;
import data.Lecture_Text_Impl;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class QISParser {
    private Document lecturesDoc;
    private List<String> lecturesLinks;
    private List<String> lecturesText;

    public QISParser(String urlName) {
        try {
            this.lecturesDoc = Jsoup.connect(urlName).get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.lecturesLinks = new ArrayList<>(0);
        this.lecturesText = new ArrayList<>(0);
    }

//    public Document getOneLectureDoc(String urlLecture) {
//        try {
//            return Jsoup.connect(urlLecture).get();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }

    public String getOneLectureText(String urlLecture) {
        Document lectureDoc;
        try {
            lectureDoc = Jsoup.connect(urlLecture).get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String lectureText = lectureDoc.text();
        lectureText = lectureText.replaceAll("\\t|\\n|\\xa0|\\r","");
        return lectureText;
    }

    public List<String> getLecturesLinks() {
        if (this.lecturesLinks.size()==0){
            Elements linksElements = this.lecturesDoc.select("td > a[href]");
            linksElements.forEach(elem -> this.lecturesLinks.add(elem.attr("href")));
        }
        return this.lecturesLinks;
    }

//    public List<String> getLecturesText() {
//        if (this.lecturesText.size()==0){
//            this.getLecturesLinks().forEach(elem -> {
//                this.lecturesText.add(this.getOneLectureText(elem));
//            });
//        }
//        return this.lecturesText;
//    }

//    public Document getLecturesDoc(){
//        return this.lecturesDoc;
//    }


}
