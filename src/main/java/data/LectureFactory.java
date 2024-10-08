package data;

import com.github.jferard.fastods.*;
import com.github.jferard.fastods.attribute.CellAlign;
import com.github.jferard.fastods.attribute.SimpleLength;
import com.github.jferard.fastods.attribute.VerticalAlign;
import com.github.jferard.fastods.style.*;
import helper.DayComparator;
import helper.ODSFileWriter;
import helper.QisParser;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

public class LectureFactory {

    private String url;
    private final String semester;
    private List<Lecture> lectures;
    private final QisParser qisParser;
    private final ODSFileWriter fileWriter;
    private final Table table;
    private final List<String> titlesList;

    public LectureFactory(String pURL, String pSemester) {
        this.url = pURL;
        this.semester = pSemester;
        this.lectures = new ArrayList<>(0);
        String offset = this.createUrlOffset();
        this.qisParser = new QisParser(this.url, offset);
        this.fileWriter = new ODSFileWriter();
        this.table = this.fileWriter.createTable("Wochenplan");
        this.titlesList = Arrays.asList("Tag","Uhrzeit","Veranstaltung","Dozent","Raum","BM 1","BM 2","BM 3",
                "AM 1","AM 2","AM 3","VM 1","VM 2","VM 3","GM 1","GM 2","GM 3", "Sonst.","OLAT Link","VRMB");
    }

    public LectureFactory(String pURL) {
        this(pURL, "default");
    }

    private String createUrlOffset() {
        if (!Objects.equals(this.semester, "default") && this.semester.length()==6) {
            String year = this.semester.split("\\.")[0];
            String half = this.semester.split("\\.")[1];
            if (Objects.equals(half, "1")) {
                String offset = "&k_semester.semid=" + year + half + "&idcol=k_semester.semid&idval="+ year + half +"&purge=n&getglobal=semester&text=Sommer+" + year;
                this.url = this.url + offset;
                return offset;
            }
            else if (Objects.equals(half, "2")) {
                int newYearShort = Integer.parseInt(year.substring(2)) + 1;
                String offset = "&k_semester.semid=" + year + half + "&idcol=k_semester.semid&idval="+ year + half +"&purge=n&getglobal=semester&text=Winter+" + year + "%2F" + newYearShort;
                this.url = this.url + offset;
                return offset;
            }
        }
        return "";
    }

    private void createTitleRow(){
        try {
            TableCellStyle headerStyle = TableCellStyle.builder("bold").fontWeightBold().build();
            TableCellWalker cellWalker = this.table.getWalker();
            for (String rowItem : this.titlesList) {
                cellWalker.setStringValue(rowItem);
                cellWalker.setStyle(headerStyle);
                cellWalker.next();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Lecture> getLectures() {
        if (this.lectures.isEmpty()) {
            this.qisParser.getLecturesLinks().forEach(
                    elem -> this.lectures.add(new Lecture_Text_Impl(this.qisParser.getOneLectureText(elem), elem))
            );
        }
        return this.lectures;
    }

    public void createFileFromLectures(OutputStream outStream, boolean pLinkFlag) {
        try {
            this.createTitleRow();

            // defining different styling for sheet
            TableColumnStyle columnStyleModules = TableColumnStyle.builder("column-modules").columnWidth(SimpleLength.in(0.4)).build();
            TableCellStyle wrappedCellStyle = TableCellStyle.builder("cell-wraped").fontWrap(true).build();
            TableColumnStyle columnStyleDefault = TableColumnStyle.builder("column-default").build();
            TableRowStyle rowStyleDouble = TableRowStyle.builder("row-default").rowHeight(SimpleLength.in(0.32)).build();
            TableCellStyle cellStyleModules = TableCellStyle.builder("cell-middle").verticalAlign(VerticalAlign.MIDDLE).textAlign(CellAlign.CENTER).build();

            // applying different styling for columns
            for (int i = 0; i < 20; i++) {
                if (i > 4 && i < 17) {
                    this.table.setColumnStyle(i,columnStyleModules);
                    this.table.setColumnDefaultCellStyle(i, cellStyleModules);
                }
                else this.table.setColumnStyle(i,columnStyleDefault);
            }

            // Load Lectures and sort by day and time with custom comparator
            this.lectures = this.getLectures();
            this.lectures.sort(new DayComparator());

            //base loop to write lectures in rows
            for (int i = 0; i < this.lectures.size(); i++){

                TableRowImpl row = this.table.getRow(i + 1);
                row.setRowStyle(rowStyleDouble);

                // write Day and Time to Row
                row.getOrCreateCell(0).setStringValue(this.lectures.get(i).getDay());
                row.getOrCreateCell(1).setStringValue(this.lectures.get(i).getTime());

                // write Title to Row
                if (pLinkFlag)
                    this.createTitleLink(row, i);
                else
                    row.getOrCreateCell(2).setStringValue(this.lectures.get(i).getTitle());
                row.getOrCreateCell(2).setStyle(wrappedCellStyle);

                // write Lecturers to Row
                row.getOrCreateCell(3).setStringValue(this.lectures.get(i).getLecturers());
                row.getOrCreateCell(3).setStyle(wrappedCellStyle);

                row.getOrCreateCell(4).setStringValue(this.lectures.get(i).getRoom());
                if (this.lectures.get(i).getModulesSet().contains("BM1"))
                    row.getOrCreateCell(5).setStringValue("x");
                if (this.lectures.get(i).getModulesSet().contains("BM2"))
                    row.getOrCreateCell(6).setStringValue("x");
                if (this.lectures.get(i).getModulesSet().contains("BM3"))
                    row.getOrCreateCell(7).setStringValue("x");
                if (this.lectures.get(i).getModulesSet().contains("AM1"))
                    row.getOrCreateCell(8).setStringValue("x");
                if (this.lectures.get(i).getModulesSet().contains("AM2"))
                    row.getOrCreateCell(9).setStringValue("x");
                if (this.lectures.get(i).getModulesSet().contains("AM3"))
                    row.getOrCreateCell(10).setStringValue("x");
                if (this.lectures.get(i).getModulesSet().contains("VM1"))
                    row.getOrCreateCell(11).setStringValue("x");
                if (this.lectures.get(i).getModulesSet().contains("VM2"))
                    row.getOrCreateCell(12).setStringValue("x");
                if (this.lectures.get(i).getModulesSet().contains("VM3"))
                    row.getOrCreateCell(13).setStringValue("x");
                if (this.lectures.get(i).getModulesSet().contains("GM1"))
                    row.getOrCreateCell(14).setStringValue("x");
                if (this.lectures.get(i).getModulesSet().contains("GM2"))
                    row.getOrCreateCell(15).setStringValue("x");
                if (this.lectures.get(i).getModulesSet().contains("GM3"))
                    row.getOrCreateCell(16).setStringValue("x");

                // write Flags "VRMB" to Row
                row.getOrCreateCell(19).setStringValue(this.lectures.get(i).getFlags());
            }
            //this.fileWriter.saveDocAsODS(pFileName);
            this.fileWriter.saveDocAsStream(outStream);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void createTitleLink(TableRowImpl pRow, int pIndex) {
        String titleRaw = this.lectures.get(pIndex).getTitle();
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
            pRow.getOrCreateCell(2).setText(Text.builder().par().link(titleLink,this.lectures.get(pIndex).getLink()).span(" " + titleRest).build());
        }
        else pRow.getOrCreateCell(2).setText(Text.builder().par().link(this.lectures.get(pIndex).getTitle(),this.lectures.get(pIndex).getLink()).build());
    }
}

//    public ODSFileWriter getFileWriter() {
//        return this.fileWriter;
//    }
//
//    public QisParser getQisParser() {
//        return qisParser;
//    }

// getLecturers_v2()
//            for (int i = 0; i < this.qisParser.getLecturesLinks().size(); i++){
//                this.lectures.add(new Lecture_Text_Impl(this.qisParser.getLecturesTexts().get(i),
//                        this.qisParser.getLecturesLinks().get(i)));
//            }