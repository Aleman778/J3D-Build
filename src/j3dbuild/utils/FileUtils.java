package j3dbuild.utils;

import java.io.File;

public class FileUtils {

    private FileUtils() {
    }
    
    public static String getFileExtension(File file) {
        String path = file.getPath().trim();
        String[] parts = path.split("\\.");
        if (parts.length == 0) {
            return "";
        }
        
        return parts[parts.length - 1];
    }
    
    public static String getFilename(File file) {
        String filename = file.getName();
        
        int index = filename.lastIndexOf(".");
        if (index >= 0) {
            filename = filename.substring(0, index);
        }
        
        return filename;
    }
}
