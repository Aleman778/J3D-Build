package j3dbuild.editor.ui.acomponents;

import javax.swing.JPanel;

import j3dbuild.editor.manager.ThemeManager;

public class APanel extends JPanel {

    private static final long serialVersionUID = 1L;

    public APanel() {
        super();
        setBackground(ThemeManager.COLOR_PANEL);
        setForeground(ThemeManager.COLOR_FOREGROUND);
        setFocusable(true);
    }
}
