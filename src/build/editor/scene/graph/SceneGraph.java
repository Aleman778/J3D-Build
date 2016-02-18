package build.editor.scene.graph;

import build.editor.J3DBuild;
import build.editor.properties.PropertyType;
import build.editor.ui.JTreeSceneGraph;
import com.sun.j3d.utils.geometry.Primitive;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;
import javax.media.j3d.Appearance;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.ColoringAttributes;
import javax.media.j3d.Geometry;
import javax.media.j3d.Group;
import javax.media.j3d.LineAttributes;
import javax.media.j3d.Locale;
import javax.media.j3d.Node;
import javax.media.j3d.PolygonAttributes;
import javax.media.j3d.RenderingAttributes;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

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
    
    public boolean isSelectionEmpty() {
        return selectedNodes.isEmpty();
    }
    
    public boolean isSelectionSingle() {
        return selectedNodes.size() == 1;
    }
    
    public void clearSelectedNodes() {
        selectedNodes.clear();
        J3DBuild.showProperties("No Properties", J3DBuild.PROPERTIES_NONE);
        JTreeSceneGraph.instance.getSelectionModel().clearSelection();
        setOutlineNodes(selectedNodes);
    }
    
    public SceneGraphNode getSingleSelectedNode() {
        return isSelectionSingle() ? selectedNodes.get(0) : null;
    }
    
    public Collection<SceneGraphNode> getSelectedNodes() {
        return selectedNodes;
    }
    
    public void setOutlineNodes(Collection<SceneGraphNode> nodes) {
        outlineNodes.removeAllChildren();
        System.out.println(nodes);
        for (SceneGraphNode node: nodes) {
            if (node.getObject() instanceof Shape3D) {
                Geometry geom = ((Shape3D) node.getObject()).getGeometry();
                Shape3D shape = createOutlineShape(geom);
                addOutlineShape(node.getJ3DNode(), shape);
            } else if (node.getObject() instanceof Primitive) {
                int index = 0;
                Shape3D shape;
                while ((shape = ((Primitive) node.getObject()).getShape(index)) != null) {
                    Geometry geom = shape.getGeometry();
                    Shape3D newShape = createOutlineShape(geom);
                    addOutlineShape(node.getJ3DNode(), newShape);
                    index++;
                }
            }
        }
    }
    
    private void addOutlineShape(Node node, Shape3D shape) {
        BranchGroup group = new BranchGroup();
        TransformGroup trGroup = new TransformGroup(getTransformFrom(node));
        setCapabilities(group);
        group.addChild(trGroup);
        trGroup.addChild(shape);
        outlineNodes.addChild(group);
    }
    
    private Shape3D createOutlineShape(Geometry geometry) {
        Appearance appearance = new Appearance();
        PolygonAttributes polyattri = new PolygonAttributes();
        polyattri.setPolygonMode(PolygonAttributes.POLYGON_LINE);
        
        RenderingAttributes renderattri = new RenderingAttributes();
        
        LineAttributes lineattri = new LineAttributes();
        lineattri.setLineAntialiasingEnable(true);
        lineattri.setLineWidth(3);
        
        appearance.setRenderingAttributes(renderattri);
        appearance.setPolygonAttributes(polyattri);
        appearance.setLineAttributes(lineattri);
        appearance.setColoringAttributes(new ColoringAttributes(1, 1, 1, ColoringAttributes.FASTEST));
        Shape3D shape = new Shape3D(geometry, appearance);
        return shape;
    }
    
    public void showNodeProporties(SceneGraphNode node) {
        Collection<PropertyType> properties = J3DBuild.PROPERTIES_NONE;
        String name = "Proporties";
        if (node != null) {
            properties = node.getProperties();
            name = node.getName();
        }
        J3DBuild.showProperties(name, properties);
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
        if (node != null && parent != null) {
            if (!parent.isLeafObject()) {
                super.insertNodeInto(node, parent, index);
                addChild(node.getObject(), parent.getObject());
            }
        }
    }
    
    public void insertNodeInto(SceneGraphNode node, SceneGraphNode parent) {
        if (node != null && parent != null) {
            insertNodeInto(node, parent, 0);
        }
    }
    
    public void removeNode(SceneGraphNode node) {
        if (node != null) {
            super.removeNodeFromParent(node);
            removeChild(node.getObject());
        }
    }
    
    public Transform3D getTransformFrom(Node node) {
        Transform3D transform = new Transform3D();
        ((Node) node).getLocalToVworld(transform);
        return transform;
    }
    
    private void addChild(Object node, Object parent) {
        try {
            if (parent instanceof Group) {
                if (node instanceof Node) {
                    hideAllBranchGraphs(locale, graphs);
                    
                    ((Group) parent).addChild((Node) node);
                    showAllBranchGraphs(locale, graphs);
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
    
    private void removeChild(Object node) {
        try {
            hideAllBranchGraphs(locale, graphs);
            Group parent = (Group) ((Node) node).getParent();
            parent.removeChild((Node) node);
            showAllBranchGraphs(locale, graphs);
        } catch (ClassCastException ex) {
            ex.printStackTrace();
        }
    }
    
    public void showAllBranchGraphs(Locale locale, Collection<BranchGroup> graphs) {
        for (BranchGroup graph: graphs) {
            locale.addBranchGraph(graph);
        }
    } 
    
    public void hideAllBranchGraphs(Locale locale, Collection<BranchGroup> graphs) {
        for (BranchGroup graph: graphs) {
            locale.removeBranchGraph(graph);
        }
    }

    public Locale getLocale() {
        return locale;
    }

    public List<BranchGroup> getBranchGraphs() {
        return graphs;
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
