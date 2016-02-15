package build.editor.scene.graph;

import build.editor.ui.JTreeSceneGraph;
import com.sun.j3d.utils.geometry.Primitive;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.media.j3d.Appearance;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.ColoringAttributes;
import javax.media.j3d.Geometry;
import javax.media.j3d.Group;
import javax.media.j3d.LineAttributes;
import javax.media.j3d.Locale;
import javax.media.j3d.Material;
import javax.media.j3d.Node;
import javax.media.j3d.PolygonAttributes;
import javax.media.j3d.RenderingAttributes;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.TransparencyAttributes;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import javax.vecmath.Color3f;

public class SceneGraph extends DefaultTreeModel {

    private final List<SceneGraphNode> selectedNodes;
    private final List<BranchGroup> graphs;
    private final Locale locale;
    private final BranchGroup outlineNodes;
    
    public SceneGraph(SceneGraphNode treeNode, boolean asksAllowsChildren, Locale locale) {
        super(treeNode, asksAllowsChildren);
        this.selectedNodes = new ArrayList<>();
        this.outlineNodes = new BranchGroup();
        this.graphs = new ArrayList<>();
        this.locale = locale;
        setCapabilities(outlineNodes);
        locale.addBranchGraph(outlineNodes);
    }
    
    public SceneGraphNode getRootNode() {
        return (SceneGraphNode) getRoot();
    }
    
    public SceneGraphNode findObject(Object node) {
        return getRootNode().findObject(node);
    }
    
    public void setSelectionObject(Object object) {
        SceneGraphNode node = findObject(object);
        setSelectionNode(node);
    }
    
    public void setSelectionNode(SceneGraphNode node) {
        if (node != null) {
            try {
                selectedNodes.clear();
                selectedNodes.add(node);
                TreeNode[] nodes = node.getPath();
                TreePath path = new TreePath(nodes);
                JTreeSceneGraph.instance.getSelectionModel().setSelectionPath(path);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            selectedNodes.clear();
        }
        setOutlineNodes(selectedNodes);
    }
    
    public void setSelectionObjects(Object[] object) {
        SceneGraphNode[] nodes = new SceneGraphNode[object.length];
        for (int i = 0; i < object.length; i++) {
            nodes[i] = findObject(object);
        }
        setSelectionNodes(nodes);
    }
    
    public void setSelectionNodes(SceneGraphNode[] nodes) {
        clearSelectedNodes();
        for (SceneGraphNode node: nodes) {
            addSelectionNode(node);
        }
    }
    
    public void addSelectionObject(Object object) {
        SceneGraphNode node = findObject(object);
        addSelectionNode(node);
    }
    
    public void addSelectionNode(SceneGraphNode node) {
        if (node != null) {
            try {
                selectedNodes.add(node);
                TreeNode[] nodes = node.getPath();
                TreePath path = new TreePath(nodes);
                JTreeSceneGraph.instance.getSelectionModel().addSelectionPath(path);
            } catch (Exception e) {
                
            }
        }
        setOutlineNodes(selectedNodes);
    }
    
    public void removeSelectionObject(Object object) {
        SceneGraphNode node = findObject(object);
        removeSelectionNode(node);
    }
    
    public void removeSelectionNode(SceneGraphNode node) {
        if (node != null) {
            try {
                selectedNodes.remove(node);
                TreeNode[] nodes = node.getPath();
                TreePath path = new TreePath(nodes);
                JTreeSceneGraph.instance.getSelectionModel().removeSelectionPath(path);
            } catch (Exception e) {
                
            }
        }
        setOutlineNodes(selectedNodes);
    }
    
    public boolean isObjectSelected(Object object) {
        SceneGraphNode node = findObject(object);
        return isNodeSelected(node);
    }
    
    public boolean isNodeSelected(SceneGraphNode node) {
        if (node != null) {
            TreeNode[] nodes = node.getPath();
            TreePath path = new TreePath(nodes);
            return JTreeSceneGraph.instance.getSelectionModel().isPathSelected(path);
        }
        return false;
    }
    
    public void clearSelectedNodes() {
        selectedNodes.clear();
        JTreeSceneGraph.instance.getSelectionModel().clearSelection();
        setOutlineNodes(selectedNodes);
    }
    
    public void setOutlineNodes(Collection<SceneGraphNode> nodes) {
        outlineNodes.removeAllChildren();
        System.out.println(nodes);
        for (SceneGraphNode node: nodes) {
            if (node.getObject() instanceof Shape3D) {
                Geometry geom = ((Shape3D) node.getObject()).getGeometry();
                Shape3D shape = createOutlineShape(geom);
                addOutlineShape(shape);
            } else if (node.getObject() instanceof Primitive) {
                int index = 0;
                Shape3D shape;
                while ((shape = ((Primitive) node.getObject()).getShape(index)) != null) {
                    Geometry geom = shape.getGeometry();
                    Shape3D newShape = createOutlineShape(geom);
                    addOutlineShape(newShape);
                    index++;
                }
            }
        }
    }
    
    private void addOutlineShape(Shape3D shape) {
        BranchGroup group = new BranchGroup();
        setCapabilities(group);
        group.addChild(shape);
        outlineNodes.addChild(group);
    }
    
    private Shape3D createOutlineShape(Geometry geometry) {
        Appearance appearance = new Appearance();
        PolygonAttributes polyattri = new PolygonAttributes();
        polyattri.setPolygonMode(PolygonAttributes.POLYGON_LINE);
        
        RenderingAttributes renderattri = new RenderingAttributes();
        
        LineAttributes lineattri = new LineAttributes();
        lineattri.setLineAntialiasingEnable(true);
        lineattri.setLineWidth(0.5f);
        
        appearance.setRenderingAttributes(renderattri);
        appearance.setPolygonAttributes(polyattri);
        appearance.setLineAttributes(lineattri);
        appearance.setMaterial(new Material(new Color3f(1, 1, 1),
                new Color3f(1, 1, 1), new Color3f(1, 1, 1), new Color3f(1, 1, 1), 0));
        Shape3D shape = new Shape3D(geometry, appearance);
        return shape;
    }

    @Deprecated
    @Override
    public void insertNodeInto(MutableTreeNode node, MutableTreeNode parent, int index) {
    }

    @Deprecated
    @Override
    public void removeNodeFromParent(MutableTreeNode node) {
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
