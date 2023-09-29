package FileOperations;

import java.io.File;

public class FileInfo {
    public static void main(String[] args) {
        File f0 = new File("D:FileOperationExample.txt");

        if(f0.exists()){
            System.out.println(f0.getName());
            System.out.println(f0.getAbsolutePath());
            System.out.println(f0.canWrite());
            System.out.println(f0.length());
        } else {
            System.out.println("Not exist");
        }

    }
}
