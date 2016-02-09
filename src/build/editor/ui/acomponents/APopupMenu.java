package build.editor.ui.acomponents;

import build.editor.J3DBuild;
import java.awt.Component;
import java.awt.Graphics;

import javax.swing.JPopupMenu;

import build.editor.manager.ThemeManager;

public class APopupMenu extends JPopupMenu {

    private static final long serialVersionUID = 1L;

    public APopupMenu(Component... menuItems) {
        for (int i = 0; i < menuItems.length; i++) {
            menuItems[i].setForeground(ThemeManager.COLOR_FOREGROUND);
            add(menuItems[i]);
        }
        setForeground(ThemeManager.COLOR_FOREGROUND);
        setDefaultLightWeightPopupEnabled(false);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(ThemeManager.COLOR_PANEL);
        g.fillRect(getX(), getY(), getWidth(), getHeight());
    }

    public void showMenu(int x, int y) {
        show(J3DBuild.instance, x, y);
    }
}
