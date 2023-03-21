package data;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lecture_Text_Impl implements Lecture{

    private String day;
    private final Pattern dayPattern;
    private List<String> lecturersList;
    private final Pattern lecturersPattern;
    private String time;
    private final Pattern timePattern;
    private String title;
    private final Pattern titlePattern;
    private Set<String> modulesList;
    private final Pattern modulesPattern;
    private final String text;
    private String room;
    private final Pattern roomPattern;
    private String link;

    public Lecture_Text_Impl(String lectureText) {
        this.day = "";
        this.dayPattern = Pattern.compile("([A-Z][a-z])\\.\\s\\d+:\\d+");
        this.time = "";
        this.timePattern = Pattern.compile("\\d+:\\d+");
        this.title = "";
        this.titlePattern = Pattern.compile(":\\sStartseite\\s(.*)\\s-\\sEinzelansicht");
        this.room = "";
        this.roomPattern = Pattern.compile("woch.*?-\\s([A-Z][A-Z]\\s\\d*\\.?\\d+).*\\sGruppe");
        this.modulesList = new HashSet<>(0);
        this.modulesPattern = Pattern.compile("BM 1|BM 2|BM 3|AM 1|AM 2|AM 3|AM 4|AM 5|VM 1|VM 2|VM 3|GM 1|GM 2|GM 3");
        this.lecturersList = new ArrayList<>(0);
        this.lecturersPattern = Pattern.compile("Zuständigkeit\\s(.+?)\\s(Studiengänge\\sAbschluss|Zuordnung\\szu)+");
        this.text = lectureText;
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
    public String getRoom() {
        if (Objects.equals(this.room, "")) {
            Matcher dayMatcher = this.roomPattern.matcher(this.text);
            if (dayMatcher.find()) this.room = dayMatcher.group(1);
            else this.room = "n.a.";
        }
        return this.room;
    }

    @Override
    public void addLecturer(String lecturer) {
        this.lecturersList.add(lecturer);
    }

    @Override
    public List<String> getLecturersList() {
        if (this.lecturersList.size()==0){
            Matcher lecturerMatcher = this.lecturersPattern.matcher(this.text);
            String lecturersRaw = "";
            if (lecturerMatcher.find()){
                lecturersRaw = lecturerMatcher.group(1);
            }
            String[] lecturersRawArray = lecturersRaw.split(", ");
            if (lecturersRawArray.length != 0) {
                this.addLecturer(lecturersRawArray[0]);
                if (lecturersRawArray.length > 3) {
                    String secondLecturerRaw = lecturersRawArray[2];
                    String[] secondLecturerList = secondLecturerRaw.split(" ");
                    String scndLecturerFinal = secondLecturerList[secondLecturerList.length - 1];
                    this.addLecturer(scndLecturerFinal);
                }
            }
        }
        return this.lecturersList;
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
    public String getTitle() {
        if (Objects.equals(this.title, "")) {
            Matcher titleMatcher = this.titlePattern.matcher(this.text);
            if (titleMatcher.find()) this.title = titleMatcher.group(1);
            else this.title = "n.a.";
        }
        return this.title;
    }

    @Override
    public void addModule(String module){ this.modulesList.add(module); }

    @Override
    public Set<String> getModulesSet() {
        if (this.modulesList.size()==0){
            Matcher moduleMatcher = this.modulesPattern.matcher(this.text);
            while(moduleMatcher.find()){
                this.addModule(moduleMatcher.group(0));
            }
        }
        return this.modulesList;
    }

    @Override
    public String getTextRaw() {
        return this.text;
    }

    @Override
    public String getLink() {
        return this.link;
    }

    @Override
    public void setLink(String urlLink) {
        this.link = urlLink;
    }

    @Override
    public String toString(){
        return this.getTitle();
    }
}
