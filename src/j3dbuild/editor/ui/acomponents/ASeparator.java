package j3dbuild.editor.ui.acomponents;

import javax.swing.JSeparator;

import j3dbuild.editor.manager.ThemeManager;

public class ASeparator extends JSeparator {

    private static final long serialVersionUID = 1L;

    public ASeparator() {
        setBackground(ThemeManager.COLOR_ITEM_DISABLED);
        setForeground(ThemeManager.COLOR_ITEM_DISABLED);
    }
}
