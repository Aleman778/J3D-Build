package j3dbuild.editor.ui;

import j3dbuild.utils.ThemeUtils;
import java.util.ArrayList;
import javax.swing.JTree;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

public class ATree extends JTree {

    public ATree() {
        getSelectionModel().setSelectionMode(
                TreeSelectionModel.CONTIGUOUS_TREE_SELECTION);
        setBackground(ThemeUtils.COLOR_PANEL);
        setForeground(ThemeUtils.COLOR_FOREGROUND);
    }
    
    public static TreePath getPath(TreeNode node) {
        ArrayList<TreeNode> nodes = new ArrayList<>();
        if (node != null) {
            do {
                nodes.add(node);
                node = node.getParent();
            } while (node != null);
        }
        System.out.println(new TreePath(nodes.toArray()).getLastPathComponent());
        if (nodes.isEmpty()) {
            return null;
        } else {
            Object[] nodearr = nodes.toArray();
            
            //Reverse array
            for (int i = 0; i < nodearr.length / 2; i++) {
                Object temp = nodearr[i];
                nodearr[i] = nodearr[nodearr.length - 1 - i];
                nodearr[nodearr.length - 1 - i] = temp;
            }
            
            return new TreePath(nodearr);
        }
    }
}
