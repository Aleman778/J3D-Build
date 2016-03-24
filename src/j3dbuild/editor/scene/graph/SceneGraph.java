package j3dbuild.editor.scene.graph;

import j3dbuild.editor.Editor;
import j3dbuild.editor.properties.PropertyType;
import j3dbuild.editor.scene.SceneView;
import j3dbuild.editor.ui.JTreeSceneGraph;
import j3dbuild.editor.ui.TransformUtility;
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

    private static final Appearance appearance;
    
    static {
        appearance = new Appearance();
        PolygonAttributes polyattri = new PolygonAttributes();
        polyattri.setPolygonMode(PolygonAttributes.POLYGON_LINE);
        
        RenderingAttributes renderattri = new RenderingAttributes();
        
        LineAttributes lineattri = new LineAttributes();
        lineattri.setLineAntialiasingEnable(false);
        lineattri.setLineWidth(2);
        
        appearance.setRenderingAttributes(renderattri);
        appearance.setPolygonAttributes(polyattri);
        appearance.setLineAttributes(lineattri);
        appearance.setColoringAttributes(new ColoringAttributes(1.0f, 1.0f, 1.0f, ColoringAttributes.FASTEST));
    }
    
    private final List<SceneGraphNode> selectedNodes;
    private final List<BranchGroup> graphs;
    private final Locale locale;
    private final BranchGroup outlineNodes;
    public final TransformUtility transformUtility;
    
    public SceneGraph(SceneGraphNode treeNode, boolean asksAllowsChildren, Locale locale, SceneView view) {
        super(treeNode, asksAllowsChildren);
        this.selectedNodes = new ArrayList<>();
        this.outlineNodes = new BranchGroup();
        this.transformUtility = new TransformUtility(view);
        this.graphs = new ArrayList<>();
        this.locale = locale;
        setCapabilities(outlineNodes);
        BranchGroup utilitygroup = new BranchGroup();
        setCapabilities(utilitygroup);
        setCapabilities(transformUtility);
        utilitygroup.addChild(transformUtility);
        this.locale.addBranchGraph(outlineNodes);
        this.locale.addBranchGraph(utilitygroup);
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
        updateSelection(selectedNodes);
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
        updateSelection(selectedNodes);
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
        updateSelection(selectedNodes);
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
        //Editor.showProperties("No Properties", Editor.PROPERTIES_NONE);
        JTreeSceneGraph.instance.getSelectionModel().clearSelection();
        updateSelection(selectedNodes);
    }
    
    public SceneGraphNode getSingleSelectedNode() {
        return isSelectionSingle() ? selectedNodes.get(0) : null;
    }
    
    public Collection<SceneGraphNode> getSelectedNodes() {
        return selectedNodes;
    }
    
    public void updateSelection(Collection<SceneGraphNode> nodes) {
        SceneGraphNode single = getSingleSelectedNode();
        if (single != null) {
            showNodeProporties(single);
        }
        
        outlineNodes.removeAllChildren();
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
        
        Shape3D shape = new Shape3D(geometry, appearance);
        return shape;
    }
    
    public void showNodeProporties(SceneGraphNode node) {
//        Collection<PropertyType> properties = Editor.PROPERTIES_NONE;
//        String name = "Proporties";
//        if (node != null) {
//            properties = node.getProperties();
//            name = node.getName();
//        }
//        Editor.showProperties(name, properties);
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
                addChild(node.getObject(), node.getGizmo(), parent.getObject());
            }
        }
    }
    
    public void insertNodeInto(SceneGraphNode node, SceneGraphNode parent) {
        if (node != null && parent != null) {
            insertNodeInto(node, parent, 0);
        }
    }
    
    public void modifyNodeParent(SceneGraphNode node, SceneGraphNode newparent) {
        if (node != null && newparent != null) {
            hideAllBranchGraphs(locale, graphs);
            SceneGraphNode parent = (SceneGraphNode) node.getParent();
            removeNode(node);
            insertNodeInto(newparent, parent);
            insertNodeInto(node, newparent);
            showAllBranchGraphs(locale, graphs);
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
    
    private void addChild(Object node, Node gizmo, Object parent) {
        try {
            if (parent instanceof Group) {
                if (node instanceof Node) {
                    hideAllBranchGraphs(locale, graphs);
                    
                    ((Group) parent).addChild((Node) node);
                    if (gizmo != null) {
                        ((Group) parent).addChild(gizmo);
                    }
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
        } catch (Exception ex) {
            
        }
        
        for (BranchGroup graph: graphs) {
            if (graph == node) {
                graphs.remove(graph);
                locale.removeBranchGraph(graph);
                break;
            }
        }
    }
    
    public void showAllBranchGraphs(Locale locale, Collection<BranchGroup> graphs) {
        for (BranchGroup graph: graphs) {
            try {
                locale.addBranchGraph(graph);
            } catch (Exception e) {
                
            }
        }
    } 
    
    public void hideAllBranchGraphs(Locale locale, Collection<BranchGroup> graphs) {
        for (BranchGroup graph: graphs) {
            try {
                locale.removeBranchGraph(graph);
            } catch (Exception e) {
                
            }
        }
    }

    public Locale getLocale() {
        return locale;
    }

    public List<BranchGroup> getBranchGraphs() {
        return graphs;
    }
    
    public static void setCapabilities(BranchGroup group) {
        group.setCapability(BranchGroup.ALLOW_DETACH);
        group.setCapability(BranchGroup.ALLOW_CHILDREN_WRITE);
        group.setCapability(BranchGroup.ALLOW_CHILDREN_READ);
        group.setCapability(BranchGroup.ALLOW_CHILDREN_EXTEND);
        group.setCapability(BranchGroup.ALLOW_BOUNDS_READ);
        group.setCapability(BranchGroup.ALLOW_BOUNDS_WRITE);
    }
    
    public static void setCapabilities(TransformGroup transform) {
        transform.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        transform.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
        transform.setCapability(BranchGroup.ALLOW_CHILDREN_WRITE);
        transform.setCapability(BranchGroup.ALLOW_CHILDREN_READ);
        transform.setCapability(BranchGroup.ALLOW_CHILDREN_EXTEND);
    }
    
    public static void setCapabilities(Appearance apperance) {
        apperance.setCapability(Appearance.ALLOW_COLORING_ATTRIBUTES_READ);
        apperance.setCapability(Appearance.ALLOW_COLORING_ATTRIBUTES_WRITE);
        apperance.setCapability(Appearance.ALLOW_LINE_ATTRIBUTES_READ);apperance.setCapability(Appearance.ALLOW_LINE_ATTRIBUTES_WRITE);
        apperance.setCapability(Appearance.ALLOW_MATERIAL_READ);
        apperance.setCapability(Appearance.ALLOW_MATERIAL_WRITE);
        apperance.setCapability(Appearance.ALLOW_POINT_ATTRIBUTES_READ);
        apperance.setCapability(Appearance.ALLOW_POINT_ATTRIBUTES_WRITE);
        apperance.setCapability(Appearance.ALLOW_POLYGON_ATTRIBUTES_READ);
        apperance.setCapability(Appearance.ALLOW_POLYGON_ATTRIBUTES_WRITE);
        apperance.setCapability(Appearance.ALLOW_RENDERING_ATTRIBUTES_READ);
        apperance.setCapability(Appearance.ALLOW_RENDERING_ATTRIBUTES_WRITE);
        apperance.setCapability(Appearance.ALLOW_TEXGEN_READ);
        apperance.setCapability(Appearance.ALLOW_TEXGEN_WRITE); 
        apperance.setCapability(Appearance.ALLOW_TEXTURE_ATTRIBUTES_READ);
        apperance.setCapability(Appearance.ALLOW_TEXTURE_ATTRIBUTES_WRITE);
        apperance.setCapability(Appearance.ALLOW_TEXTURE_READ);
        apperance.setCapability(Appearance.ALLOW_TEXTURE_WRITE);
        apperance.setCapability(Appearance.ALLOW_TEXTURE_UNIT_STATE_READ);
        apperance.setCapability(Appearance.ALLOW_TEXTURE_UNIT_STATE_WRITE);
        apperance.setCapability(Appearance.ALLOW_TEXGEN_READ);
        apperance.setCapability(Appearance.ALLOW_TRANSPARENCY_ATTRIBUTES_READ);
        apperance.setCapability(Appearance.ALLOW_TRANSPARENCY_ATTRIBUTES_WRITE);
    }
    
    public static void setCapabilities(Shape3D shape) {
        shape.setCapability(Shape3D.ALLOW_APPEARANCE_OVERRIDE_READ);
        shape.setCapability(Shape3D.ALLOW_APPEARANCE_OVERRIDE_WRITE);
        shape.setCapability(Shape3D.ALLOW_APPEARANCE_READ);
        shape.setCapability(Shape3D.ALLOW_APPEARANCE_WRITE);
        shape.setCapability(Shape3D.ALLOW_AUTO_COMPUTE_BOUNDS_READ);
        shape.setCapability(Shape3D.ALLOW_AUTO_COMPUTE_BOUNDS_WRITE);
        shape.setCapability(Shape3D.ALLOW_BOUNDS_READ);
        shape.setCapability(Shape3D.ALLOW_BOUNDS_WRITE);
        shape.setCapability(Shape3D.ALLOW_COLLIDABLE_READ);
        shape.setCapability(Shape3D.ALLOW_COLLIDABLE_WRITE);
        shape.setCapability(Shape3D.ALLOW_COLLISION_BOUNDS_READ);
        shape.setCapability(Shape3D.ALLOW_COLLISION_BOUNDS_WRITE);
        shape.setCapability(Shape3D.ALLOW_GEOMETRY_READ);
        shape.setCapability(Shape3D.ALLOW_GEOMETRY_WRITE);
        shape.setCapability(Shape3D.ALLOW_LOCALE_READ);
        shape.setCapability(Shape3D.ALLOW_LOCAL_TO_VWORLD_READ);
        shape.setCapability(Shape3D.ALLOW_PARENT_READ);
        shape.setCapability(Shape3D.ALLOW_PICKABLE_READ);
        shape.setCapability(Shape3D.ALLOW_PICKABLE_WRITE);
    }
    
    public static void setCapabilities(Primitive primitive) {
        int index = 0;
        Shape3D shape;
        while ((shape = primitive.getShape(index)) != null) {
            setCapabilities(shape);
            index++;
        }
    }
}
