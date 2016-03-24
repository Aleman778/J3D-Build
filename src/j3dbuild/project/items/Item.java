package j3dbuild.project.items;

import java.awt.Component;
import java.io.File;

public abstract class Item {
    
    protected File file;
    
    public Item(File file) {
        this.file = file;
    }
    
    public File getFile() {
        return file;
    }
    
    public abstract Component getUI();
    public abstract void repaint();
    
    public abstract void load();
    public abstract void save();
    public abstract void saveAs(File dest);
    public abstract void export(File dest, String ext);
    
}
