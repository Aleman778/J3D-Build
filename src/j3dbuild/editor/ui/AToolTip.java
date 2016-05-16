package j3dbuild.editor.ui;

import javax.swing.JComponent;
import javax.swing.JToolTip;
import javax.swing.border.LineBorder;

import j3dbuild.utils.ThemeUtils;

public class AToolTip extends JToolTip {

    private static final long serialVersionUID = 1L;

    public AToolTip(JComponent component) {
        super();
        setComponent(component);
        setBackground(ThemeUtils.COLOR_BACKGROUND);
        setBorder(new LineBorder(ThemeUtils.COLOR_ITEM));
        setForeground(ThemeUtils.COLOR_FOREGROUND);
    }
}
