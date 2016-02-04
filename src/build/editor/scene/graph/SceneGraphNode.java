package build.editor.scene.graph;

import javax.media.j3d.SceneGraphObject;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;

public class SceneGraphNode extends DefaultMutableTreeNode {
    
    private SceneGraphObject object;
    private String name;
    
    public SceneGraphNode(SceneGraphObject object) {
        this.object = object;
        this.name = object.getName();
        if (name == null) {
            name = "Object";
        }
        setUserObject(name);
    }
    
    public SceneGraphNode(String text) {
        this.object = null;
        setUserObject(text);
    }

    @Override
    public void add(MutableTreeNode mtn) {
        super.add((SceneGraphNode) mtn);
    }
    
    public SceneGraphObject getObject() {
        return object;
    }
}
