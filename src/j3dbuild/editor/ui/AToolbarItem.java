package j3dbuild.editor.ui;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import j3dbuild.utils.ThemeUtils;

public class AToolbarItem extends JButton {

    private static final long serialVersionUID = 1L;

    public AToolbarItem(String iconClasspath) {
        super("");
        setBackground(ThemeUtils.COLOR_PANEL);
        setFocusable(false);
        setIcon(new ImageIcon(iconClasspath));
    }
}
