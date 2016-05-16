package j3dbuild.editor.ui;

import javax.swing.JLabel;

import j3dbuild.utils.ThemeUtils;

public class ALabel extends JLabel {

    private static final long serialVersionUID = 1L;

    public ALabel() {
        this("");
    }

    public ALabel(String text) {
        super(text);
        setForeground(ThemeUtils.COLOR_FOREGROUND);
    }
}
