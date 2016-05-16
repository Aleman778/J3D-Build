package j3dbuild.project;

import j3dbuild.editor.ui.AMenuItem;
import j3dbuild.editor.ui.APopupMenu;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreePath;

public class ProjectGraph extends DefaultTreeModel {
    
    private static final APopupMenu popupScene;
    private static final APopupMenu popupItem;
    private static final AMenuItem menuCut;
    private static final AMenuItem menuCopy;
    private static final AMenuItem menuPaste;
    private static final AMenuItem menuDelete;
    private static final AMenuItem menuRename;
    private static final AMenuItem menuNew;
    private static final AMenuItem menuOpen;
    
    static {
        menuCut    = new AMenuItem("Cut");
        menuCopy   = new AMenuItem("Copy");
        menuPaste  = new AMenuItem("Paste");
        menuDelete = new AMenuItem("Delete");
        menuRename = new AMenuItem("Rename");
        menuNew    = new AMenuItem("New");
        menuOpen   = new AMenuItem("Open");
        
        popupScene = new APopupMenu(menuCut);
        popupScene.add(menuOpen);
        popupScene.addSeparator();
        popupScene.add(menuCut);
        popupScene.add(menuCopy);
        popupScene.add(menuPaste);
        popupScene.addSeparator();
        popupScene.add(menuDelete);
        popupScene.add(menuRename);
        popupItem = new APopupMenu();
    }
    
    private final ArrayList<Project> projects;
    private final JTree tree;
    
    public ProjectGraph(JTree tree) {
        super(new DefaultMutableTreeNode("Projects"));
        this.tree = tree;
        projects = new ArrayList<>();
        
        tree.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent me) {
                TreePath path = tree.getClosestPathForLocation(me.getX(), me.getY());
                if (path != null) {
                    Item item = (Item) path.getLastPathComponent();
                    if (SwingUtilities.isLeftMouseButton(me) && me.getClickCount() == 2) {
                        try {
                            if (item.getFile().isFile() && item.getFile().exists()) {
                                item.edit();
                            }
                        } catch (ClassCastException ex) {
                            ex.printStackTrace();
                        }
                    } else if (SwingUtilities.isRightMouseButton(me)) {
                        if (item instanceof Scene) {
                            popupScene.showMenu(me.getXOnScreen() - tree.getX(),
                                me.getYOnScreen() - tree.getY());
                        }
                    }
                }
            }
            
        });
    }
    
    public void addProject(Project project) {
        if (!projects.contains(project)) {
            project.setProject(project);
            projects.add(project);
            refresh();
        }
    }

    public void removeProject(Project project) {
        if (projects.contains(project)) {
            projects.remove(project);
            refresh();
        }
    }
    
    @Override
    public MutableTreeNode getRoot() {
        return (MutableTreeNode) root;
    }
    
    public void refresh() {
        ((DefaultMutableTreeNode) getRoot()).removeAllChildren();
        
        for (Project project: projects) {
            getRoot().insert(project, getRoot().getChildCount());
            project.refresh();
        }
        reload();
        tree.expandRow(0);
    }
    
    public void save() {
        for (Project project: projects) {
            project.save();
        }
    }
    
    public void saveAs(File file) {
        for (Project project: projects) {
            project.saveAs(file);
        }
    }
    
    public void export(File file) {
        for (Project project: projects) {
            project.export(file);
        }        
    }
}
