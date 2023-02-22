import com.github.jferard.fastods.*;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class ODSFileWriter {

    public static void main(String[] args) throws IOException {
        testRun();
    }

    public static void testRun() throws IOException {
        OdsFactory factory;
        factory = OdsFactory.create();
        AnonymousOdsFileWriter odsWriter = factory.createWriter();
        OdsDocument document = odsWriter.document();
        Table table = document.addTable("Wochenplan");
        TableRowImpl row = table.getRow(0);
        TableCell cell = row.getOrCreateCell(0);
        cell.setStringValue("Halle Wellt!");
        URL url = new URL("https://www.uni-frankfurt.de");

        cell.setText(Text.builder().par().span("Before ").link("link Text", url).build());
        odsWriter.saveAs(new File("src/main/resources/test.ods"));




    }
}
