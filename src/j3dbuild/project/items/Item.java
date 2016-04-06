package j3dbuild.project.items;

import java.awt.Component;
import java.io.File;

public abstract class Item {
    
    private String title;
    protected File file;
    
    public Item(String title, File file) {
        this.file = file;
    }
    
    public File getFile() {
        return file;
    }
    
    public String getTitle() {
        return title;
    }
    
    public abstract Component getContentUI();
    public abstract Component getLeftUI();
    public abstract Component getRightUI();
    public abstract void repaint();
    
    public abstract void load();
    public abstract void save();
    public abstract void saveAs(File dest);
    public abstract void export(File dest, String ext);
}
