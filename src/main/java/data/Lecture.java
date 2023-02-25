package data;

import org.jsoup.nodes.Document;

import java.util.List;

public interface Lecture {
    void setDay(String day);
    String getDay();
    void setLecturersList(List<String> lecturers);
    void addLecturer(String lecturer);
    List<String> getLecturersList();
    void setTime(String time);
    String getTime();
    void setTitle(String title);
    String getTitle();
    void setModulesList(List<String> modules);
    void addModule(String module);
    List<String> getModulesList();
    void setText(String text);
    String getText();

}
