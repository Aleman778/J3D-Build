package build.editor.scene.graph;

import build.editor.manager.ThemeManager;
import java.awt.Component;
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
    private static final ImageIcon ICON_GROUP = new ImageIcon("res/gui/icons/iconGroup.png");
    private static final ImageIcon ICON_LEAF = new ImageIcon("res/gui/icons/iconLeaf.png");
    
    public SceneGraphRenderer() {
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
        if (((DefaultMutableTreeNode) value).getUserObject().equals("Universe")) {
            setIcon(ICON_UNIVERSE);
        } else if (((DefaultMutableTreeNode) value).getUserObject().equals("Locale")) {
            setIcon(ICON_LOCALE);
        } else if (((DefaultMutableTreeNode) value).getUserObject().equals("Branch Group")) {
            setIcon(ICON_BRANCHGROUP);
        } else if (((DefaultMutableTreeNode) value).getUserObject().equals("Textured Floor")) {
            setIcon(ICON_SHAPE3D);
        } else if (((DefaultMutableTreeNode) value).getUserObject().equals("Color Cube")) {
            setIcon(ICON_SHAPE3D);
        } else if (leaf) {
            setIcon(ICON_LEAF);
        }
        return this;
    }
}
