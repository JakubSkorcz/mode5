package FileOperations;

import java.io.File;
import java.io.IOException;

public class CreateFile {
    public static void main(String[] args) {
        try{
            File f0 = new File("D:FileOperationExample.txt");
            if(f0.createNewFile()){
                System.out.println("File " + f0.getName() + " is created successfully");
            } else {
                System.out.println("File exist already");
            }
        } catch(IOException exception){
            System.out.println("Unexpected error");
            exception.printStackTrace();
        }
    }
}
