package build.editor.ui.acomponents;

import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;

import build.editor.manager.ThemeManager;

public class AScrollPane extends JScrollPane {

    private static final long serialVersionUID = 1L;

    public AScrollPane(boolean border) {
        super();
        if (border) {
            setBorder(new LineBorder(ThemeManager.COLOR_ITEM));
        } else {
            setBorder(null);
        }
        setBackground(ThemeManager.COLOR_ITEM);
    }

    @Override
    public JScrollBar createVerticalScrollBar() {
        return new AScrollBar();
    }

    @Override
    public JScrollBar createHorizontalScrollBar() {
        AScrollBar scrollbar = new AScrollBar();
        scrollbar.setOrientation(JScrollBar.HORIZONTAL);
        return scrollbar;
    }
}
