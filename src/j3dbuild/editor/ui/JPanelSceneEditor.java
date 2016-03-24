package j3dbuild.editor.ui;

import j3dbuild.editor.scene.SceneCanvas;
import j3dbuild.editor.ui.acomponents.*;
import j3dbuild.project.items.Scene;
import java.awt.BorderLayout;
import javax.media.j3d.Canvas3D;

public class JPanelSceneEditor extends APanel {

    private final Scene scene;
    private final Canvas3D canvas;
    
    public JPanelSceneEditor(Scene scene) {
        initComponents();
        this.scene = scene;
        
        //Set up Scene
        canvas = new SceneCanvas(false);
        
        
        setLayout(new BorderLayout());
        add(canvas, BorderLayout.CENTER);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar = new AToolBar();

        setLayout(new java.awt.BorderLayout());

        jToolBar.setRollover(true);
        jToolBar.setMaximumSize(new java.awt.Dimension(13, 28));
        jToolBar.setMinimumSize(new java.awt.Dimension(13, 28));
        jToolBar.setPreferredSize(new java.awt.Dimension(13, 28));
        add(jToolBar, java.awt.BorderLayout.PAGE_START);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToolBar jToolBar;
    // End of variables declaration//GEN-END:variables

    @Override
    public void repaint() {
        
    }

    public Scene getScene() {
        return scene;
    }
}
