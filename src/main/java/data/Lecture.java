package data;

import java.util.List;
import java.util.Set;

public interface Lecture {

    String getDay();
    String getCommentary();
    String getRoom();
    void addLecturer(String lecturer);
    List<String> getLecturersList();
    String getTime();
    String getFlags();
    String getTitle();
    void addModule(String module);
    Set<String> getModulesSet();
    String getTextRaw();
    String getLink();
}


//void setTitle(String title);
//void setModulesList(Set<String> modules);
//void setLecturersList(List<String> lecturers);
//void setRoom(String room);
//void se//void setTime(String time);tDay(String day);