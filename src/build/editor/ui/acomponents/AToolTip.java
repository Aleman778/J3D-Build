package build.editor.ui.acomponents;

import javax.swing.JComponent;
import javax.swing.JToolTip;
import javax.swing.border.LineBorder;

import build.editor.manager.ThemeManager;

public class AToolTip extends JToolTip {

    private static final long serialVersionUID = 1L;

    public AToolTip(JComponent component) {
        super();
        setComponent(component);
        setBackground(ThemeManager.COLOR_BACKGROUND);
        setBorder(new LineBorder(ThemeManager.COLOR_ITEM));
        setForeground(ThemeManager.COLOR_FOREGROUND);
    }
}
