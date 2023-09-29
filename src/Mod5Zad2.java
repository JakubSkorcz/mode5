import javax.print.DocFlavor;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLOutput;
import java.util.Scanner;

public class Mod5Zad2 {
    Scanner sc = new Scanner(System.in);
    private String diskPath;
    private String folderPath;

    public void fileOperator() throws IOException {
        String diskPath;
        String folderPath;
        System.out.println("Ustaw sciezke dysku:");
        diskPath = sc.nextLine();
        this.diskPath = diskPath;
        System.out.println("Ustaw folder:");
        folderPath = sc.nextLine();
        this.folderPath = folderPath;
        while(true) {
            boolean exit = false;
            switch (actionSelector()){
                case 0:
                    break;
                case 1:
                    System.out.println("Podaj nazwe pliku do utworzenia:");
                    createFile(diskPath,folderPath);
                    break;
                case 2:
                    printDirectory(diskPath,folderPath);
                    System.out.println("Podaj nazwe pliku do ktorego zapisac tekst:");
                    writeUserInputToFile(diskPath,folderPath);
                    break;
                case 3:
                    printDirectory(diskPath,folderPath);
                    System.out.println("Podaj nazwe pliku z ktorego odczytac tekst:");
                    readFromFile(diskPath,folderPath);
                    break;
                case 4:
                    printDirectory(diskPath,folderPath);
                    System.out.println("Podaj nazwe pliku do odczytu danych:");
                    fileInfo(diskPath,folderPath);
                    break;
                case 5:
                    printDirectory(diskPath,folderPath);
                    System.out.println("Podaj nazwe pliku do usuniecia:");
                    deleteFile(diskPath,folderPath);
                    break;
                case 6:
                    printDirectory(diskPath,folderPath);
                    areTextFilesEqual();
                    break;

                case 7:
                    printDirectory(diskPath,folderPath);
                    invertTextInFile();
                    break;
                case 99:
                    System.out.println("bye bye");
                    exit = true;
            }
            if(exit){
                break;
            }
        }
    }

    public void writeUserInputToFile(String diskPath, String folderPath){
        String filename = sc.nextLine();
        try {
            System.out.print("Twoj tekst: ");
            FileWriter fwrite = new FileWriter(diskPath + ":" + "\\" + folderPath + "\\" + filename + ".txt");
            String userInputToFile = sc.nextLine();
            fwrite.write(jackSpacer(userInputToFile));
            fwrite.close();
            System.out.println("Pomyslnie zapisano tekst do pliku.");
        }catch (IOException e) {
            System.out.println("Nieoczekiwany blad.");
            e.printStackTrace();
        }

    }

    private String jackSpacer(String input){
        int spacer = 0;
        char[] stringToChars = input.toCharArray();
        for(int i = 0; i < input.length();i++){
            if(Character.isSpaceChar(stringToChars[i])){
                spacer++;
                if(spacer == 4){
                    stringToChars[i]  = '\n';
                    spacer = 0;
                }
            }
        }
        return String.valueOf(stringToChars);
    }

    public String readFromFile(String diskPath, String folderPath){
        String filename = sc.nextLine();
        try {
            File f1 = new File(diskPath + ":" + "\\" + folderPath + "\\" + filename + ".txt");
            Scanner dataReader = new Scanner(f1);
            String fileData = null;
            while (dataReader.hasNextLine()) {
                fileData = dataReader.nextLine();
                System.out.println(fileData);
            }
            dataReader.close();
            return fileData;
        } catch (FileNotFoundException exception) {
            System.out.println("Niespodziewany blad.");
            exception.printStackTrace();
            return null;
        }
    }

    private String readTextInFile(String diskPath, String folderPath,String fileName) throws IOException {
        String data = "";
        data = new String(Files.readAllBytes(Paths.get(diskPath + ":" + "\\" + folderPath + "\\" + fileName + ".txt")));
        return data;
    }

    private int actionSelector(){
        System.out.println("---------------------");
        System.out.println("Wybierz czynnosc:");
        System.out.println("1. Utworz plik");
        System.out.println("2. Zapisz do pliku");
        System.out.println("3. Odczyt z pliku");
        System.out.println("4. Dane pliku");
        System.out.println("5. Usun plik");
        System.out.println("6. Porownaj zawartosc plikow");
        System.out.println("7. Odwroc zawartosc pliku");
        System.out.println("99. Wyjscie");
        System.out.print("Twoj wybor: ");
        String input = sc.nextLine();
        if (!input.matches("[0-9]+")) {
            System.out.println("Numer gosciu");
            return 0;
        } else {
            return Integer.parseInt(input);
        }
    }

    private void createFile(String diskPath, String folderPath){

        String filename = sc.nextLine();
        try{
            File f0 = new File(diskPath + ":" + "\\" + folderPath + "\\" + filename + ".txt");
            if(f0.createNewFile()){
                System.out.println("Plik " + f0.getName() + " zostal pomyslnie utworzony");
            } else {
                System.out.println("Plik o takiej nazwie juz istnieje");
            }
        } catch(IOException exception){
            System.out.println("Unexpected error");
            exception.printStackTrace();
        }
    }

    private void fileInfo(String diskPath, String folderPath){

            String fileName = sc.nextLine();
            File f0 = new File(diskPath + ":" + "\\" + folderPath + "\\" + fileName + ".txt");
            if (f0.exists()) {
                System.out.println("---------------------");
                System.out.println("Nazwa pliku: " + f0.getName());
                System.out.println("Sciezka pliku: " + f0.getAbsolutePath());
                System.out.println("Mozliwy zapis: " + f0.canWrite());
                System.out.println("Rozmiar pliku: " + f0.length() + " bajtow.");
            } else {
                System.out.println("Plik o danej nazwie nie istnieje.");
            }
        }

    private void printDirectory(String diskPath, String folderPath){
        File folder = new File(diskPath + ":" + "\\" + folderPath);
        File[] listOfFiles = folder.listFiles();
        assert listOfFiles != null;
        System.out.println("Dostepne pliki w folderze:");
        for (File listOfFile : listOfFiles) {
            if (listOfFile.isFile()) {
                System.out.println(listOfFile.getName());
            } else if (listOfFile.isDirectory()) {
                System.out.println(listOfFile.getName());
            }
        }
    }

    private void deleteFile(String diskPath, String folderPath){

        String fileName = sc.nextLine();
        File f0 = new File(diskPath + ":" + "\\" + folderPath + "\\" + fileName + ".txt");
        if(f0.exists()){
            f0.delete();
            System.out.println("Pomyslnie usunieto plik.");
        } else {
            System.out.println("Nie ma takiego pliku5.");
        }
    }

    private void areTextFilesEqual() throws IOException {
        System.out.println("Pierwszy plik");
        String file1 = sc.nextLine();
        String tekst1 = readTextInFile(diskPath, folderPath, file1);
        System.out.println("Drugi plik");
        String file2 = sc.nextLine();
        String tekst2 = readTextInFile(diskPath, folderPath,file2);
        // dodac obsluge bledow dla nullow
        if(tekst1.equals(tekst2)){
            System.out.println("Zawartosc plikow jest identyczna.");
        } else {
            System.out.println("Zawartosc plikow jest rozna.");
        }

    }

    private String invertTextInFile () throws IOException {
        System.out.println("Podaj nazwe pliku do odwrocenia tekstu:");
        String filename = sc.nextLine();
        String normalOrder = readTextInFile(diskPath, folderPath,filename);
        StringBuilder invertedString = new StringBuilder();
        invertedString.append(normalOrder);
        invertedString.reverse();
        System.out.println(invertedString);
        System.out.println("Do jakiego pliku zapisac?");
        String fileNameToSave = sc.nextLine();
        FileWriter fwrite = new FileWriter(diskPath + ":" + "\\" + folderPath + "\\" + fileNameToSave + ".txt");
        fwrite.write(jackSpacer(String.valueOf(invertedString)));
        fwrite.close();
        System.out.println("Pomyslnie zapisano tekst do pliku.");
        return String.valueOf(invertedString);
    }

}
