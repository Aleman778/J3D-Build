package j3dbuild.editor.ui;

import javax.swing.ImageIcon;
import javax.swing.JMenuItem;

import j3dbuild.utils.ThemeUtils;

public class AMenuItem extends JMenuItem {

    private static final long serialVersionUID = 1L;

    public AMenuItem(String text, String iconClasspath) {
        super(text);
        setIcon(new ImageIcon(getClass().getResource("/" + iconClasspath)));
        setForeground(ThemeUtils.COLOR_FOREGROUND);
        
    }

    public AMenuItem(String text) {
        super(text);
        setForeground(ThemeUtils.COLOR_FOREGROUND);
    }

    public AMenuItem() {
        this("");
    }
}
