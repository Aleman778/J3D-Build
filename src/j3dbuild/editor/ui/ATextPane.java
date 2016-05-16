package j3dbuild.editor.ui;

import j3dbuild.utils.ThemeUtils;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JTextPane;

public class ATextPane extends JTextPane {

    private static final long serialVersionUID = 1L;
    private Color borderColor;

    public ATextPane() {
        super();
        borderColor = ThemeUtils.COLOR_ITEM;
        setBackground(ThemeUtils.COLOR_BACKGROUND);
        setForeground(ThemeUtils.COLOR_FOREGROUND);
        setSelectionColor(ThemeUtils.COLOR_SELECTION);
        setCaretColor(ThemeUtils.COLOR_FOREGROUND);
        setSelectedTextColor(new Color(ThemeUtils.COLOR_SELECTION.getRGB()).brighter().brighter());
        setBorder(BorderFactory.createEmptyBorder());
    }
}
