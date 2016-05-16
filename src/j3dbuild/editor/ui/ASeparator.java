package j3dbuild.editor.ui;

import javax.swing.JSeparator;

import j3dbuild.utils.ThemeUtils;

public class ASeparator extends JSeparator {

    private static final long serialVersionUID = 1L;

    public ASeparator() {
        setBackground(ThemeUtils.COLOR_ITEM_DISABLED);
        setForeground(ThemeUtils.COLOR_ITEM_DISABLED);
    }
}
