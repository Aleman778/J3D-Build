package j3dbuild.editor.ui.acomponents;

import javax.swing.JLabel;

import j3dbuild.editor.manager.ThemeManager;

public class ALabel extends JLabel {

    private static final long serialVersionUID = 1L;

    public ALabel() {
        this("");
    }

    public ALabel(String text) {
        super(text);
        setForeground(ThemeManager.COLOR_FOREGROUND);
    }
}
