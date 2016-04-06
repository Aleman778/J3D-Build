package j3dbuild.editor.scene;

import com.sun.j3d.utils.geometry.Primitive;
import static j3dbuild.editor.scene.graph.SceneGraph.setCapabilities;
import j3dbuild.editor.scene.graph.SceneGraphNode;
import j3dbuild.editor.ui.SceneGraphUI;
import j3dbuild.project.items.Scene;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.media.j3d.Appearance;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.ColoringAttributes;
import javax.media.j3d.Geometry;
import javax.media.j3d.LineAttributes;
import javax.media.j3d.Node;
import javax.media.j3d.PolygonAttributes;
import javax.media.j3d.RenderingAttributes;
import javax.media.j3d.Shape3D;
import javax.media.j3d.TransformGroup;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

public class SceneSelection {
    
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
    private final BranchGroup outlineNodes;
    private final Scene scene;
    
    public SceneSelection(Scene scene) {
        this.selectedNodes = new ArrayList<>();
        this.outlineNodes = new BranchGroup();
        this.scene = scene;
        setCapabilities(outlineNodes);
    }
    
    public void set(Object object) {
        SceneGraphNode node = scene.graph.findObject(object);
        set(node);
    }
    
    public void set(SceneGraphNode node) {
        if (node != null) {
            try {
                selectedNodes.clear();
                selectedNodes.add(node);
                TreeNode[] nodes = node.getPath();
                TreePath path = new TreePath(nodes);
                scene.graph.getUI().getSelectionModel().setSelectionPath(path);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            selectedNodes.clear();
        }
        update(selectedNodes);
    }
    
    public void setAll(Object[] object) {
        SceneGraphNode[] nodes = new SceneGraphNode[object.length];
        for (int i = 0; i < object.length; i++) {
            nodes[i] = scene.graph.findObject(object);
        }
        setAll(nodes);
    }
    
    public void setAll(SceneGraphNode[] nodes) {
        clear();
        for (SceneGraphNode node: nodes) {
            add(node);
        }
    }
    
    public void add(Object object) {
        SceneGraphNode node = scene.graph.findObject(object);
        add(node);
    }
    
    public void add(SceneGraphNode node) {
        if (node != null) {
            try {
                selectedNodes.add(node);
                TreeNode[] nodes = node.getPath();
                TreePath path = new TreePath(nodes);
                scene.graph.getUI().getSelectionModel().addSelectionPath(path);
            } catch (Exception e) {
                
            }
        }
        update(selectedNodes);
    }
    
    public void remove(Object object) {
        SceneGraphNode node = scene.graph.findObject(object);
        remove(node);
    }
    
    public void remove(SceneGraphNode node) {
        if (node != null) {
            try {
                selectedNodes.remove(node);
                TreeNode[] nodes = node.getPath();
                TreePath path = new TreePath(nodes);
                scene.graph.getUI().getSelectionModel().removeSelectionPath(path);
            } catch (Exception e) {
                
            }
        }
        update(selectedNodes);
    }
    
    public boolean isSelected(Object object) {
        SceneGraphNode node = scene.graph.findObject(object);
        return isSelected(node);
    }
    
    public boolean isSelected(SceneGraphNode node) {
        if (node != null) {
            TreeNode[] nodes = node.getPath();
            TreePath path = new TreePath(nodes);
            return scene.graph.getUI().getSelectionModel().isPathSelected(path);
        }
        return false;
    }
    
    public boolean isEmpty() {
        return selectedNodes.isEmpty();
    }
    
    public boolean isSingle() {
        return selectedNodes.size() == 1;
    }
    
    public void clear() {
        selectedNodes.clear();
        //Editor.showProperties("No Properties", Editor.PROPERTIES_NONE);
        scene.graph.getUI().getSelectionModel().clearSelection();
        update(selectedNodes);
    }
    
    public SceneGraphNode getSingle() {
        return isSingle() ? selectedNodes.get(0) : null;
    }
    
    public Collection<SceneGraphNode> getAll() {
        return selectedNodes;
    }
    
    public void update(Collection<SceneGraphNode> nodes) {
        SceneGraphNode single = getSingle();
        
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
        TransformGroup trGroup = new TransformGroup(scene.graph.getTransformFrom(node));
        setCapabilities(group);
        group.addChild(trGroup);
        trGroup.addChild(shape);
        outlineNodes.addChild(group);
    }
    
    private Shape3D createOutlineShape(Geometry geometry) {
        Shape3D shape = new Shape3D(geometry, appearance);
        return shape;
    }
}
