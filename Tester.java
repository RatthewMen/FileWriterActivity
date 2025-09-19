public class Tester {
    public static void main(String[] args) {
        //MyFileWriter.makeSecretFile(".theStrings.txt", "deez nuts");
        //MyFileWriter.fileinSecretFolder("homework.txt", ".hiddenStrings");

        //MyFileWriter.printFileSizebutPublic("boar.txt");
        //MyFileWriter.toString("boar.txt");
        //MyFileWriter.hashFile("boar.txt");
        //String boarhash = "5d790795330b9003623dd05d7a63e9bd00806273ce3a033ebee49bbaa52f090b";
        String EmptyHash = "e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855";
        MyFileWriter.testHashFileEmptyFiles(EmptyHash);
        String largeFileHash = "e842bc146b244ac5ac96afff5d32a9e1f27690ef7168fa11c84d09e5c39fd270";
        MyFileWriter.testHashFileLargeFiles(largeFileHash);
        String specialStuff = "ed7b42c9a93f44fd17cf86a6c735e053c651d8ca0df5c6b71a2e2175527eb310";
        MyFileWriter.testHashFileSpecialChars(specialStuff);
        MyFileWriter.testHashFileNonExistent(EmptyHash);

    }
}
