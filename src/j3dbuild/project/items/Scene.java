package j3dbuild.project.items;

import j3dbuild.editor.scene.J3DCanvas;
import j3dbuild.editor.scene.SceneSelection;
import j3dbuild.editor.scene.SceneView;
import j3dbuild.editor.scene.Universe;
import j3dbuild.editor.scene.graph.SceneGraph;
import j3dbuild.editor.scene.graph.SceneGraphNode;
import j3dbuild.editor.ui.SceneEditor;
import j3dbuild.editor.ui.SceneGraphUI;
import j3dbuild.editor.ui.acomponents.APanel;
import java.awt.Component;
import java.io.File;
import javax.media.j3d.Locale;

public class Scene extends Item {
    
    public final J3DCanvas canvas;
    public final Universe universe;
    public final SceneSelection selection;
    public final SceneGraph graph;
    public final SceneView view;
    public final Locale locale;
    
    public Scene(String title, File file) {
        super(title, file);
        
        canvas = new J3DCanvas();
        universe = new Universe();
        view = new SceneView(this);
        locale = universe.getLocale();
        graph = new SceneGraph(this, new SceneGraphNode(this, "Universe", universe));
        selection = new SceneSelection(this);
    }
    
    

    @Override
    public Component getContentUI() {
        return new SceneEditor(this);
    }

    @Override
    public Component getLeftUI() {
        return new SceneGraphUI(this);
    }

    @Override
    public Component getRightUI() {
        APanel panel = new APanel();
        panel.setName("Test");
        return panel;
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
