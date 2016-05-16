package j3dbuild.editor;

import j3dbuild.editor.ui.APanel;
import j3dbuild.project.Item;
import java.awt.Color;
import java.io.File;

public abstract class Editor extends APanel {
    
    protected Item item;

    public Editor(Item item) {
        this.item = item;
        setBackground(Color.yellow);
    }

    public Item getItem() {
        return item;
    }
    
    public abstract void save();
    public abstract void saveAs(File file);
    public abstract void export(File file);
}
