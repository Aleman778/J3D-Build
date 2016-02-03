package build.editor.ui;

import build.editor.J3DBuild;
import build.editor.scene.graph.SceneGraph;
import build.editor.scene.graph.SceneGraphNode;
import build.editor.scene.graph.SceneGraphRenderer;
import build.editor.ui.acomponents.AMenu;
import build.editor.ui.acomponents.AMenuItem;
import build.editor.ui.acomponents.APopupMenu;
import build.editor.ui.acomponents.ASeparator;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingUtilities;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

public class JTreeSceneGraph extends JTreeDragAndDrop {
    
    private static final APopupMenu POPUP_BRANCH;
    
    static {
        //Branch Group Popup Menu
        POPUP_BRANCH = new APopupMenu();
        POPUP_BRANCH.add(new AMenuItem("Paste"));
        POPUP_BRANCH.add(new AMenuItem("Rename"));
        POPUP_BRANCH.addSeparator();
        POPUP_BRANCH.add(new AMenuItem("Rename"));
        POPUP_BRANCH.add(new AMenuItem("Duplicate"));
        POPUP_BRANCH.add(new AMenuItem("Delete"));
        POPUP_BRANCH.addSeparator();
        POPUP_BRANCH.add(new AMenuItem("Transform"));
        POPUP_BRANCH.addSeparator();
        POPUP_BRANCH.add(new AMenuItem("Create Node"));
        POPUP_BRANCH.add(new AMenu("Primitive"));
        POPUP_BRANCH.add(new AMenu("Light"));
        POPUP_BRANCH.add(new AMenu("Audio"));
        POPUP_BRANCH.add(new AMenu("Behaviour"));
        POPUP_BRANCH.add(new AMenuItem("Camera"));
        POPUP_BRANCH.addSeparator();
        POPUP_BRANCH.add(new AMenuItem("Properties"));
    }
    
    public JTreeSceneGraph() {
        addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent me) {
                if (SwingUtilities.isRightMouseButton(me)) {
                    
                    
                    TreePath path = getPathForLocation(me.getX(), me.getY());
                    if (path != null) {
                        setSelectionPath(path);
                        try {
                            DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();
                            
                            //Create PopupMenu
                            POPUP_BRANCH.showMenu(me.getXOnScreen() - J3DBuild.instance.getX(),
                                me.getYOnScreen() - J3DBuild.instance.getY());
                        } catch (ClassCastException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            }
        });
        
        addTreeSelectionListener(new TreeSelectionListener() {

            @Override
            public void valueChanged(TreeSelectionEvent tse) {
                try {
                    DefaultMutableTreeNode node = (DefaultMutableTreeNode) tse.getPath().getLastPathComponent();
                    System.out.println(node);
                } catch (ClassCastException ex) {
                    System.out.println(tse.getPath().getLastPathComponent().getClass());
                    ex.printStackTrace();
                }
            }
        });
        
        setCellRenderer(new SceneGraphRenderer());
    }

    public void setTreeModel(SceneGraph sceneGraph) {
        this.treeModel = sceneGraph;
    }
}
