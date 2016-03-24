package j3dbuild.project.items;

import j3dbuild.editor.ui.JPanelSceneEditor;
import java.awt.Component;
import java.io.File;

public class Scene extends Item {

    
    
    public Scene(File file) {
        super(file);
    }
    
    @Override
    public Component getUI() {
        return new JPanelSceneEditor(this);
    }

    @Override
    public void repaint() {
    }

    @Override
    public void load() {
    }

    @Override
    public void save() {
    }

    @Override
    public void saveAs(File dest) {
    }

    @Override
    public void export(File dest, String ext) {
    }
}
