import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TxtProcessor {

    public List<String> getDataMatrix(File[] files) {
        List<String> matrix = new ArrayList<>();
        List<String> unsortedData = new ArrayList<>();
        for (File file : files) {
            List<String> rows = getFilteredData(file.getName());
            unsortedData.addAll(rows);
        }
        List<String> sorted = sortRows(unsortedData);
        matrix.add(getHeaders(files[0].getName()));
        matrix.addAll(sorted);
        return matrix;
    }

    public String getHeaders(String fileName) {
        String headers = null;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            headers = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return headers;
    }

    private List<String> getFilteredData(String fileName) {
        List<String> rows = new ArrayList<>();
        try {
            List<String> unfilteredRows = Files.readAllLines(Paths.get(fileName), Charset.defaultCharset());
            for (int i = 1; i < unfilteredRows.size(); i++) {
                String row = unfilteredRows.get(i);
                if (row.startsWith("comets") && row.contains("normal")) {
                    rows.add(row);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rows;
    }

    private List<String> sortRows(List<String> unsortedRows) {
        return unsortedRows.stream().sorted((r1, r2) -> {
            String[] cells1 = r1.split("\t");
            String[] cells2 = r2.split("\t");
            return Double.compare(Double.valueOf(cells2[16]), Double.valueOf(cells1[16]));
        }).collect(Collectors.toList());
    }
}
