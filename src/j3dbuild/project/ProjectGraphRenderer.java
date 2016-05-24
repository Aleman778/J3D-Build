package j3dbuild.project;

import j3dbuild.utils.ThemeUtils;
import java.awt.Component;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

public class ProjectGraphRenderer extends DefaultTreeCellRenderer {

    private static final ImageIcon ICON_CLOSED = new ImageIcon(ProjectGraphRenderer.class.getResource("/gui/icons/iconFolderClosed.png"));
    private static final ImageIcon ICON_OPEN   = new ImageIcon(ProjectGraphRenderer.class.getResource("/gui/icons/iconFolderOpen.png"));
    private static final ImageIcon ICON_FILE   = new ImageIcon(ProjectGraphRenderer.class.getResource("/gui/icons/iconFile.png"));
    private static final ImageIcon ICON_J3D    = new ImageIcon(ProjectGraphRenderer.class.getResource("/gui/icons/iconJ3D.png"));
    
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
        setBackground(ThemeUtils.COLOR_PANEL);
        setForeground(ThemeUtils.COLOR_FOREGROUND);
        
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
        boolean directory = false;
        if (node instanceof Item) {
            directory = ((Item) node).getFile().isDirectory();
        }
        
        
        if (node instanceof Project) {
            setIcon(ICON_J3D);
        } else if (directory && node.isLeaf()) {
            setIcon(ICON_CLOSED);
        } else if (node.isLeaf()) {
            setIcon(ICON_FILE);
        }
        
        return this;
    }
}
