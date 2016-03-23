package j3dbuild.editor.ui.acomponents;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JMenuBar;

import j3dbuild.editor.manager.ThemeManager;

public class AMenuBar extends JMenuBar {

    private static final long serialVersionUID = 1L;

    public AMenuBar() {
        setBorder(null);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(ThemeManager.COLOR_PANEL);
        g2d.fillRect(0, 0, getWidth(), getHeight());
    }

}
