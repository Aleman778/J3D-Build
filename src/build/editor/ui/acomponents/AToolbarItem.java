package build.editor.ui.acomponents;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import build.editor.manager.ThemeManager;

public class AToolbarItem extends JButton {

    private static final long serialVersionUID = 1L;

    public AToolbarItem(String iconClasspath) {
        super("");
        setBackground(ThemeManager.COLOR_PANEL);
        setFocusable(false);
        setIcon(new ImageIcon(iconClasspath));
    }
}
