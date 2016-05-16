package j3dbuild.editor.ui;

import javax.swing.JPanel;

import j3dbuild.utils.ThemeUtils;

public class APanel extends JPanel {

    private static final long serialVersionUID = 1L;

    public APanel() {
        super();
        setBackground(ThemeUtils.COLOR_PANEL);
        setForeground(ThemeUtils.COLOR_FOREGROUND);
        setFocusable(true);
    }
}
