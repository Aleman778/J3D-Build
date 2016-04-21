package j3dbuild.utils;

import java.io.File;

public class FileUtils {

    private FileUtils() {
    }
    
    public static String getFileExtension(File file) {
        String path = file.getPath().trim();
        String[] parts = path.split("\\.");
        return parts[parts.length - 1];
    }
    
}
