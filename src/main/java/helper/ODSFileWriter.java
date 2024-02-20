package helper;

import com.github.jferard.fastods.*;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Class handling saving and manipulating sheet files in odf-format.
 */
public class ODSFileWriter {
OdsDocument document;
AnonymousOdsFileWriter odsWriter;

    /**
     * Constructor with default initialization.
     */
    public ODSFileWriter(){
        OdsFactory factory;
        factory = OdsFactory.create();
        this.odsWriter = factory.createWriter();
        this.document = this.odsWriter.document();
    }

    /**
     * Create table (as Table Object) with specified name.
     * @param pTableName name for table
     * @return table
     */
    public Table createTable(String pTableName) {
        try {
            return this.document.addTable(pTableName);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveDocAsStream(OutputStream outStream) {
        try {
            this.odsWriter.save(outStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Save FileWriter-Object as ODS-File in resources folder
     * @param fileName name for ods-file
     */
    public void saveDocAsODS(String fileName){
        // "src/main/resources/"+
        try {
            this.odsWriter.saveAs(new File (fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}


//    public void createTable(String pTableName) {
//        Table table;
//        try {
//            table = this.document.addTable(pTableName);
//            TableCellWalker cellWalker = table.getWalker();
//            for (int r = 0; r < 10;r++){
//                for (int c = 0; c<9; c++){
//                    cellWalker.setStringValue((char) (c + 'A') + String.valueOf(r + 1));
//                    cellWalker.next();
//                }
//                cellWalker.nextRow();
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

//        URL url = new URL("https://www.uni-frankfurt.de");
//        cell.setText(Text.builder().par().span("Before ").link("link Text", url).build());