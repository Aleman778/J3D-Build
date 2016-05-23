package j3dbuild.editor.ui;

import j3dbuild.utils.ThemeUtils;
import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JEditorPane;

public class AEditorPane extends JEditorPane {

    public AEditorPane() {
        setBackground(ThemeUtils.COLOR_BACKGROUND);
        setForeground(ThemeUtils.COLOR_FOREGROUND);
        setSelectionColor(ThemeUtils.COLOR_SELECTION);
        setCaretColor(ThemeUtils.COLOR_FOREGROUND);
        setSelectedTextColor(new Color(ThemeUtils.COLOR_SELECTION.getRGB()).brighter().brighter());
        setFont(new Font("Monospaced", Font.PLAIN, 14));
        setBorder(BorderFactory.createEmptyBorder());
    }
}
