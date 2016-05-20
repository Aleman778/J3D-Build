package j3dbuild.editor.ui;

import j3dbuild.core.Application;
import java.awt.Component;
import java.awt.Graphics;
import javax.swing.JPopupMenu;
import j3dbuild.utils.ThemeUtils;
import java.awt.Color;
import javax.swing.JSeparator;
import javax.swing.border.LineBorder;

public class APopupMenu extends JPopupMenu {

    public APopupMenu(Component... menuItems) {
        for (Component menuItem: menuItems) {
            menuItem.setForeground(ThemeUtils.COLOR_FOREGROUND);
            add(menuItem);
        }
        setForeground(ThemeUtils.COLOR_FOREGROUND);
        setBorder(new LineBorder(ThemeUtils.COLOR_ITEM_SELECTED, 1));
        setDefaultLightWeightPopupEnabled(false);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(ThemeUtils.COLOR_PANEL);
        g.fillRect(getX(), getY(), getWidth(), getHeight());
    }

    public void showMenu(int x, int y) {
        show(Application.instance, x, y);
    }
}
