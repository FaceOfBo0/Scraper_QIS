package data;

import java.util.List;
import java.util.Set;

public interface Lecture {
    //void setDay(String day);
    String getDay();
    //void setRoom(String room);
    String getRoom();
    //void setLecturersList(List<String> lecturers);
    void addLecturer(String lecturer);
    List<String> getLecturersList();
    //void setTime(String time);
    String getTime();
    //void setTitle(String title);
    String getTitle();
    //void setModulesList(Set<String> modules);
    void addModule(String module);
    Set<String> getModulesSet();

    void setTextRaw(String pext);
    String getTextRaw();
    String getLink();
    void setLink(String urlLink);

}
