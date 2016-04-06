package j3dbuild.editor.ui;

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
import j3dbuild.project.items.Scene;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Group;
import javax.media.j3d.Leaf;
import javax.media.j3d.Locale;
import javax.media.j3d.TransformGroup;
import javax.swing.SwingUtilities;
import javax.swing.tree.TreePath;

public class SceneGraphUI extends JTreeDragAndDrop {
    
    private final Scene scene;
    
    private APopupMenu popupLocale;
    private APopupMenu popupGroup;
    private APopupMenu popupLeaf;
    
    public SceneGraphUI(Scene scene) {
        this.scene = scene;
        
        addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent me) {
                if (SwingUtilities.isRightMouseButton(me)) {
                    TreePath path = getPathForLocation(me.getX(), me.getY());
                    if (path != null) {
                        try {
                            SceneGraphNode node = (SceneGraphNode) path.getLastPathComponent();
                            scene.selection.set(node);
                            
                            //Create PopupMenu
                            showPopupMenu(node, me);
                        } catch (ClassCastException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
                
                if (SwingUtilities.isLeftMouseButton(me)) {
//                    JPanelSceneEditor editor = Editor.getSceneEditor();
//                    SceneGraphNode node = scene.selection.getSingle();
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
                TreePath[] paths = getSelectionModel().getSelectionPaths();
                scene.selection.clear();
                for (TreePath path: paths) {
                    scene.selection.add((SceneGraphNode) path.getLastPathComponent());
                }
            }
        });

        setEditable(true);
        setCellRenderer(new SceneGraphRenderer());
    }
    
    public void showPopupMenu(SceneGraphNode node, MouseEvent me) {
        if (node.getObject() instanceof Group) {
            popupGroup.showMenu(me.getXOnScreen() - getX(),
                me.getYOnScreen() - getY());

        } else if (node.getObject() instanceof Leaf) {
            popupLeaf.showMenu(me.getXOnScreen() - getX(),
                me.getYOnScreen() - getY());

        } else if (node.getObject() instanceof Locale) {
            popupLocale.showMenu(me.getXOnScreen() - getX(),
                me.getYOnScreen() - getY());

        }
    }

    public void setSceneGraph(SceneGraph sceneGraph) {
        super.setModel(sceneGraph);
    }
    
    private void setPopupMenu() {
        
        //Locale Popup Menu
        popupLocale = new APopupMenu();
        AMenuItem itemGraph = new AMenuItem("Add Branch Graph");
        itemGraph.addActionListener((ActionEvent e) -> {
            BranchGroup group = new BranchGroup();
            SceneGraph.setCapabilities(group);
            group.setName("Branch Group");
            scene.graph.insertNodeInto(new SceneGraphNode(scene, group), scene.selection.getSingle());
        });
        popupLocale.add(itemGraph);
        
        //Branch Group Popup Menu
        popupGroup = new APopupMenu();
        popupGroup.add(new AMenuItem("Copy"));
        popupGroup.add(new AMenuItem("Paste"));
        popupGroup.addSeparator();
        AMenuItem itemRename = new AMenuItem("Rename");
        itemRename.addActionListener((ActionEvent e) -> {
            SceneGraphNode node = scene.selection.getSingle();
            if (node != null) {
                startEditingAtPath(JTreeDragAndDrop.getPath(node.getPath()[node.getPath().length - 1]));
            }
        });
        popupGroup.add(itemRename);
        popupGroup.add(new AMenuItem("Duplicate"));
        AMenuItem itemDelete = new AMenuItem("Delete");
        itemDelete.addActionListener((ActionEvent e) -> {
            scene.graph.removeNode(scene.selection.getSingle());
            scene.selection.clear();
        });
        popupGroup.add(itemDelete);
        popupGroup.addSeparator();
        AMenuItem itemTrans = new AMenuItem("Transform Group");
        itemTrans.addActionListener((ActionEvent e) -> {
            TransformGroup transform = new TransformGroup();
            transform.setName("Transform Group");
            SceneGraph.setCapabilities(transform);
            scene.graph.modifyNodeParent(scene.selection.getSingle(), new SceneGraphNode(scene, transform));
        });
        popupGroup.add(itemTrans);
        AMenuItem itemBranch = new AMenuItem("Branch Group");
        itemBranch.addActionListener((ActionEvent e) -> {
            BranchGroup branch = new BranchGroup();
            branch.setName("Branch Group");
            SceneGraph.setCapabilities(branch);
            scene.graph.modifyNodeParent(scene.selection.getSingle(), new SceneGraphNode(scene, branch));
        });
        popupGroup.add(itemBranch);
        popupGroup.addSeparator();
        popupGroup.add(new AMenuItem("Create Node"));
        AMenu menuPrimitive = new AMenu("Primitive");
        AMenuItem itemBox = new AMenuItem("Box");
        itemBox.addActionListener((ActionEvent e) -> {
            Box box = new Box();
            box.setName("Box");
            scene.graph.insertNodeInto(new SceneGraphNode(scene, box), scene.selection.getSingle());
        });
        AMenuItem itemSphere = new AMenuItem("Sphere");
        itemSphere.addActionListener((ActionEvent e) -> {
            Sphere sphere = new Sphere();
            sphere.setName("Sphere");
            scene.graph.insertNodeInto(new SceneGraphNode(scene, sphere), scene.selection.getSingle());
        });
        AMenuItem itemCylinder = new AMenuItem("Cylinder");
        itemCylinder.addActionListener((ActionEvent e) -> {
            Cylinder cylinder = new Cylinder();
            cylinder.setName("Cylinder");
            scene.graph.insertNodeInto(new SceneGraphNode(scene, cylinder), scene.selection.getSingle());
        });
        AMenuItem itemCone = new AMenuItem("Cone");
        itemCone.addActionListener((ActionEvent e) -> {
            Cone cone = new Cone();
            cone.setName("Cone");
            scene.graph.insertNodeInto(new SceneGraphNode(scene, cone), scene.selection.getSingle());
        });
        AMenuItem itemColor = new AMenuItem("ColorCube");
        itemColor.addActionListener((ActionEvent e) -> {
            ColorCube cube = new ColorCube();
            cube.setName("Color Cube");
            scene.graph.insertNodeInto(new SceneGraphNode(scene, cube), scene.selection.getSingle());
        });
        menuPrimitive.add(itemBox);
        menuPrimitive.add(itemSphere);
        menuPrimitive.add(itemCylinder);
        menuPrimitive.add(itemCone);
        menuPrimitive.add(itemColor);
        popupGroup.add(menuPrimitive);
        popupGroup.add(new AMenu("Light"));
        popupGroup.add(new AMenu("Audio"));
        popupGroup.add(new AMenu("Behaviour"));
        popupGroup.add(new AMenuItem("Camera"));
        popupGroup.addSeparator();
        AMenuItem itemProperties = new AMenuItem("Properties");
        itemProperties.addActionListener((ActionEvent e) -> {
            //graph.showNodeProporties(scene.selection.getSingle());
        });
        popupGroup.add(itemProperties);
        
        //Leaf Group Popup Menu
        popupLeaf = new APopupMenu();
        popupLeaf.add(new AMenuItem("Copy"));
        popupLeaf.add(new AMenuItem("Paste"));
        popupLeaf.addSeparator();
        AMenuItem itemRename2 = new AMenuItem("Rename");
        itemRename2.addActionListener((ActionEvent e) -> {
            SceneGraphNode node = scene.selection.getSingle();
            if (node != null) {
                startEditingAtPath(JTreeDragAndDrop.getPath(node.getPath()[node.getPath().length - 1]));
            }
        });
        popupLeaf.add(itemRename2);
        popupLeaf.add(new AMenuItem("Duplicate"));
        AMenuItem itemDelete2 = new AMenuItem("Delete");
        itemDelete2.addActionListener((ActionEvent e) -> {
            scene.graph.removeNode(scene.selection.getSingle());
            scene.selection.clear();
        });
        popupLeaf.add(itemDelete2);
        popupLeaf.addSeparator();
        AMenuItem itemProperties2 = new AMenuItem("Properties");
        itemProperties2.addActionListener((ActionEvent e) -> {
            //graph.showNodeProporties(scene.selection.getSingle());
        });
        popupLeaf.add(itemProperties2);
    }
}
