package j3dbuild.editor;

import com.sun.j3d.utils.geometry.ColorCube;
import com.sun.j3d.utils.universe.SimpleUniverse;
import j3dbuild.editor.scene.J3DCanvas;
import j3dbuild.editor.scene.SceneGrid;
import j3dbuild.editor.scene.SceneSelection;
import j3dbuild.editor.scene.SceneView;
import j3dbuild.editor.scene.Universe;
import j3dbuild.editor.scene.graph.SceneGraph;
import j3dbuild.editor.scene.graph.SceneGraphNode;
import j3dbuild.project.Item;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.Enumeration;
import javax.media.j3d.Background;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.DirectionalLight;
import javax.media.j3d.Group;
import javax.media.j3d.Locale;
import javax.media.j3d.Transform3D;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3f;

public class SceneEditor extends Editor {

    public final SceneGraphNode nodeUniverse;
    public final SceneGraphNode nodeLocale;
    public final J3DCanvas canvas;
    public final Universe universe;
    public final SceneSelection selection;
    public final SceneGraph graph;
    public final SceneView view;
    public final Locale locale;
    public BranchGroup group;
    
    public SceneEditor(Item item) {
        super(item);
        initComponents();
        
        canvas = new J3DCanvas();
        view = new SceneView(this);
        view.addCanvas3D(canvas);
        Transform3D transform = new Transform3D();
        transform.setTranslation(new Vector3f(0, 0, 8.0f));
        view.setTransform(transform);
        view.setCameraOutput(canvas);
        universe = new Universe();
        locale = new Locale(universe);
        selection = new SceneSelection(this);
        locale.addBranchGraph(view.getBranchGraph());
        
        //Scene Graph
        nodeUniverse = new SceneGraphNode(this, "Universe", universe);
        nodeLocale = new SceneGraphNode(this, "Locale", locale);
        graph = new SceneGraph(this, nodeUniverse);
        graph.insertNodeInto(nodeLocale, nodeUniverse);
        
        //Add root BranchGroup
        group = new BranchGroup();
        group.setName("Branch Group");
        SceneGraph.setCapabilities(group);
        SceneGraphNode nodeGroup = new SceneGraphNode(this, group);
        graph.insertNodeInto(nodeGroup, nodeLocale);
        
        
        //Add default background
        Background background = new Background(0.2f, 0.5f, 1.0f);
        background.setApplicationBounds(new BoundingSphere(new Point3d(), 1000.0));
        background.setName("Background");
        graph.insertNodeInto(new SceneGraphNode(this, background), nodeGroup);
        locale.addBranchGraph(group);
        
        //Add a light
        Color3f light1Color = new Color3f(1.8f, 0.1f, 0.1f);
        BoundingSphere bounds = new BoundingSphere(new Point3d(0, 0, 0), 100.0);
        Vector3f light1Direction = new Vector3f(4.0f, -7.0f, -12.0f);
        DirectionalLight light = new DirectionalLight(light1Color, light1Direction);
        light.setInfluencingBounds(bounds);
        light.setName("Directional Light");
        graph.insertNodeInto(new SceneGraphNode(this, light), nodeGroup);

        //Scene Grid
        SceneGrid grid = new SceneGrid();
        BranchGroup groupGrid = new BranchGroup();
        groupGrid.addChild(grid);
        locale.addBranchGraph(groupGrid);
        
        setLayout(new BorderLayout());
        
        canvas.setName("c3d");
        add(canvas, BorderLayout.CENTER);
        
        System.out.println(group);
        dump(group, "   ");
    }

    private void dump(Group group, String indent) {
        Enumeration enumeration = group.getAllChildren();
        
        while (enumeration.hasMoreElements()) {
            Object obj = enumeration.nextElement();
            System.out.println(indent + obj);
            if (obj instanceof Group) {
                dump((Group) obj, indent + "    ");
            }
        }
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
