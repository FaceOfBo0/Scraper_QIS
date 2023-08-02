package data;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lecture_Text_Impl implements Lecture{

    private String day;
    private final Pattern dayPattern;
    private final List<String> lecturersList;
    private final Pattern lecturersPattern;
    private String time;
    private final Pattern timePattern;
    private String title;
    private final Pattern titlePattern;
    private final Set<String> modulesList;
    private final Pattern modulesPattern;
    private String textRaw;
    private StringBuilder flags;
    private String room;
    private final Pattern roomPattern;
    private String commentary;
    private final Pattern commentaryPattern;

    private String link;

    public Lecture_Text_Impl(String pLectureText, String pLectureURL) {
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
        this.commentary = "";
        this.flags = new StringBuilder();
        this.commentaryPattern = Pattern.compile("Inhalt\\sKommentar(.*?)\\sLeistungsnachweis");
        this.link = pLectureURL;
        this.textRaw = pLectureText;
    }

    public String getFlags(){
        if (this.flags.isEmpty()){
            this.flags = new StringBuilder("V___");
            if (!Objects.equals(this.getRoom(), "n.a."))
                this.flags.setCharAt(1,'R');
            if (!this.getModulesSet().isEmpty())
                this.flags.setCharAt(2,'M');
            if (!Objects.equals(this.getCommentary(), "n.a.") && !Objects.equals(this.getCommentary(), "...") && !Objects.equals(this.getCommentary(), ""))
                this.flags.setCharAt(3,'B');
        }
        return this.flags.toString();
    }

    public String getCommentary(){
        if (Objects.equals(this.commentary, "")) {
            Matcher commentaryMatcher = this.commentaryPattern.matcher(this.textRaw);
            if (commentaryMatcher.find()) this.commentary = commentaryMatcher.group(1).trim();
            else this.commentary = "n.a.";
        }
        return this.commentary;
    }

    @Override
    public String getDay() {
        if (Objects.equals(this.day, "")) {
            Matcher dayMatcher = this.dayPattern.matcher(this.textRaw);
            if (dayMatcher.find()) this.day = dayMatcher.group(1);
            else this.day = "n.a.";
        }
        return this.day;
    }

    @Override
    public String getRoom() {
        if (Objects.equals(this.room, "")) {
            Matcher dayMatcher = this.roomPattern.matcher(this.textRaw);
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
        if (this.lecturersList.isEmpty()){
            Matcher lecturerMatcher = this.lecturersPattern.matcher(this.textRaw);
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
            Matcher timeMatcher = this.timePattern.matcher(this.textRaw);
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
            Matcher titleMatcher = this.titlePattern.matcher(this.textRaw);
            if (titleMatcher.find()) {
                this.title = titleMatcher.group(1);
                this.title = this.title.replace("&","&amp;");
            }
            else this.title = "n.a.";
        }
        return this.title;
    }

    @Override
    public void addModule(String module){ this.modulesList.add(module); }

    @Override
    public Set<String> getModulesSet() {
        if (this.modulesList.isEmpty()){
            Matcher moduleMatcher = this.modulesPattern.matcher(this.textRaw);
            while(moduleMatcher.find()){
                this.addModule(moduleMatcher.group(0));
            }
        }
        return this.modulesList;
    }

    @Override
    public String getTextRaw() {
        return this.textRaw;
    }

    @Override
    public String getLink() {
        return this.link;
    }

    @Override
    public String toString(){
        return this.getTitle();
    }
}
