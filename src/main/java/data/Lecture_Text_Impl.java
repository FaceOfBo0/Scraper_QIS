package data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lecture_Text_Impl implements Lecture{

    private String day;
    Pattern dayPattern;
    private List<String> lecturersList;
    Pattern lecturerPattern;
    private String time;
    Pattern timePattern;
    private String title;
    Pattern titlePattern;
    private List<String> modulesList;
    Pattern modulePattern;
    private String text;

    public Lecture_Text_Impl(String lectureText){
        this.day = "";
        this.dayPattern = Pattern.compile("([A-Z][a-z])\\.\\s\\d+:\\d+");
        this.time = "";
        this.timePattern = Pattern.compile("\\d+:\\d+");
        this.title = "";
        this.modulesList = new ArrayList<>(0);
        this.lecturersList = new ArrayList<>(0);
        this.text = lectureText;
    }

    @Override
    public void setDay(String day) {
        this.day = day;
    }

    @Override
    public String getDay() {
        if (Objects.equals(this.day, "")) {
            Matcher dayMatcher = this.dayPattern.matcher(this.text);
            if (dayMatcher.find()) this.day = dayMatcher.group(1);
            else this.day = "n.a.";
        }
        return this.day;
    }

    @Override
    public void setLecturersList(List<String> lecturers) {
        this.lecturersList = lecturers;
    }

    @Override
    public void addLecturer(String lecturer) {
        this.lecturersList.add(lecturer);
    }

    @Override
    public List<String> getLecturersList() {
        return this.lecturersList;
    }

    @Override
    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String getTime() {
        if (Objects.equals(this.time, "")) {
            List<String> resultList = new ArrayList<>(0);
            Matcher timeMatcher = this.timePattern.matcher(this.text);
            while (timeMatcher.find()){
                resultList.add(timeMatcher.group(0));
            }
            if (resultList.size()>=2)
                this.time = resultList.get(0).substring(0,resultList.get(0).length()-3) + "-"
                    + resultList.get(1).substring(0,resultList.get(1).length()-3);
            else this.time = "n.a.";
        }
        return this.time;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String getTitle() {
        return this.title;
    }

    @Override
    public void setModulesList(List<String> modules) {
        this.modulesList = modules;
    }
    @Override
    public void addModule(String module){ this.modulesList.add(module); }
    @Override
    public List<String> getModulesList() {
        return this.modulesList;
    }

    @Override
    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String getText() {
        return this.text;
    }
}
