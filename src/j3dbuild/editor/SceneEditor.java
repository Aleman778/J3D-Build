package j3dbuild.editor;

import j3dbuild.editor.scene.J3DCanvas;
import j3dbuild.editor.scene.SceneSelection;
import j3dbuild.editor.scene.SceneView;
import j3dbuild.editor.scene.Universe;
import j3dbuild.editor.scene.graph.SceneGraph;
import j3dbuild.editor.scene.graph.SceneGraphNode;
import j3dbuild.project.Item;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;

import java.io.File;
import javax.media.j3d.Background;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.J3DGraphics2D;
import javax.media.j3d.Locale;
import javax.vecmath.Point3d;

public class SceneEditor extends Editor {

    public final J3DCanvas canvas;
    public final Universe universe;
    public final SceneSelection selection;
    public final SceneGraph graph;
    public final SceneView view;
    public final Locale locale;
    
    public SceneEditor(Item item) {
        super(item);
        initComponents();
        
        canvas = new J3DCanvas() {


            @Override
            public void postRender() {
                this.getGraphics2D().setColor(Color.white);
                this.getGraphics2D().drawString("Heads Up Display (HUD) Works!",100,100);
                this.getGraphics2D().flush(false);
            }
        };
        universe = new Universe();
        view = new SceneView(this);
        locale = universe.getLocale();
        graph = new SceneGraph(this, new SceneGraphNode(this, "Universe", universe));
        selection = new SceneSelection(this);
        
        BranchGroup group = new BranchGroup();
        Background back = new Background(0.0f, 0.5f, 1.0f);
        back.setApplicationBounds(new BoundingSphere(new Point3d(), 1000.0f));
        group.addChild(back);
        locale.addBranchGraph(group);
        
        setLayout(new BorderLayout());
        add(canvas, BorderLayout.CENTER);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setLayout(new java.awt.BorderLayout());
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    @Override
    public void save() {
    }

    @Override
    public void saveAs(File file) {
    }

    @Override
    public void export(File file) {
    }
}
