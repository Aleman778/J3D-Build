package build.editor.scene.graph;

import build.editor.properties.PropertyType;
import build.editor.properties.TransformProperty;
import build.editor.ui.JTreeSceneGraph;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javax.media.j3d.Leaf;
import javax.media.j3d.Node;
import javax.media.j3d.SceneGraphObject;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;

public class SceneGraphNode extends DefaultMutableTreeNode {
    
    private final Object object;
    private final List<PropertyType> properties;
    private String name;
    
    public SceneGraphNode(String name, Object object) {
        this.properties = new ArrayList<>();
        this.object = object;
        this.name = name;
        super.setUserObject(object);
    }
    
    public SceneGraphNode(SceneGraphObject object) {
        this.properties = new ArrayList<>();
        this.object = object;
        this.name = object.getName();
        if (name == null) {
            name = "Object";
        }
        super.setUserObject(object);
        
        TransformProperty transform = new TransformProperty();
        transform.addChangeListener(new ChangeListener<Transform3D>() {

            @Override
            public void changed(ObservableValue<? extends Transform3D> observable, Transform3D oldValue, Transform3D newValue) {
                Node node = getJ3DNode();
                Node parent = node.getParent();
                
                if (parent instanceof TransformGroup) {
                    SceneGraph graph = JTreeSceneGraph.instance.getSceneGraph();
                    graph.hideAllBranchGraphs(graph.getLocale(), graph.getBranchGraphs());
                    ((TransformGroup) parent).setTransform(newValue);
                    graph.showAllBranchGraphs(graph.getLocale(), graph.getBranchGraphs());
                    graph.setOutlineNodes(graph.getSelectedNodes());
                }
            }
        });
        properties.add(transform);
    }
    
    public SceneGraphNode(String name) {
        this.properties = new ArrayList<>();
        this.object = null;
        this.name = name;
        super.setUserObject(name);
    }

    @Deprecated
    @Override
    public void add(MutableTreeNode sceneGraphNode) {
    }
    
    public Node getJ3DNode() {
        if (object instanceof SceneGraphObject) {
            return (Node) object;
        }
        return null;
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
        super.setUserObject(object);
        this.name = object.toString();
    }

    public void setName(String name) {
        this.name = name;
        setUserObject(name);
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
