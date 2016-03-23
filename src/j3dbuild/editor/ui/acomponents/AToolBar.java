package j3dbuild.editor.ui.acomponents;

import j3dbuild.editor.manager.ThemeManager;
import javax.swing.JToolBar;

public class AToolBar extends JToolBar {

    private static final long serialVersionUID = 1L;

    public AToolBar() {
        super();
        setBackground(ThemeManager.COLOR_PANEL);
        setForeground(ThemeManager.COLOR_FOREGROUND);
        setFocusable(true);
    }
}
