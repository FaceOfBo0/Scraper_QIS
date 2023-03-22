package data;

import com.github.jferard.fastods.*;
import com.github.jferard.fastods.attribute.SimpleLength;
import com.github.jferard.fastods.style.TableCellStyle;
import com.github.jferard.fastods.style.TableColumnStyle;
import helper.ODSFileWriter;
import helper.QISParser;

import java.io.IOException;
import java.util.*;

public class LectureFactory {

    private List<Lecture> lectures;
    private final QISParser qisParser;
    private final ODSFileWriter fileWriter;
    private Table table;
    public LectureFactory(String pURL){
        this.lectures = new ArrayList<>(0);
        this.qisParser = new QISParser(pURL);
        this.fileWriter = new ODSFileWriter();
        this.table = this.fileWriter.createTable("Wochenplan");
    }

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
        if (this.lectures.size()==0)
            this.qisParser.getLecturesLinks().forEach(elem -> {
                this.lectures.add(new Lecture_Text_Impl(this.qisParser.getOneLectureText(elem), elem));
            });
        return this.lectures;
    }

    public void createSheetFromLectures(){

        try {
            this.createTitleRow(Arrays.asList("Tag","Uhrzeit","Veranstaltung","Dozent","Raum","BM 1","BM 2","BM 3",
                    "AM 1","AM 2","AM 3","VM 1","VM 2","VM 3","GM 1","GM 2","GM 3"));
            for (int i = 0; i<17;i++) {
                TableColumnStyle columnStyle = TableColumnStyle.builder("column").columnWidth(SimpleLength.cm(2)).build();
                this.table.setColumnStyle(i,columnStyle);
            }
            this.lectures = this.getLectures();
            for (int i = 0; i < this.lectures.size(); i++){
                TableRowImpl row = this.table.getRow(i+1);
                row.getOrCreateCell(0).setStringValue(this.lectures.get(i).getDay());
                row.getOrCreateCell(1).setStringValue(this.lectures.get(i).getTime());
                row.getOrCreateCell(2).setStringValue(this.lectures.get(i).getTitle());
                String lecturers = "";
                if (this.lectures.get(i).getLecturersList().size() > 1)
                    lecturers = String.join(", ", this.lectures.get(i).getLecturersList());
                else if (this.lectures.get(i).getLecturersList().size() == 1)
                    lecturers = this.lectures.get(i).getLecturersList().get(0);
                row.getOrCreateCell(3).setStringValue(lecturers);
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
            this.fileWriter.saveDocAsODS("results.ods");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public ODSFileWriter getFileWriter() {
        return this.fileWriter;
    }

    public QISParser getQisParser() {
        return qisParser;
    }
}
