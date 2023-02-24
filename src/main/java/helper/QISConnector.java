package helper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class QISConnector {
    private Document docSite;
    private List<String> seminarLinksList;
    public QISConnector(String urlName) throws IOException {
        this.docSite = Jsoup.connect(urlName).get();
        this.seminarLinksList = this.getLectureLinksList();
    }

    public List<String> getLectureLinksList(){
        if (this.seminarLinksList.size()==0){
            Elements linksElements = this.docSite.select("td > a[href]");
            linksElements.forEach(elem -> this.seminarLinksList.add(elem.attr("href")));
        }
        return this.seminarLinksList;
    }

    public Document getDocSite(){
        return this.docSite;
    }


}
