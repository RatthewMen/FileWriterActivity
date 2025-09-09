import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;

public class MyFileWriter {
    public static void main(String[] args) {
        String data = "Hello World";
        String fileName1 = "example.txt";
        String fileName2 = "example2.txt";
        String fileName3 = "example3.txt";
        String fileName4 = "example4.txt";
        String fileName5 = "example5.txt";
        long startTime;
        long endTime;
        String TimesString;
        String SheetsString = "Times: ";
        String TimesFile = "Times.txt";

        // 1. Using FileWriter
        startTime = System.currentTimeMillis();
        try (FileWriter writer = new FileWriter(fileName1)) {
            writer.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        endTime = System.currentTimeMillis();
        TimesString = "FileWriter: " + (endTime - startTime) + " miliseconds. \n";
        SheetsString = SheetsString + (endTime - startTime)+ " ";

        // 2. Using BufferedWriter
        startTime = System.currentTimeMillis();
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName2))) {
            bufferedWriter.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        endTime = System.currentTimeMillis();
        TimesString = TimesString + "BufferedWriter: " + (endTime - startTime) + " miliseconds. \n";
        SheetsString = SheetsString + (endTime - startTime)+ " ";


        // 3. Using FileOutputStream
        startTime = System.currentTimeMillis();
        try (FileOutputStream outputStream = new FileOutputStream(fileName3)) {
            outputStream.write(data.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        endTime = System.currentTimeMillis();
        TimesString = TimesString + "FileOutputStream: " + (endTime - startTime) + " miliseconds. \n";
        SheetsString = SheetsString + (endTime - startTime)+ " ";


        // 4. Using BufferedOutputStream
        startTime = System.currentTimeMillis();
        try (BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(fileName4))) {
            bufferedOutputStream.write(data.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        endTime = System.currentTimeMillis();
        TimesString = TimesString + "BufferedOutputStream: " + (endTime - startTime) + " miliseconds. \n";
        SheetsString = SheetsString + (endTime - startTime)+ " ";

        // 5. Using Files (java.nio.file)
        startTime = System.currentTimeMillis();
        try {
            Files.write(Paths.get(fileName5), data.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
        endTime = System.currentTimeMillis();
        TimesString = TimesString + "Files: " + (endTime - startTime) + " miliseconds. \n";
        SheetsString = SheetsString + (endTime - startTime) + " ";

        TimesString = TimesString + "\n" + SheetsString;
        //6. Printing the times at the end (using filewriter cus idk)
        try (FileWriter writer = new FileWriter(TimesFile)) {
            writer.write(TimesString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void makeSecretFile(String fileName, String password) {
        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(fileName))) {
            bos.write(password.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void fileinSecretFolder(String fileName, String path){
        Path dir = Path.of(path);
        File targetFile = dir.resolve(fileName).toFile();
        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(targetFile))) {

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void printFileSize(String fileName) {
        File f = new File(fileName);
        //f.getPath();
        f.length();
        System.out.println(f.length());

    }

    public static void printFileSizebutPublic(String fileName){
        printFileSize(fileName);
    }
}