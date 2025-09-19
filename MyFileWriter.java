import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.security.MessageDigest;

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
    // methods 

    //makes a secret file
    public static void makeSecretFile(String fileName, String password) {
        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(fileName))) {
            bos.write(password.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //makes the file in the secret folder
    public static void fileinSecretFolder(String fileName, String path){
        Path dir = Path.of(path);
        File targetFile = dir.resolve(fileName).toFile();
        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(targetFile))) {

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //prints out the file size
    private static void printFileSize(String fileName) {
        File f = new File(fileName);
        //f.getPath();
        f.length();
        System.out.println(f.length());

    }

    //tester method for public file size
    public static void printFileSizebutPublic(String fileName){
        printFileSize(fileName);
    }
    

    //prints out the filename
    public static String toString(String fileName){
        File f = new File(fileName);
        //f.getPath();
        f.length();
        System.out.println(f.toString());
        return f.toString();
   
    }

    //Hashes with the SHA-256 algorithim
    public static String hashFile(String filePath){
        try{
            byte[] Bytes = Files.readAllBytes(Paths.get(filePath));

            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(Bytes);

            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                hexString.append(String.format("%02x", b));
            }
            //System.out.println("The hash for " + Files.readString(Paths.get(filePath)) + " is: " + hexString.toString());

            return hexString.toString();

        } catch (java.nio.file.NoSuchFileException e) {
            System.err.println("File not found: " + filePath);
            return null;
    
        } catch (IOException e) {
            System.err.println("I/O error while reading file: " + filePath);
            return null;
        
        } catch (Exception e){
            System.out.println("DUMB ERROR FOR File : " + filePath);
            return null;
        }
    }
    
    //compares 2 strings or hashes to see if they are equal
    public static String compareHash(String hash1, String hash2){
        if (hash1.equals(hash2)){
            return "Hashes are equal";
        }
        return "NOT EQUAL WRONG BOZO";
    }

    //test empty files. Apparently empty files have a hash
    public static void testHashFileEmptyFiles(String emptyHash){
        System.out.println(compareHash(emptyHash, hashFile("emptyfile.txt")));
    }

    //tests large files and takes a second to work
    public static void testHashFileLargeFiles(String bigFile){
        System.out.println(compareHash(bigFile, hashFile("largetext.txt")));
    }

    //works with unicode
    public static void testHashFileSpecialChars(String specialChars){
        System.out.println(compareHash(specialChars, hashFile("FunnyCharacters.txt")));
    }

    //throws the file not found exception
    public static void testHashFileNonExistent(String idkbruh){
        System.out.println(compareHash(idkbruh, hashFile("realfile.txt")));
    }



    //public static void 


}
