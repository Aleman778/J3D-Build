package j3dbuild.editor.scene.graph;

import j3dbuild.editor.SceneEditor;
import j3dbuild.editor.properties.PropertyType;
import j3dbuild.editor.properties.StringProperty;
import j3dbuild.editor.properties.TransformProperty;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javafx.beans.value.ObservableValue;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Leaf;
import javax.media.j3d.Node;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;

public final class SceneGraphNode extends DefaultMutableTreeNode {
    
    private BranchGroup group;
    private final Object object;
    private final SceneEditor scene;
    private final List<PropertyType> properties;
    private String name;
    
    public SceneGraphNode(SceneEditor scene, String name, Object object) {
        this.properties = new ArrayList<>();
        this.object = object;
        this.scene = scene;
        this.group = null;
        this.name = name;
        super.setUserObject(object);
        
        StringProperty nameproperty = new StringProperty("Name", name);
        nameproperty.addChangeListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            setName(newValue);
        });
        properties.add(nameproperty);
    }
    
    public SceneGraphNode(SceneEditor scene, Node object) {
        this.properties = new ArrayList<>();
        this.object = object;
        this.scene = scene;
        if (!(object instanceof BranchGroup)) {
            this.group = new BranchGroup();
            this.group.addChild(object);
            SceneGraph.setCapabilities(group);
        }
        this.name = object.getName();
        super.setUserObject(object);
        StringProperty nameproperty = new StringProperty("Name", name);
        nameproperty.addChangeListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            setName(newValue);
        });
        properties.add(nameproperty);
        
//        TransformProperty transform = new TransformProperty();
//        transform.addChangeListener((ObservableValue<? extends Transform3D> observable, Transform3D oldValue, Transform3D newValue) -> {
//            Node node = getJ3DNode();
//            System.out.println("Transform");
//            if (node instanceof TransformGroup) {
//                ((TransformGroup) node).setTransform(newValue);
//                scene.selection.update(scene.selection.getAll());
//            }
//        });
//        properties.add(transform);
    }
    
    public SceneGraphNode(SceneEditor scene, String name) {
        this.properties = new ArrayList<>();
        this.object = null;
        this.group = null;
        this.scene = scene;
        this.name = name;
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
    
    public Node getGroup() {
        if (group != null) {
            return group;
        } else {
            return getJ3DNode();
        }
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
        
        scene.graph.nodeChanged(this);
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
