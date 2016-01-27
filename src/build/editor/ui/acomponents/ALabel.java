package build.editor.ui.acomponents;

import java.awt.Font;

import javax.swing.JLabel;

import build.editor.manager.ThemeManager;

public class ALabel extends JLabel {

    private static final long serialVersionUID = 1L;
    public static Font font = new Font("Segoe UI", Font.PLAIN, 12);

    public ALabel() {
        super("");
        setForeground(ThemeManager.COLOR_FOREGROUND);
    }

    public ALabel(String text) {
        super(text);
        setForeground(ThemeManager.COLOR_FOREGROUND);
        setFont(font);
    }
}
