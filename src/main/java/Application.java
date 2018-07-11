import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.util.List;

public class Application {

    private static final String NEW_FILE_NAME = "generated.xls";

    public static void main(String[] args) {

        File[] xlsFiles = FileUtils.getXlsFilesFromCurrentDirectory();

        // Prepare matrix from files
        List<String> dataToBeSaved = new TxtProcessor().getDataMatrix(xlsFiles);

        // Create and save WorkBook
        XlsProcessor xlsProcessor = new XlsProcessor();
        Workbook report = xlsProcessor.prepareWorkbook(dataToBeSaved);
        xlsProcessor.saveReport(report, NEW_FILE_NAME);
        System.out.println("Comet report created! Have a nice day!");
    }
}
