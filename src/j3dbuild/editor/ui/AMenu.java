package j3dbuild.editor.ui;

import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.plaf.basic.BasicPopupMenuUI;

import j3dbuild.utils.ThemeUtils;

public class AMenu extends JMenu {

    private static final long serialVersionUID = 1L;

    public AMenu(String text) {
        super(text);

        setForeground(ThemeUtils.COLOR_FOREGROUND);
        getPopupMenu().setUI(new APopupMenuUI());
    }

    public AMenu() {
        this("");
    }

    class APopupMenuUI extends BasicPopupMenuUI {

        @Override
        public void paint(Graphics g, JComponent c) {
            g.setColor(ThemeUtils.COLOR_BACKGROUND);
            g.fillRect(0, 0, getPopupMenu().getWidth() - 1, getPopupMenu().getHeight() - 1);
        }
    }
}
