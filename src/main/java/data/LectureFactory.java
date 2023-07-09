package data;

import com.github.jferard.fastods.*;
import com.github.jferard.fastods.attribute.CellAlign;
import com.github.jferard.fastods.attribute.SimpleLength;
import com.github.jferard.fastods.attribute.VerticalAlign;
import com.github.jferard.fastods.style.*;
import helper.DayComparator;
import helper.ODSFileWriter;
import helper.QISParser;

import java.io.IOException;
import java.util.*;

public class LectureFactory {

    private List<Lecture> lectures;
    private final QISParser qisParser;
    private final ODSFileWriter fileWriter;
    private final Table table;
    private final List<String> titlesList;

    public LectureFactory(String pURL, String pSemester) {
        String urlOffset = "";
        if (!Objects.equals(pSemester, "default") && pSemester.length()==6) {
            String year = pSemester.split("\\.")[0];
            String half = pSemester.split("\\.")[1];
            if (Objects.equals(half, "1")) {
                urlOffset = "&k_semester.semid=" + year + half + "&idcol=k_semester.semid&idval="+ year + half +"&purge=n&getglobal=semester&text=Sommer+" + year;
                pURL = pURL + urlOffset;
                System.out.println(pURL);
            }
            else if (Objects.equals(half, "2")) {
                int newYearShort = Integer.parseInt(year.substring(2))+1;
                urlOffset = "&k_semester.semid=" + year + half + "&idcol=k_semester.semid&idval="+ year + half +"&purge=n&getglobal=semester&text=Winter+" + year + "%2F" + newYearShort;
                pURL = pURL + urlOffset;
                System.out.println(pURL);
            }

        }
        this.lectures = new ArrayList<>(0);
        this.titlesList = Arrays.asList("Tag","Uhrzeit","Veranstaltung","Dozent","Raum","BM 1","BM 2","BM 3",
                "AM 1","AM 2","AM 3","VM 1","VM 2","VM 3","GM 1","GM 2","GM 3", "Sonst.","OLAT Link");
        this.qisParser = new QISParser(pURL, urlOffset);
        this.fileWriter = new ODSFileWriter();
        this.table = this.fileWriter.createTable("Wochenplan");
    }

//    public LectureFactory(String pURL){
//       this(pURL, "default");
//    }

    private void createTitleRow(List<String> rowItems){
        try {
            TableCellWalker cellWalker;
            TableCellStyle headerStyle = TableCellStyle.builder("bold").fontWeightBold().build();
            cellWalker = this.table.getWalker();
            for (String rowItem : rowItems) {
                cellWalker.setStringValue(rowItem);
                cellWalker.setStyle(headerStyle);
                cellWalker.next();
            }
        } catch (IOException e) {

            throw new RuntimeException(e);
        }
    }
    public List<Lecture> getLectures(){
        if (this.lectures.size()==0) {
//            this.qisParser.getLecturesLinks().forEach(elem -> this.lectures.add(new Lecture_Text_Impl(this.qisParser.getOneLectureText(elem), elem)));
            for (int i = 0; i < this.qisParser.getLecturesLinks().size(); i++){
                this.lectures.add(new Lecture_Text_Impl(this.qisParser.getLecturesTexts().get(i),
                        this.qisParser.getLecturesLinks().get(i)));
            }
        }
        return this.lectures;
    }

    public void createODSFileFromLectures(String pFileName, boolean pLinkFlag){

        try {
            this.createTitleRow(this.titlesList);

            // defining different stylings for sheet
            TableColumnStyle columnStyleModules = TableColumnStyle.builder("column-modules").columnWidth(SimpleLength.in(0.4)).build();
            TableCellStyle wrapedCellStyle = TableCellStyle.builder("cell-wraped").fontWrap(true).build();
            TableColumnStyle columnStyleDefault = TableColumnStyle.builder("column-default").build();
            TableRowStyle rowStyleDouble = TableRowStyle.builder("row-default").rowHeight(SimpleLength.in(0.32)).build();
            TableCellStyle cellStyleModules = TableCellStyle.builder("cell-middle").verticalAlign(VerticalAlign.MIDDLE).textAlign(CellAlign.CENTER).build();

            // applying different stylings for columns
            for (int i = 0; i < 19; i++) {
                if (i > 4 && i < 17) {
                    this.table.setColumnStyle(i,columnStyleModules);
                    this.table.setColumnDefaultCellStyle(i, cellStyleModules);
                }
                else this.table.setColumnStyle(i,columnStyleDefault);
            }


            this.lectures = this.getLectures();
            this.lectures.sort(new DayComparator());


            for (int i = 0; i < this.lectures.size(); i++){
                TableRowImpl row = this.table.getRow(i+1);
                row.setRowStyle(rowStyleDouble);
                row.getOrCreateCell(0).setStringValue(this.lectures.get(i).getDay());
                row.getOrCreateCell(1).setStringValue(this.lectures.get(i).getTime());

                if(!pLinkFlag)
                    row.getOrCreateCell(2).setStringValue(this.lectures.get(i).getTitle());
                else {
                    String titleRaw = this.lectures.get(i).getTitle();
                    if (titleRaw.length() > 30) {
                        List<String> splitTitle = new ArrayList<>(Arrays.asList(titleRaw.split(" ")));
                        List<String> splitTitleLink = new ArrayList<>(0);
                        String titleLink;
                        String titleRest;

                        if (splitTitle.contains("Forschungskolloquium"))
                            splitTitleLink.add(splitTitle.remove(0));
                        else {
                            splitTitleLink.add(splitTitle.remove(0));
                            splitTitleLink.add(splitTitle.remove(0));
                        }

                        titleLink = String.join(" ",splitTitleLink);
                        titleRest = String.join(" ",splitTitle);
                        row.getOrCreateCell(2).setText(Text.builder().par().link(titleLink,this.lectures.get(i).getLink()).span(" " + titleRest).build());
                    }
                    else row.getOrCreateCell(2).setText(Text.builder().par().link(this.lectures.get(i).getTitle(),this.lectures.get(i).getLink()).build());
                }
                row.getOrCreateCell(2).setStyle(wrapedCellStyle);

                String lecturers = "";
                if (this.lectures.get(i).getLecturersList().size() > 1)
                    lecturers = String.join(", ", this.lectures.get(i).getLecturersList());
                else if (this.lectures.get(i).getLecturersList().size() == 1)
                    lecturers = this.lectures.get(i).getLecturersList().get(0);
                row.getOrCreateCell(3).setStringValue(lecturers);
                row.getOrCreateCell(3).setStyle(wrapedCellStyle);

                row.getOrCreateCell(4).setStringValue(this.lectures.get(i).getRoom());
                if (this.lectures.get(i).getModulesSet().contains("BM 1"))
                    row.getOrCreateCell(5).setStringValue("x");
                if (this.lectures.get(i).getModulesSet().contains("BM 2"))
                    row.getOrCreateCell(6).setStringValue("x");
                if (this.lectures.get(i).getModulesSet().contains("BM 3"))
                    row.getOrCreateCell(7).setStringValue("x");
                if (this.lectures.get(i).getModulesSet().contains("AM 1"))
                    row.getOrCreateCell(8).setStringValue("x");
                if (this.lectures.get(i).getModulesSet().contains("AM 2"))
                    row.getOrCreateCell(9).setStringValue("x");
                if (this.lectures.get(i).getModulesSet().contains("AM 3"))
                    row.getOrCreateCell(10).setStringValue("x");
                if (this.lectures.get(i).getModulesSet().contains("VM 1"))
                    row.getOrCreateCell(11).setStringValue("x");
                if (this.lectures.get(i).getModulesSet().contains("VM 2"))
                    row.getOrCreateCell(12).setStringValue("x");
                if (this.lectures.get(i).getModulesSet().contains("VM 3"))
                    row.getOrCreateCell(13).setStringValue("x");
                if (this.lectures.get(i).getModulesSet().contains("GM 1"))
                    row.getOrCreateCell(14).setStringValue("x");
                if (this.lectures.get(i).getModulesSet().contains("GM 2"))
                    row.getOrCreateCell(15).setStringValue("x");
                if (this.lectures.get(i).getModulesSet().contains("GM 3"))
                    row.getOrCreateCell(16).setStringValue("x");
            }
            this.fileWriter.saveDocAsODS(pFileName);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

//    public ODSFileWriter getFileWriter() {
//        return this.fileWriter;
//    }
//
//    public QISParser getQisParser() {
//        return qisParser;
//    }
}
