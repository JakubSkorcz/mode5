import java.io.FileWriter;
import java.io.IOException;

public class WriteToFile {
    public static void main(String[] args) {
        try {
            FileWriter fwrite = new FileWriter("D:FileOperationExample.txt");
            fwrite.write("A named location used to store related information is referred to as a File.");
            fwrite.close();
            System.out.println("Content is successfully wrote to the file.");
        }catch (IOException e) {
            System.out.println("Unexpected error occurred");
            e.printStackTrace();
        }
    }
}
