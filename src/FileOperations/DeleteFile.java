package FileOperations;

import java.io.File;

public class DeleteFile {
    public static void main(String[] args) {
        File f0 = new File("D:FileOperationExample.txt");
        if(f0.delete()){
            System.out.println("deleted");
        } else {
            System.out.println("error");
        }
    }
}
