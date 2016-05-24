package j3dbuild.editor.resource;

import j3dbuild.utils.ThemeUtils;
import java.awt.Component;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

public class ResourceGraphRenderer extends DefaultTreeCellRenderer {

    private static final ImageIcon ICON_CLOSED = new ImageIcon(ResourceGraphRenderer.class.getResource("/gui/icons/iconFolderClosed.png"));
    private static final ImageIcon ICON_OPEN   = new ImageIcon(ResourceGraphRenderer.class.getResource("/gui/icons/iconFolderOpen.png"));
    
    public ResourceGraphRenderer() {
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
        
        if (node.isLeaf()) {
            setIcon(ICON_CLOSED);
        }
        
        return this;
    }
}
