package j3dbuild.editor.resource;

import j3dbuild.editor.manager.ThemeManager;
import j3dbuild.editor.scene.Universe;
import java.awt.Component;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Group;
import javax.media.j3d.Locale;
import javax.media.j3d.Shape3D;
import javax.media.j3d.TransformGroup;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

public class ResourceGraphRenderer extends DefaultTreeCellRenderer {

    private static final ImageIcon ICON_CLOSED = new ImageIcon("res/gui/icons/iconFolderClosed.png");
    private static final ImageIcon ICON_OPEN = new ImageIcon("res/gui/icons/iconFolderOpen.png");
    
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
        setBackground(ThemeManager.COLOR_PANEL);
        setForeground(ThemeManager.COLOR_FOREGROUND);
        
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
        
        if (node.isLeaf()) {
            setIcon(ICON_CLOSED);
        }
        
        return this;
    }
}
