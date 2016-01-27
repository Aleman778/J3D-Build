package build.editor.ui.acomponents;

import javax.swing.ImageIcon;
import javax.swing.JMenuItem;

import build.editor.manager.ThemeManager;

public class AMenuItem extends JMenuItem {

    private static final long serialVersionUID = 1L;

    public AMenuItem(String text, String iconClasspath) {
        super(text);
        setIcon(new ImageIcon(getClass().getResource(iconClasspath)));
        setForeground(ThemeManager.COLOR_FOREGROUND);
        
    }

    public AMenuItem(String text) {
        super(text);
        setForeground(ThemeManager.COLOR_FOREGROUND);
    }

    public AMenuItem() {
        this("");
    }
}
