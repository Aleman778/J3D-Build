package j3dbuild.editor.ui.acomponents;

import j3dbuild.editor.manager.ThemeManager;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JTextPane;

public class ATextPane extends JTextPane {

    private static final long serialVersionUID = 1L;
    private Color borderColor;

    public ATextPane() {
        super();
        borderColor = ThemeManager.COLOR_ITEM;
        setBackground(ThemeManager.COLOR_BACKGROUND);
        setForeground(ThemeManager.COLOR_FOREGROUND);
        setSelectionColor(ThemeManager.COLOR_SELECTION);
        setCaretColor(ThemeManager.COLOR_FOREGROUND);
        setSelectedTextColor(new Color(ThemeManager.COLOR_SELECTION.getRGB()).brighter().brighter());
        setBorder(BorderFactory.createEmptyBorder());
    }
}
