package build.editor.scene.graph;

import build.editor.properties.*;
import build.editor.scene.Gizmo;
import build.editor.ui.JTreeSceneGraph;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javafx.beans.value.ObservableValue;
import javax.media.j3d.Leaf;
import javax.media.j3d.Node;
import javax.media.j3d.DirectionalLight;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;

public final class SceneGraphNode extends DefaultMutableTreeNode {
    
    private final Object object;
    private final Node gizmo;
    private final List<PropertyType> properties;
    private String name;
    
    public SceneGraphNode(String name, Object object) {
        this.properties = new ArrayList<>();
        this.object = object;
        this.name = name;
        this.gizmo = null;
        super.setUserObject(object);
        
        StringProperty nameproperty = new StringProperty("Name", name);
        nameproperty.addChangeListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            setName(newValue);
        });
        properties.add(nameproperty);
    }
    
    public SceneGraphNode(Node object) {
        this.properties = new ArrayList<>();
        this.object = object;
        this.name = object.getName();
        if (object instanceof DirectionalLight) {
            this.gizmo = new Gizmo();
        } else {
            this.gizmo = null;
        }
        
        if (name == null) {
            name = "Object";
        }
        super.setUserObject(object);
        
        StringProperty nameproperty = new StringProperty("Name", name);
        nameproperty.addChangeListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            System.out.println(newValue);
            setName(newValue);
        });
        properties.add(nameproperty);
        
        TransformProperty transform = new TransformProperty();
        transform.addChangeListener((ObservableValue<? extends Transform3D> observable, Transform3D oldValue, Transform3D newValue) -> {
            Node node = getJ3DNode();
            Node parent1 = node.getParent();
            if (parent1 instanceof TransformGroup) {
                SceneGraph graph = JTreeSceneGraph.instance.getSceneGraph();
                graph.hideAllBranchGraphs(graph.getLocale(), graph.getBranchGraphs());
                ((TransformGroup) parent1).setTransform(newValue);
                graph.showAllBranchGraphs(graph.getLocale(), graph.getBranchGraphs());
                graph.updateSelection(graph.getSelectedNodes());
            }
        });
        
        properties.add(transform);
    }
    
    public SceneGraphNode(String name) {
        this.properties = new ArrayList<>();
        this.object = null;
        this.name = name;
        this.gizmo = null;
        super.setUserObject(name);
        
        StringProperty nameproperty = new StringProperty("Name", name);
        nameproperty.addChangeListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            setName(newValue);
        });
        properties.add(nameproperty);
    }

    @Deprecated
    @Override
    public void add(MutableTreeNode sceneGraphNode) {
    }
    
    public Node getJ3DNode() {
        if (object instanceof Node) {
            return (Node) object;
        }
        return null;
    }

    public Node getGizmo() {
        return gizmo;
    }
    
    public SceneGraphNode findObject(Object object) {
        if (this.object == object) {
            return this;
        }
        
        for (int i = 0; i < getChildCount(); i++) {
            SceneGraphNode node = ((SceneGraphNode) getChildAt(i)).findObject(object);
            if (node != null) {
                return node;
            }
        }
        
        return null;
    }
    
    public Object getObject() {
        return object;
    }

    public boolean hasObject() {
        return object != null;
    }

    @Override
    public void setUserObject(Object object) {
        setName(object.toString());
    }

    public void setName(String name) {
        this.name = name;
        Node node = getJ3DNode();
        if (node != null) {
            node.setName(name);
        }
        
        JTreeSceneGraph.instance.getSceneGraph().nodeChanged(this);
        JTreeSceneGraph.instance.revalidate();
        JTreeSceneGraph.instance.repaint();
    }
    
    public String getName() {
        return name;
    }

    public Collection<PropertyType> getProperties() {
        return properties;
    }
    
    public boolean isLeafObject() {
        if (object != null) {
            if (object instanceof Leaf) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public boolean isLeaf() {
        boolean leaf = super.isLeaf();
        if (object != null && !leaf) {
            if (object instanceof Leaf) {
                leaf = true;
            }
        }
        
        return leaf;
    }

    @Override
    public String toString() {
        return name;
    }
}
