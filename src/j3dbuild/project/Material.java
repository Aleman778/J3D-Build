package j3dbuild.project;

import j3dbuild.editor.MaterialEditor;
import java.io.File;

public class Material extends Item {

    public Material(Project project, String title, File file) {
        super(project, title, file);
    }

    @Override
    public void edit() {
        MaterialEditor mated = new MaterialEditor(this);
        mated.setVisible(true);
    }
    
    @Override
    public void close() {
        editor = null;
    }
}
