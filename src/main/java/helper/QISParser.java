package helper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class QISParser {
    private Document lecturesDoc;
    private List<String> lecturesLinks;
    private List<String> lecturesTexts;
    private String urlOffset;

    public QISParser(String pUrlName, String pUrlOffset) {
        try {
            this.lecturesDoc = Jsoup.connect(pUrlName).get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.lecturesLinks = new ArrayList<>(0);
        this.lecturesTexts = new ArrayList<>(0);
        this.urlOffset = pUrlOffset;
    }

//    public Document getOneLectureDoc(String urlLecture) {
//        try {
//            return Jsoup.connect(urlLecture).get();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }

    public String getOneLectureText(String urlLecture) {
        if (!Objects.equals(this.urlOffset, ""))
            urlLecture += this.urlOffset;
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
            linksElements.forEach(elem -> {
                if (!Objects.equals(this.urlOffset, ""))
                    this.lecturesLinks.add(elem.attr("href") + this.urlOffset);
                else this.lecturesLinks.add(elem.attr("href"));
            });
        }
        return this.lecturesLinks;
    }

    public List<String> getLecturesTexts() {
        if (this.lecturesTexts.size()==0){
            this.getLecturesLinks().forEach(elem -> {
                this.lecturesTexts.add(this.getOneLectureText(elem));
            });
        }
        return this.lecturesTexts;
    }

    public Document getLecturesDoc(){
        return this.lecturesDoc;
    }


}
