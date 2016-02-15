package build.editor.ui;

import build.editor.J3DBuild;
import build.editor.scene.SceneView;
import build.editor.scene.Universe;
import build.editor.scene.graph.SceneGraph;
import build.editor.scene.graph.SceneGraphNode;
import build.editor.scene.graph.SceneGraphRenderer;
import build.editor.ui.acomponents.AMenu;
import build.editor.ui.acomponents.AMenuItem;
import build.editor.ui.acomponents.APopupMenu;
import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.ColorCube;
import com.sun.j3d.utils.geometry.Sphere;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Group;
import javax.media.j3d.Leaf;
import javax.media.j3d.Locale;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.swing.SwingUtilities;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import javax.vecmath.Vector3f;

public class JTreeSceneGraph extends JTreeDragAndDrop {
    
    public static final JTreeSceneGraph instance;
    
    private static final APopupMenu POPUP_LOCALE;
    private static final APopupMenu POPUP_GROUP;
    private static final APopupMenu POPUP_LEAF;
    private static SceneGraphNode selectedNode;
    
    static {
        //Instantiate
        instance = new JTreeSceneGraph();
        
        //Locale Popup Menu
        POPUP_LOCALE = new APopupMenu();
        AMenuItem itemGraph = new AMenuItem("Add Branch Graph");
        itemGraph.addActionListener((ActionEvent e) -> {
            BranchGroup group = new BranchGroup();
            SceneGraph.setCapabilities(group);
            group.setName("Branch Group");
            SceneGraph graph = instance.getSceneGraph();
            graph.insertNodeInto(new SceneGraphNode(group), selectedNode);
        });
        POPUP_LOCALE.add(itemGraph);
        
        //Branch Group Popup Menu
        POPUP_GROUP = new APopupMenu();
        POPUP_GROUP.add(new AMenuItem("Copy"));
        POPUP_GROUP.add(new AMenuItem("Paste"));
        POPUP_GROUP.addSeparator();
        POPUP_GROUP.add(new AMenuItem("Rename"));
        POPUP_GROUP.add(new AMenuItem("Duplicate"));
        POPUP_GROUP.add(new AMenuItem("Delete"));
        POPUP_GROUP.addSeparator();
        AMenuItem itemTrans = new AMenuItem("Transform Group");
        itemTrans.addActionListener((ActionEvent e) -> {
            Transform3D tr3d = new Transform3D();
            tr3d.setTranslation(new Vector3f(0, 1, 0));
            TransformGroup transform = new TransformGroup(tr3d);
            transform.setName("Transform Group");
            SceneGraph graph = instance.getSceneGraph();
            graph.insertNodeInto(new SceneGraphNode(transform), selectedNode);
        });
        POPUP_GROUP.add(itemTrans);
        AMenuItem itemBranch = new AMenuItem("Branch Group");
        itemBranch.addActionListener((ActionEvent e) -> {
            BranchGroup branch = new BranchGroup();
            branch.setName("Branch Group");
            SceneGraph graph = instance.getSceneGraph();
            graph.insertNodeInto(new SceneGraphNode(branch), selectedNode);
        });
        POPUP_GROUP.add(itemBranch);
        POPUP_GROUP.addSeparator();
        POPUP_GROUP.add(new AMenuItem("Create Node"));
        AMenu menuPrimitive = new AMenu("Primitive");
        AMenuItem itemBox = new AMenuItem("Box");
        itemBox.addActionListener((ActionEvent e) -> {
            Box box = new Box();
            box.setName("Box");
            SceneGraph graph = instance.getSceneGraph();
            graph.insertNodeInto(new SceneGraphNode(box), selectedNode);
        });
        AMenuItem itemSphere = new AMenuItem("Sphere");
        itemSphere.addActionListener((ActionEvent e) -> {
            Sphere sphere = new Sphere();
            sphere.setName("Sphere");
            SceneGraph graph = instance.getSceneGraph();
            graph.insertNodeInto(new SceneGraphNode(sphere), selectedNode);
        });
        AMenuItem itemColor = new AMenuItem("ColorCube");
        itemColor.addActionListener((ActionEvent e) -> {
            ColorCube cube = new ColorCube();
            cube.setName("Color Cube");
            SceneGraph graph = instance.getSceneGraph();
            graph.insertNodeInto(new SceneGraphNode(cube), selectedNode);
        });
        
        menuPrimitive.add(itemBox);
        menuPrimitive.add(itemSphere);
        menuPrimitive.add(itemColor);
        POPUP_GROUP.add(menuPrimitive);
        POPUP_GROUP.add(new AMenu("Light"));
        POPUP_GROUP.add(new AMenu("Audio"));
        POPUP_GROUP.add(new AMenu("Behaviour"));
        POPUP_GROUP.add(new AMenuItem("Camera"));
        POPUP_GROUP.addSeparator();
        POPUP_GROUP.add(new AMenuItem("Properties"));
        
        //Branch Group Popup Menu
        POPUP_LEAF = new APopupMenu();
        POPUP_LEAF.add(new AMenuItem("Copy"));
        POPUP_LEAF.add(new AMenuItem("Paste"));
        POPUP_LEAF.addSeparator();
        POPUP_LEAF.add(new AMenuItem("Rename"));
        POPUP_LEAF.add(new AMenuItem("Duplicate"));
        POPUP_LEAF.add(new AMenuItem("Delete"));
        POPUP_LEAF.addSeparator();
        POPUP_LEAF.add(new AMenuItem("Properties"));
    }
    
    private JTreeSceneGraph() {
        addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent me) {
                if (SwingUtilities.isRightMouseButton(me)) {
                    TreePath path = getPathForLocation(me.getX(), me.getY());
                    if (path != null) {
                        setSelectionPath(path);
                        try {
                            SceneGraphNode node = (SceneGraphNode) path.getLastPathComponent();
                            selectedNode = node;
                            
                            //Create PopupMenu
                            if (selectedNode.getObject() instanceof Group) {
                                POPUP_GROUP.showMenu(me.getXOnScreen() - J3DBuild.instance.getX(),
                                    me.getYOnScreen() - J3DBuild.instance.getY());
                                
                            } else if (selectedNode.getObject() instanceof Leaf) {
                                POPUP_LEAF.showMenu(me.getXOnScreen() - J3DBuild.instance.getX(),
                                    me.getYOnScreen() - J3DBuild.instance.getY());
                                
                            } else if (selectedNode.getObject() instanceof Locale) {
                                POPUP_LOCALE.showMenu(me.getXOnScreen() - J3DBuild.instance.getX(),
                                    me.getYOnScreen() - J3DBuild.instance.getY());
                                
                            }
                        } catch (ClassCastException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
                
                if (SwingUtilities.isLeftMouseButton(me)) {
                    JPanelSceneEditor editor = J3DBuild.instance.getSceneEditor();
                    
                    if (editor != null && selectedNode != null) {
                        SceneView view = editor.getView();
                        Vector3f translation = new Vector3f();
                        
                        if (selectedNode.getObject() != null) {
                            if (selectedNode.getObject() instanceof TransformGroup) {
                                Transform3D transform = new Transform3D();
                                ((TransformGroup) selectedNode.getObject()).getTransform(transform);
                                transform.get(translation);
                            }
                        }
                        
                        view.interpolateToPoint(translation);
                    }
                }
            }
        });
        
        addTreeSelectionListener(new TreeSelectionListener() {

            @Override
            public void valueChanged(TreeSelectionEvent tse) {
                try {
                    SceneGraphNode node = (SceneGraphNode) tse.getPath().getLastPathComponent();
                    selectedNode = node;
                } catch (ClassCastException ex) {
                    System.out.println(tse.getPath().getLastPathComponent().getClass());
                    ex.printStackTrace();
                }
            }
        });
        
        setCellRenderer(new SceneGraphRenderer());
    }

    public void setSceneGraph(SceneGraph sceneGraph) {
        super.setModel(sceneGraph);
    }

    public SceneGraph getSceneGraph() {
        return (SceneGraph) super.getModel();
    }
}
