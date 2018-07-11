import java.io.File;

public class FileUtils {

    public static File[] getXlsFilesFromCurrentDirectory(){
        File directory = new File(".");
        return directory.listFiles((dir, filename) -> filename.endsWith(".xls"));
    }

}
