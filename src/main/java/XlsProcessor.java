import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class XlsProcessor {

    public Workbook prepareWorkbook(List<String> matrix) {
        Workbook report = new HSSFWorkbook();
        Sheet sheet = report.createSheet();
        for (int i = 0; i < matrix.size(); i++) {
            Row emptyRow = sheet.createRow(i);
            String[] cells = matrix.get(i).split("\t");
            for (int j = 0; j < cells.length; j++) {
                emptyRow.createCell(j).setCellValue(cells[j]);
            }
        }
        return report;
    }

    public void saveReport(Workbook report, String fileName) {
        try {
            FileOutputStream outputStream = new FileOutputStream(fileName);
            report.write(outputStream);
            report.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
