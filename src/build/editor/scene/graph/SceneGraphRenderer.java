package build.editor.scene.graph;

import build.editor.manager.ThemeManager;
import build.editor.scene.Universe;
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

public class SceneGraphRenderer extends DefaultTreeCellRenderer {

    private static final ImageIcon ICON_UNIVERSE = new ImageIcon("res/gui/icons/iconUniverse.png");
    private static final ImageIcon ICON_LOCALE = new ImageIcon("res/gui/icons/iconLocale.png");
    private static final ImageIcon ICON_BRANCHGROUP = new ImageIcon("res/gui/icons/iconBranchGroup.png");
    private static final ImageIcon ICON_TRANSFORMGROUP = new ImageIcon("res/gui/icons/iconTransformGroup.png");
    private static final ImageIcon ICON_SHAPE3D = new ImageIcon("res/gui/icons/iconShape3D.png");
    private static final ImageIcon ICON_CLOSED = new ImageIcon("res/gui/icons/iconFolderClosed.png");
    private static final ImageIcon ICON_GROUP = new ImageIcon("res/gui/icons/iconGroup.png");
    private static final ImageIcon ICON_LEAF = new ImageIcon("res/gui/icons/iconLeaf.png");
    private static final ImageIcon ICON_OPEN = new ImageIcon("res/gui/icons/iconFolderOpen.png");
    
    public SceneGraphRenderer() {
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
        
        if (node.getUserObject() instanceof Universe) {
            setIcon(ICON_UNIVERSE);
        } else if (node.getUserObject() instanceof Locale) {
            setIcon(ICON_LOCALE);
        } else if (node.getUserObject() instanceof BranchGroup) {
            setIcon(ICON_BRANCHGROUP);
        } else if (node.getUserObject() instanceof TransformGroup) {
            setIcon(ICON_TRANSFORMGROUP);
        } else if (node.getUserObject() instanceof Group) {
            setIcon(ICON_GROUP);
        } else if (node.getUserObject() instanceof Shape3D) {
            setIcon(ICON_SHAPE3D);
        } else if (node.isLeaf()) {
            setIcon(ICON_LEAF);
        }
        
        return this;
    }
}
