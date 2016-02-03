package build.editor.scene.graph;

import javax.swing.tree.DefaultTreeModel;

public class SceneGraph extends DefaultTreeModel {

    public SceneGraph(SceneGraphNode treeNode, boolean asksAllowsChildren) {
        super(treeNode, asksAllowsChildren);
    }
}
