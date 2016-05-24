package j3dbuild.editor;


import j3dbuild.editor.properties.PropertyType;
import j3dbuild.editor.scene.J3DCanvas;
import j3dbuild.editor.scene.SceneGrid;
import j3dbuild.editor.scene.SceneSelection;
import j3dbuild.editor.scene.SceneView;
import j3dbuild.editor.scene.Universe;
import j3dbuild.editor.scene.graph.SceneGraph;
import j3dbuild.editor.scene.graph.SceneGraphNode;
import j3dbuild.editor.scene.graph.SceneGraphUI;
import j3dbuild.editor.ui.*;
import j3dbuild.project.Item;
import j3dbuild.utils.ThemeUtils;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.File;
import java.util.Collection;
import java.util.Enumeration;
import javax.media.j3d.Background;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.DirectionalLight;
import javax.media.j3d.Group;
import javax.media.j3d.Locale;
import javax.media.j3d.Transform3D;
import javax.swing.JTree;
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
        locale.addBranchGraph(group);
        
        //Add default background
        Background background = new Background(0.2f, 0.5f, 1.0f);
        background.setApplicationBounds(new BoundingSphere(new Point3d(), 1000.0));
        background.setName("Background");
        graph.insertNodeInto(new SceneGraphNode(this, background), nodeGroup);
        
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
        
        //Set scene graph model
        jTreeSceneGraph.setModel(graph);
        
        //Set picking canvas
        canvas.setPickingCanvas(group);
        
        //Add Canvas (Update UI)
        add(canvas, BorderLayout.CENTER);
        setBackground(ThemeUtils.COLOR_BACKGROUND);
    }

    public JTree getGraphTree() {
        return jTreeSceneGraph;
    }
    
    public void setProperties(String name, Collection<PropertyType> properties) {
        jPanelProperties.removeAll();
        jPanelProperties.setPreferredSize(new Dimension(jPanelProperties.getWidth(), 0));
        for (PropertyType property: properties) {
            JAccordion accodion = new JAccordion(property.getName());
            accodion.setContent(property);
            if (property.getIcon() != null) {
                accodion.setIcon(property.getIcon());
            }
            accodion.setPreferredSize(new Dimension(jPanelProperties.getWidth() - 16, accodion.getPreferredSize().height));
            jPanelProperties.add(accodion);
        }
        
        jPanelProperties.revalidate();
        jPanelProperties.repaint();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSplitPane1 = new ASplitPane();
        jTabbedSceneGraph = new ATabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTreeSceneGraph = new SceneGraphUI(this);
        jTabbedProperties = new ATabbedPane();
        jPanelProperties = new APanel();

        setLayout(new java.awt.BorderLayout(4, 4));

        jSplitPane1.setDividerLocation(240);
        jSplitPane1.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        jSplitPane1.setPreferredSize(new java.awt.Dimension(260, 207));

        jPanel1.setLayout(new java.awt.BorderLayout());

        jScrollPane1.setBorder(null);

        jTreeSceneGraph.setCellRenderer(new j3dbuild.editor.scene.graph.SceneGraphRenderer());
        jScrollPane1.setViewportView(jTreeSceneGraph);

        jPanel1.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jTabbedSceneGraph.addTab("Scene Graph", jPanel1);

        jSplitPane1.setTopComponent(jTabbedSceneGraph);

        jTabbedProperties.addTab("Properties", jPanelProperties);

        jSplitPane1.setRightComponent(jTabbedProperties);

        add(jSplitPane1, java.awt.BorderLayout.LINE_END);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelProperties;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JTabbedPane jTabbedProperties;
    private javax.swing.JTabbedPane jTabbedSceneGraph;
    private javax.swing.JTree jTreeSceneGraph;
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

    @Override
    public void repaint() {
        if (canvas != null) {
            canvas.getView().repaint();
        }
    }
}
