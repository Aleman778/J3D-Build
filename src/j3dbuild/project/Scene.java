package j3dbuild.project;

import j3dbuild.core.Application;
import j3dbuild.editor.SceneEditor;
import java.io.File;

public class Scene extends Item {

    public Scene(Project project, String title, File file) {
        super(project, title, file);
    }

    @Override
    public void edit() {
        SceneEditor editor = new SceneEditor(this);
        Application.instance.addEditor(editor);
    }
    
    @Override
    public void close() {
        
    }
}
