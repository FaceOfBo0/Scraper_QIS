package data;

import com.github.jferard.fastods.Table;
import com.github.jferard.fastods.TableCellWalker;
import com.github.jferard.fastods.style.TableCellStyle;
import com.github.jferard.fastods.style.TableCellStyleBuilder;
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
        this.createTitleRow(Arrays.asList("Tag","Uhrzeit","Veranstaltung","Dozent","Raum","BM 1","BM 2","BM 3",
                "AM 1","AM 2","AM 3","VM 1","VM 2","VM 3","GM 1","GM 2","GM 3"));

    }

    public ODSFileWriter getFileWriter() {
        return this.fileWriter;
    }

    public QISParser getQisParser() {
        return qisParser;
    }
}
