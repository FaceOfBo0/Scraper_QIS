package data;
import org.jsoup.nodes.Document;

import java.util.ArrayList;
import java.util.List;

public class Lecture_Impl implements Lecture{

    private String day;
    private List<String> lecturersList;
    private String time;
    private String name;
    private List<String> modulesList;
    private Document document;

    Lecture_Impl(){
        this.lecturersList = new ArrayList<>(0);
        this.day = "";
    }

    @Override
    public void setDay(String day) {
        this.day = day;
    }

    @Override
    public String getDay() {
        return this.day;
    }

    @Override
    public void setLecturer(String lecturer) {
        this.lecturersList.add(lecturer);
    }

    @Override
    public List<String> getLecturers() {
        return this.lecturersList;
    }

    @Override
    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String getTime() {
        return this.time;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setModulesList(List<String> modules) {
        this.modulesList = modules;
    }

    @Override
    public List<String> getModulesList() {
        return this.modulesList;
    }

    @Override
    public void setDocument(Document doc) {
        this.document = doc;
    }

    @Override
    public Document getDocument() {
        return this.document;
    }
}
