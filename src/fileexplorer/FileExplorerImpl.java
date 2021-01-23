package fileexplorer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;
import java.util.stream.Stream;

public class FileExplorerImpl implements FileExplorer {

    private static final String NEW_LINE = System.lineSeparator();
    private static final String locatie="C:\\Users\\Alex Birle\\Desktop\\ALEX\\AN4\\SEM 1\\SISTEME DISTRIBUITE\\ProiectulMeu_cod\\src\\Fisiere\\";
    /*
    String path = "C:" + File.separator + "hello" + File.separator + "hi.txt";
    // Use relative path for Unix systems
     File f = new File(path);
     f.getParentFile().mkdirs(); 
     f.createNewFile();
    */
    
    @Override
    public String readFileContent(String name) {

        StringBuilder strinBuilder = new StringBuilder();

        File myObj = new File(locatie+name);
        try (Scanner myReader = new Scanner(myObj)) {
            while (myReader.hasNextLine()) {
                strinBuilder.append(myReader.nextLine()).append(NEW_LINE);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return String.format("File %s does not exist", name);
        }
        return strinBuilder.toString();
    }

    @Override
    public String listAllFiles() {

        StringBuilder sb = new StringBuilder();
        try (Stream<Path> paths = Files.list(Paths.get(locatie))) {

            paths.filter(Files::isRegularFile)
                    .map(f -> f.getName(10))
                    .forEach(s -> sb.append(s).append(NEW_LINE));

        } catch (IOException e) {
            e.printStackTrace();
        }

        return sb.toString();
    }

    @Override
    public String deleteFile(String name) {

        if (new File(locatie+name).delete()) {
            return String.format("File %s was deleted!", name);
        }
        return String.format("File %s was not deleted!", name);
    }

    @Override
    public String createFile(String name) {

        try {
            File myObj = new File(locatie+name);
            if (myObj.createNewFile()) {
                return String.format("File %s created!", name);
            } else {
                return String.format("File %s alredy exists!",name);
            }
        } catch (IOException e) {

            e.printStackTrace();
            return String.format("Unknow error occured while creating file %s!",name);
        }

    }

    @Override
    public String writeToFile(String name, String message) {

        try {
            Files.write(Paths.get(locatie+name), message.getBytes(), StandardOpenOption.APPEND);
            return String.format("Text was succsessfully added to %s", name);
        } catch (IOException e) {
            //exception handling left as an exercise for the reader
            return String.format("File %s doesn't exist!", name);
        }

    }

    @Override
    public String createAndWriteToFile(String name, String message) {

        File file = new File(locatie+name);
        try (FileWriter fr = new FileWriter(file)) {
            fr.write(message);
            return String.format("Text was added successfully to %s", name);
        } catch (IOException ex) {
           ex.printStackTrace();
           return String.format("%s doesn't exists!", name);
        }

    }

}
