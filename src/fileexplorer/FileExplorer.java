package fileexplorer;



public interface FileExplorer {

    String readFileContent(String name);
    
    String listAllFiles();
    
    String deleteFile(String name);
    
    String createFile(String name);
    
    String writeToFile(String name,String message);
    
    String createAndWriteToFile(String name,String message);
}
