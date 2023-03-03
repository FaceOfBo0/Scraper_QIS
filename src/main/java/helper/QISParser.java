package helper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class QISParser {
    private Document lecturesDoc;
    private List<String> lecturesLinksList;
    private List<String> lecturesTextList;

    public QISParser(String urlName) {
        try {
            this.lecturesDoc = Jsoup.connect(urlName).get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.lecturesLinksList = new ArrayList<>(0);
        this.lecturesTextList = new ArrayList<>(0);
    }

    public Document getOneLectureDoc(String urlLecture) {
        try {
            return Jsoup.connect(urlLecture).get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

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

    public List<String> getLecturesLinksList() {
        if (this.lecturesLinksList.size()==0){
            Elements linksElements = this.lecturesDoc.select("td > a[href]");
            linksElements.forEach(elem -> this.lecturesLinksList.add(elem.attr("href")));
        }
        return this.lecturesLinksList;
    }

    public List<String> getLecturesTextList() {
        if (this.lecturesTextList.size()==0){
            this.getLecturesLinksList().forEach(elem -> {
                this.lecturesTextList.add(getOneLectureText(elem));
            });
        }
        return this.lecturesTextList;
    }

    public Document getLecturesDoc(){
        return this.lecturesDoc;
    }


}
