package helper;

import com.github.jferard.fastods.*;

import java.io.File;
import java.io.IOException;

public class ODSFileWriter {

    public void ODSFileWriter(){

    }
    public void createTable() throws IOException {
        OdsFactory factory;
        factory = OdsFactory.create();
        AnonymousOdsFileWriter odsWriter = factory.createWriter();
        OdsDocument document = odsWriter.document();
        Table table = document.addTable("Wochenplan");
        TableCellWalker cellWalker = table.getWalker();
        for (int r = 0; r < 10;r++){
            for (int c = 0; c<9; c++){
                cellWalker.setStringValue((char) (c + 'A') + String.valueOf(r + 1));
                cellWalker.next();
            }
            cellWalker.nextRow();
        }
//        URL url = new URL("https://www.uni-frankfurt.de");
//        cell.setText(Text.builder().par().span("Before ").link("link Text", url).build());
        odsWriter.saveAs(new File("src/main/resources/test.ods"));

    }
}
