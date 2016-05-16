package j3dbuild.editor.ui;

import j3dbuild.utils.ThemeUtils;
import javax.swing.JToolBar;

public class AToolBar extends JToolBar {

    private static final long serialVersionUID = 1L;

    public AToolBar() {
        super();
        setBackground(ThemeUtils.COLOR_PANEL);
        setForeground(ThemeUtils.COLOR_FOREGROUND);
        setFocusable(true);
    }
}
