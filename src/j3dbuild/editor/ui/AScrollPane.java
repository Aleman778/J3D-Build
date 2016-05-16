package j3dbuild.editor.ui;

import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;

import j3dbuild.utils.ThemeUtils;
import java.awt.Component;
import javax.swing.BorderFactory;

public class AScrollPane extends JScrollPane {

    private static final long serialVersionUID = 1L;

    public AScrollPane() {
        this(false);
    }

    public AScrollPane(Component cmpnt) {
        super(cmpnt);
    }
    
    public AScrollPane(boolean border) {
        super();
        if (border) {
            setBorder(new LineBorder(ThemeUtils.COLOR_ITEM));
        } else {
            setBorder(null);
        }
        setBackground(ThemeUtils.COLOR_ITEM);
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
