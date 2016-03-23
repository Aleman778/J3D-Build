package j3dbuild.editor.ui;

import java.io.File;

public interface UIEditor {
    
    public File getFile();
    
    public void save();
    
    public void build();
    
    public void export(File target, String type);
}
