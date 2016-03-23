package j3dbuild.editor.ui.acomponents;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.plaf.basic.BasicRadioButtonMenuItemUI;

import j3dbuild.editor.Util;
import j3dbuild.editor.manager.ThemeManager;

public class ARadioButtonMenuItem extends JRadioButtonMenuItem {

    private static final long serialVersionUID = 1L;

    private static final BufferedImage checkIcon = Util.loadImage("hud/hudMarkDot.png");

    public ARadioButtonMenuItem(String text) {
        super(text);
        setUI(new ARadioButtonMenuItemUI());
    }

    class ARadioButtonMenuItemUI extends BasicRadioButtonMenuItemUI {

        private boolean hover = false;

        public ARadioButtonMenuItemUI() {
        }

        @Override
        protected void installListeners() {
            super.installListeners();

            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    hover = true;
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    hover = false;
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    hover = false;
                }
            });
        }

        @Override
        protected void paintMenuItem(Graphics g, JComponent c, Icon checkIcon,
                Icon arrowIcon, Color background, Color foreground,
                int defaultTextIconGap) {
            checkIcon = null;
            super.paintMenuItem(g, c, checkIcon, arrowIcon, background, foreground,
                    defaultTextIconGap);
            if (isSelected()) {
                g.drawImage(ARadioButtonMenuItem.checkIcon, 4, 7, null);
            }
        }

        @Override
        protected void paintBackground(Graphics g, JMenuItem menuItem,
                Color bgColor) {
            if (hover) {
                g.setColor(ThemeManager.COLOR_SELECTION);
            } else if (isSelected()) {
                g.setColor(ThemeManager.COLOR_SELECTION.darker());
            } else {
                g.setColor(ThemeManager.COLOR_PANEL);
            }
            g.fillRect(0, 0, getWidth(), getHeight());
        }

        @Override
        protected void paintText(Graphics g, JMenuItem menuItem,
                Rectangle textRect, String text) {

            if (hover) {
                g.setColor(ThemeManager.COLOR_SELECTION.brighter().brighter());
            } else {
                g.setColor(ThemeManager.COLOR_FOREGROUND);
            }
            super.paintText(g, menuItem, textRect, text);
        }
    }
}
