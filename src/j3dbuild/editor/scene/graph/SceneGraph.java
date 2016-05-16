package j3dbuild.editor.scene.graph;

import com.sun.j3d.utils.geometry.Primitive;
import j3dbuild.editor.SceneEditor;
import java.util.ArrayList;
import java.util.List;
import javax.media.j3d.Appearance;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Group;
import javax.media.j3d.Locale;
import javax.media.j3d.Node;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;

public class SceneGraph extends DefaultTreeModel {

    private SceneEditor scene;
    private SceneGraphUI ui;
    private ArrayList<BranchGroup> graphs;
    
    public SceneGraph(SceneEditor scene, SceneGraphNode root) {
        super(root, true);
        graphs = new ArrayList<>();
    }
    
    public SceneGraphNode getRootNode() {
        return (SceneGraphNode) getRoot();
    }
    
    public SceneGraphNode findObject(Object node) {
        return getRootNode().findObject(node);
    }

    public SceneGraphUI getUI() {
        return ui;
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
            hideAllBranchGraphs();
            SceneGraphNode parent = (SceneGraphNode) node.getParent();
            removeNode(node);
            insertNodeInto(newparent, parent);
            insertNodeInto(node, newparent);
            showAllBranchGraphs();
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
                    hideAllBranchGraphs();
                    
                    ((Group) parent).addChild((Node) node);
                    if (gizmo != null) {
                        ((Group) parent).addChild(gizmo);
                    }
                    showAllBranchGraphs();
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
            hideAllBranchGraphs();
            Group parent = (Group) ((Node) node).getParent();
            parent.removeChild((Node) node);
            showAllBranchGraphs();
        } catch (Exception ex) {
            
        }
        
        for (BranchGroup graph: graphs) {
            if (graph == node) {
                graphs.remove(graph);
                scene.locale.removeBranchGraph(graph);
                break;
            }
        }
    }
    
    public void showAllBranchGraphs() {
        for (BranchGroup graph: graphs) {
            try {
                scene.locale.addBranchGraph(graph);
            } catch (Exception e) {
                
            }
        }
    } 
    
    public void hideAllBranchGraphs() {
        for (BranchGroup graph: graphs) {
            try {
                scene.locale.removeBranchGraph(graph);
            } catch (Exception e) {
                
            }
        }
    }

    public Locale getLocale() {
        return scene.locale;
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
