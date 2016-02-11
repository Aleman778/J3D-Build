package build.editor.scene.graph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Group;
import javax.media.j3d.Locale;
import javax.media.j3d.Node;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;

public class SceneGraph extends DefaultTreeModel {

    private final List<BranchGroup> graphs;
    private final Locale locale;
    
    public SceneGraph(SceneGraphNode treeNode, boolean asksAllowsChildren, Locale locale) {
        super(treeNode, asksAllowsChildren);
        this.graphs = new ArrayList<>();
        this.locale = locale;
    }

    @Deprecated
    @Override
    public void insertNodeInto(MutableTreeNode node, MutableTreeNode parent, int index) {
    }

    @Deprecated
    @Override
    public void removeNodeFromParent(MutableTreeNode node) {
    }
    
    public SceneGraphNode getRootNode() {
        return (SceneGraphNode) getRoot();
    }
    
    public SceneGraphNode findObject(Object node) {
        return getRootNode().findObject(node);
    }
    
    public void insertNodeInto(SceneGraphNode node, SceneGraphNode parent, int index) {
        if (!parent.isLeafObject()) {
            super.insertNodeInto(node, parent, index);
            addChild(node.getObject(), parent.getObject());
        }
    }
    
    public void insertNodeInto(SceneGraphNode node, SceneGraphNode parent) {
        insertNodeInto(node, parent, 0);
    }
    
    public void removeNode(SceneGraphNode node) {
        super.removeNodeFromParent(node);
    }
    
    private void addChild(Object node, Object parent) {
        try {
            if (parent instanceof Group) {
                if (node instanceof Node) {
                    for (BranchGroup graph: graphs) {
                        removeAllBranchGraphs(locale, graphs);
                    }
                    
                    ((Group) parent).addChild((Node) node);
                    addAllBranchGraphs(locale, graphs);
                }
            } else if (parent instanceof Locale) {
                if (node instanceof BranchGroup) {
                    graphs.add((BranchGroup) node);
                    ((Locale) parent).addBranchGraph((BranchGroup) node);
                }
            }
        } catch (ClassCastException ex) {
            ex.printStackTrace();
        }
    }
    
    private void addAllBranchGraphs(Locale locale, Collection<BranchGroup> graphs) {
        for (BranchGroup graph: graphs) {
            locale.addBranchGraph(graph);
        }
    } 
    
    private void removeAllBranchGraphs(Locale locale, Collection<BranchGroup> graphs) {
        for (BranchGroup graph: graphs) {
            locale.removeBranchGraph(graph);
        }
    }
    
    public static void setCapabilities(BranchGroup node) {
        node.setCapability(BranchGroup.ALLOW_DETACH);
        node.setCapability(BranchGroup.ALLOW_CHILDREN_WRITE);
        node.setCapability(BranchGroup.ALLOW_CHILDREN_READ);
        node.setCapability(BranchGroup.ALLOW_CHILDREN_EXTEND);
        node.setCapability(BranchGroup.ALLOW_BOUNDS_READ);
        node.setCapability(BranchGroup.ALLOW_BOUNDS_WRITE);
    }
}
