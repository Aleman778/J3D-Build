package j3dbuild.editor.ui;

import j3dbuild.editor.Editor;
import j3dbuild.editor.scene.SceneView;
import j3dbuild.editor.scene.graph.SceneGraph;
import j3dbuild.editor.scene.graph.SceneGraphNode;
import j3dbuild.editor.scene.graph.SceneGraphRenderer;
import j3dbuild.editor.ui.acomponents.AMenu;
import j3dbuild.editor.ui.acomponents.AMenuItem;
import j3dbuild.editor.ui.acomponents.APopupMenu;
import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.Cone;
import com.sun.j3d.utils.geometry.Cylinder;
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
import javax.swing.tree.TreePath;
import javax.vecmath.Vector3f;

public class JTreeSceneGraph extends JTreeDragAndDrop {
    
    public static final JTreeSceneGraph instance;
    
    private static final APopupMenu POPUP_LOCALE;
    private static final APopupMenu POPUP_GROUP;
    private static final APopupMenu POPUP_LEAF;
    
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
            graph.insertNodeInto(new SceneGraphNode(group), graph.getSingleSelectedNode());
        });
        POPUP_LOCALE.add(itemGraph);
        
        //Branch Group Popup Menu
        POPUP_GROUP = new APopupMenu();
        POPUP_GROUP.add(new AMenuItem("Copy"));
        POPUP_GROUP.add(new AMenuItem("Paste"));
        POPUP_GROUP.addSeparator();
        AMenuItem itemRename = new AMenuItem("Rename");
        itemRename.addActionListener((ActionEvent e) -> {
            SceneGraph graph = instance.getSceneGraph();
            SceneGraphNode node = graph.getSingleSelectedNode();
            if (node != null) {
                instance.startEditingAtPath(JTreeDragAndDrop.getPath(node.getPath()[node.getPath().length - 1]));
            }
        });
        POPUP_GROUP.add(itemRename);
        POPUP_GROUP.add(new AMenuItem("Duplicate"));
        AMenuItem itemDelete = new AMenuItem("Delete");
        itemDelete.addActionListener((ActionEvent e) -> {
            SceneGraph graph = instance.getSceneGraph();
            graph.removeNode(graph.getSingleSelectedNode());
            graph.clearSelectedNodes();
        });
        POPUP_GROUP.add(itemDelete);
        POPUP_GROUP.addSeparator();
        AMenuItem itemTrans = new AMenuItem("Transform Group");
        itemTrans.addActionListener((ActionEvent e) -> {
            TransformGroup transform = new TransformGroup();
            transform.setName("Transform Group");
            SceneGraph graph = instance.getSceneGraph();
            SceneGraph.setCapabilities(transform);
            graph.modifyNodeParent(graph.getSingleSelectedNode(), new SceneGraphNode(transform));
        });
        POPUP_GROUP.add(itemTrans);
        AMenuItem itemBranch = new AMenuItem("Branch Group");
        itemBranch.addActionListener((ActionEvent e) -> {
            BranchGroup branch = new BranchGroup();
            branch.setName("Branch Group");
            SceneGraph graph = instance.getSceneGraph();
            SceneGraph.setCapabilities(branch);
            graph.modifyNodeParent(graph.getSingleSelectedNode(), new SceneGraphNode(branch));
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
            graph.insertNodeInto(new SceneGraphNode(box), graph.getSingleSelectedNode());
        });
        AMenuItem itemSphere = new AMenuItem("Sphere");
        itemSphere.addActionListener((ActionEvent e) -> {
            Sphere sphere = new Sphere();
            sphere.setName("Sphere");
            SceneGraph graph = instance.getSceneGraph();
            graph.insertNodeInto(new SceneGraphNode(sphere), graph.getSingleSelectedNode());
        });
        AMenuItem itemCylinder = new AMenuItem("Cylinder");
        itemCylinder.addActionListener((ActionEvent e) -> {
            Cylinder cylinder = new Cylinder();
            cylinder.setName("Cylinder");
            SceneGraph graph = instance.getSceneGraph();
            graph.insertNodeInto(new SceneGraphNode(cylinder), graph.getSingleSelectedNode());
        });
        AMenuItem itemCone = new AMenuItem("Cone");
        itemCone.addActionListener((ActionEvent e) -> {
            Cone cone = new Cone();
            cone.setName("Cone");
            SceneGraph graph = instance.getSceneGraph();
            graph.insertNodeInto(new SceneGraphNode(cone), graph.getSingleSelectedNode());
        });
        AMenuItem itemColor = new AMenuItem("ColorCube");
        itemColor.addActionListener((ActionEvent e) -> {
            ColorCube cube = new ColorCube();
            cube.setName("Color Cube");
            SceneGraph graph = instance.getSceneGraph();
            graph.insertNodeInto(new SceneGraphNode(cube), graph.getSingleSelectedNode());
        });
        menuPrimitive.add(itemBox);
        menuPrimitive.add(itemSphere);
        menuPrimitive.add(itemCylinder);
        menuPrimitive.add(itemCone);
        menuPrimitive.add(itemColor);
        POPUP_GROUP.add(menuPrimitive);
        POPUP_GROUP.add(new AMenu("Light"));
        POPUP_GROUP.add(new AMenu("Audio"));
        POPUP_GROUP.add(new AMenu("Behaviour"));
        POPUP_GROUP.add(new AMenuItem("Camera"));
        POPUP_GROUP.addSeparator();
        AMenuItem itemProperties = new AMenuItem("Properties");
        itemProperties.addActionListener((ActionEvent e) -> {
            SceneGraph graph = instance.getSceneGraph();
            graph.showNodeProporties(graph.getSingleSelectedNode());
        });
        POPUP_GROUP.add(itemProperties);
        
        //Leaf Group Popup Menu
        POPUP_LEAF = new APopupMenu();
        POPUP_LEAF.add(new AMenuItem("Copy"));
        POPUP_LEAF.add(new AMenuItem("Paste"));
        POPUP_LEAF.addSeparator();
        AMenuItem itemRename2 = new AMenuItem("Rename");
        itemRename2.addActionListener((ActionEvent e) -> {
            SceneGraph graph = instance.getSceneGraph();
            SceneGraphNode node = graph.getSingleSelectedNode();
            if (node != null) {
                instance.startEditingAtPath(JTreeDragAndDrop.getPath(node.getPath()[node.getPath().length - 1]));
            }
        });
        POPUP_LEAF.add(itemRename2);
        POPUP_LEAF.add(new AMenuItem("Duplicate"));
        AMenuItem itemDelete2 = new AMenuItem("Delete");
        itemDelete2.addActionListener((ActionEvent e) -> {
            SceneGraph graph = instance.getSceneGraph();
            graph.removeNode(graph.getSingleSelectedNode());
            graph.clearSelectedNodes();
        });
        POPUP_LEAF.add(itemDelete2);
        POPUP_LEAF.addSeparator();
        AMenuItem itemProperties2 = new AMenuItem("Properties");
        itemProperties2.addActionListener((ActionEvent e) -> {
            SceneGraph graph = instance.getSceneGraph();
            graph.showNodeProporties(graph.getSingleSelectedNode());
        });
        POPUP_LEAF.add(itemProperties2);
    }
    
    private JTreeSceneGraph() {
        addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent me) {
                SceneGraph graph = instance.getSceneGraph();
                if (SwingUtilities.isRightMouseButton(me)) {
                    TreePath path = getPathForLocation(me.getX(), me.getY());
                    if (path != null) {
                        try {
                            SceneGraphNode node = (SceneGraphNode) path.getLastPathComponent();
                            graph.setSelectionNode(node);
                            
                            //Create PopupMenu
                            showPopupMenu(node, me);
                        } catch (ClassCastException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
                
                if (SwingUtilities.isLeftMouseButton(me)) {
//                    JPanelSceneEditor editor = Editor.instance.getSceneEditor();
//                    SceneGraphNode node = graph.getSingleSelectedNode();
//                    
//                    if (editor != null && node != null) {
//                        SceneView view = editor.getView();
//                        Vector3f translation = new Vector3f();
//                        
//                        if (node.getObject() != null) {
//                            if (node.getObject() instanceof TransformGroup) {
//                                Transform3D transform = new Transform3D();
//                                ((TransformGroup) node.getObject()).getTransform(transform);
//                                transform.get(translation);
//                            }
//                        }
//                        
//                        view.interpolateToPoint(translation);
//                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent me) {
                SceneGraph graph = instance.getSceneGraph();
                TreePath[] paths = instance.getSelectionModel().getSelectionPaths();
                graph.clearSelectedNodes();
                for (TreePath path: paths) {
                    graph.addSelectionNode((SceneGraphNode) path.getLastPathComponent());
                }
            }
        });

        setEditable(true);
        setCellRenderer(new SceneGraphRenderer());
    }
    
    public static void showPopupMenu(SceneGraphNode node, MouseEvent me) {
        if (node.getObject() instanceof Group) {
            POPUP_GROUP.showMenu(me.getXOnScreen() - Editor.instance.getX(),
                me.getYOnScreen() - Editor.instance.getY());

        } else if (node.getObject() instanceof Leaf) {
            POPUP_LEAF.showMenu(me.getXOnScreen() - Editor.instance.getX(),
                me.getYOnScreen() - Editor.instance.getY());

        } else if (node.getObject() instanceof Locale) {
            POPUP_LOCALE.showMenu(me.getXOnScreen() - Editor.instance.getX(),
                me.getYOnScreen() - Editor.instance.getY());

        }
    }

    public void setSceneGraph(SceneGraph sceneGraph) {
        super.setModel(sceneGraph);
    }

    public SceneGraph getSceneGraph() {
        return (SceneGraph) super.getModel();
    }
}
