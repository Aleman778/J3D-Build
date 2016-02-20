package build.editor.ui.acomponents;

import build.editor.manager.ThemeManager;
import javax.swing.JTree;
import javax.swing.tree.TreeSelectionModel;

public class ATree extends JTree {

    public ATree() {
        getSelectionModel().setSelectionMode(
                TreeSelectionModel.CONTIGUOUS_TREE_SELECTION);
        setBackground(ThemeManager.COLOR_PANEL);
        setForeground(ThemeManager.COLOR_FOREGROUND);
    }
    
}
