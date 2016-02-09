package build.editor.scene.graph;

import javax.media.j3d.Leaf;
import javax.media.j3d.SceneGraphObject;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;

public class SceneGraphNode extends DefaultMutableTreeNode {
    
    private final Object object;
    private String name;
    
    public SceneGraphNode(String name, Object object) {
        this.object = object;
        this.name = name;
        super.setUserObject(object);
    }
    
    public SceneGraphNode(SceneGraphObject object) {
        this.object = object;
        this.name = object.getName();
        if (name == null) {
            name = "Object";
        }
        super.setUserObject(object);
    }
    
    public SceneGraphNode(String name) {
        this.object = null;
        this.name = name;
        super.setUserObject(name);
    }

    @Deprecated
    @Override
    public void add(MutableTreeNode sceneGraphNode) {
    }
    
    public SceneGraphObject getSceneGraphObject() {
        if (object instanceof SceneGraphObject) {
            return (SceneGraphObject) object;
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
