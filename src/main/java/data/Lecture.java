package data;

import org.jsoup.nodes.Document;

import java.util.List;

public interface Lecture {
    void setDay(String day);
    String getDay();
    void setLecturer(String lecturer);
    List<String> getLecturers();
    void setTime(String time);
    String getTime();
    void setName(String name);
    String getName();
    void setModulesList(List<String> modules);
    List<String> getModulesList();
    void setDocument(Document doc);
    Document getDocument();

}
