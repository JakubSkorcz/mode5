import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Mod5Zad1 {
    public void printDirectory(String directoryPath){
        File folder = new File(directoryPath);
        File[] listOfFiles = folder.listFiles();
        assert listOfFiles != null;
        for (File listOfFile : listOfFiles) {
            if (listOfFile.isFile()) {
                System.out.println(listOfFile.getName());
            } else if (listOfFile.isDirectory()) {
                System.out.println(listOfFile.getName());
            }
        }
    }
}
