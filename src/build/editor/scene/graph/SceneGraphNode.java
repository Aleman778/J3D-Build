package build.editor.scene.graph;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;

public class SceneGraphNode extends DefaultMutableTreeNode {
    
    public SceneGraphNode() {
        this("");
    }
    
    public SceneGraphNode(String title) {
        setUserObject(title);
    }

    @Override
    public void add(MutableTreeNode mtn) {
        super.add((SceneGraphNode) mtn);
    }
}
