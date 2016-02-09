package build.editor.ui;

import build.editor.scene.SceneCanvas;
import build.editor.scene.SceneGrid;
import build.editor.scene.SceneView;
import build.editor.scene.Universe;
import build.editor.scene.graph.SceneGraph;
import build.editor.scene.graph.SceneGraphNode;
import java.awt.BorderLayout;
import java.io.File;
import javax.media.j3d.Background;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.DirectionalLight;
import javax.media.j3d.Locale;
import javax.media.j3d.Transform3D;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3f;

public class JPanelSceneEditor extends javax.swing.JPanel implements UIEditor {

    private final SceneGraphNode nodeUniverse;
    private final SceneGraphNode nodeLocale;
    private final Universe universe;
    private final SceneCanvas canvas;
    private final SceneGraph graph;
    private final SceneView view;
    private final File file;
    
    public JPanelSceneEditor(File file) {
        initComponents();
        this.file = file;
        
        //Set up Scene
        canvas = new SceneCanvas(false);
        view = new SceneView();
        view.addCanvas3D(canvas);
        Transform3D transform = new Transform3D();
        transform.setTranslation(new Vector3f(0, 0, 8.0f));
        view.setTransform(transform);
        view.setCameraOutput(canvas);
        universe = new Universe();
        universe.addSceneView(view);
        view.setPickingCanvas(canvas, universe.getLocale());
        
        //Scene Graph
        nodeUniverse = new SceneGraphNode("Universe", universe);
        nodeLocale = new SceneGraphNode("Locale", universe.getLocale());
        graph = new SceneGraph(nodeUniverse, true, universe.getLocale());
        graph.insertNodeInto(nodeLocale, nodeUniverse);
        
        //Add root BranchGroup
        BranchGroup group = new BranchGroup();
        group.setName("Branch Group");
        SceneGraph.setCapabilities(group);
        SceneGraphNode nodeGroup = new SceneGraphNode(group);
        graph.insertNodeInto(nodeGroup, nodeLocale);
        
        //Add default background
        Background background = new Background(0.2f, 0.5f, 1.0f);
        background.setApplicationBounds(new BoundingSphere());
        background.setName("Background");
        graph.insertNodeInto(new SceneGraphNode(background), nodeGroup);
        
        //Add a light
        Color3f light1Color = new Color3f(1.8f, 0.1f, 0.1f);
        BoundingSphere bounds = new BoundingSphere(new Point3d(0, 0, 0), 100.0);
        Vector3f light1Direction = new Vector3f(4.0f, -7.0f, -12.0f);
        DirectionalLight light = new DirectionalLight(light1Color, light1Direction);
        light.setInfluencingBounds(bounds);
        light.setName("Directional Light");
        graph.insertNodeInto(new SceneGraphNode(light), nodeGroup);
        
        //Scene Grid
        SceneGrid grid = new SceneGrid();
        BranchGroup groupGrid = new BranchGroup();
        groupGrid.addChild(grid);
        Locale locale = universe.getLocale();
        locale.addBranchGraph(groupGrid);
        
        JTreeSceneGraph.instance.expandTree();
        
        setLayout(new BorderLayout());
        add(canvas, BorderLayout.CENTER);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1004, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 566, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    public SceneGraph getSceneGraph() {
        return graph;
    }

    public Universe getUniverse() {
        return universe;
    }

    public SceneCanvas getCanvas() {
        return canvas;
    }

    public SceneGraph getGraph() {
        return graph;
    }

    public SceneView getView() {
        return view;
    }
    
    @Override
    public void repaint() {
        super.repaint();
        if (view != null) {
            view.repaint();
        }
    }
    
    @Override
    public File getFile() {
        return file;
    }

    @Override
    public void save() {
    }

    @Override
    public void build() {
    }

    @Override
    public void export(File target, String type) {
    }
}
