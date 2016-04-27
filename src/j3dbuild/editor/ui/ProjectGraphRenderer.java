package j3dbuild.editor.ui;

import j3dbuild.editor.manager.ThemeManager;
import j3dbuild.project.Project;
import java.awt.Component;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

public class ProjectGraphRenderer extends DefaultTreeCellRenderer {

    private static final ImageIcon ICON_CLOSED = new ImageIcon("res/gui/icons/iconFolderClosed.png");
    private static final ImageIcon ICON_OPEN = new ImageIcon("res/gui/icons/iconFolderOpen.png");
    private static final ImageIcon ICON_FILE = new ImageIcon("res/gui/icons/iconFile.png");
    private static final ImageIcon ICON_J3D = new ImageIcon("res/gui/icons/iconJ3D.png");
    
    public ProjectGraphRenderer() {
        setClosedIcon(ICON_CLOSED);
        setOpenIcon(ICON_OPEN);
    }

    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selection, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        super.getTreeCellRendererComponent(tree, value, selection, expanded, leaf, row, false);
        
        if (!selected) {
            setOpaque(true);
        } else {
            setOpaque(false);
        }
        setBorder(BorderFactory.createEmptyBorder());
        setBackground(ThemeManager.COLOR_PANEL);
        setForeground(ThemeManager.COLOR_FOREGROUND);
        
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
        
        if (node.getUserObject() instanceof Project) {
            setIcon(ICON_J3D);
        } else if (node.isLeaf()) {
            setIcon(ICON_FILE);
        }
        
        return this;
    }
}
