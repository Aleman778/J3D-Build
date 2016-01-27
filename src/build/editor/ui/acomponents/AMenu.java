package build.editor.ui.acomponents;

import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.plaf.basic.BasicPopupMenuUI;

import build.editor.manager.ThemeManager;

public class AMenu extends JMenu {

    private static final long serialVersionUID = 1L;

    public AMenu(String text) {
        super(text);

        setForeground(ThemeManager.COLOR_FOREGROUND);
        getPopupMenu().setUI(new APopupMenuUI());
    }

    public AMenu() {
        this("");
    }

    class APopupMenuUI extends BasicPopupMenuUI {

        @Override
        public void paint(Graphics g, JComponent c) {
            g.setColor(ThemeManager.COLOR_PANEL);
            g.fillRect(0, 0, getPopupMenu().getWidth() - 1, getPopupMenu().getHeight() - 1);
        }
    }
}
