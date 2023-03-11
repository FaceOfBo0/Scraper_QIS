package helper;

import com.github.jferard.fastods.*;

import java.io.File;
import java.io.IOException;

public class ODSFileWriter {
OdsDocument document;
AnonymousOdsFileWriter fileWriter;

    public ODSFileWriter(){
        OdsFactory factory;
        factory = OdsFactory.create();
        this.fileWriter = factory.createWriter();
        this.document = this.fileWriter.document();
    }

    public void createTable() {
        Table table = null;
        try {
            table = this.document.addTable("Wochenplan");
            TableCellWalker cellWalker = table.getWalker();
            for (int r = 0; r < 10;r++){
                for (int c = 0; c<9; c++){
                    cellWalker.setStringValue((char) (c + 'A') + String.valueOf(r + 1));
                    cellWalker.next();
                }
                cellWalker.nextRow();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

//        URL url = new URL("https://www.uni-frankfurt.de");
//        cell.setText(Text.builder().par().span("Before ").link("link Text", url).build());
    }
    public void saveDocAsODS(String fileName){
        try {
            this.fileWriter.saveAs(new File ("src/main/resources/"+fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
