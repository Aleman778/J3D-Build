package build.editor.ui.acomponents;

import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JRadioButton;
import javax.swing.JToolTip;

import build.editor.Util;
import build.editor.manager.ThemeManager;

public class AToggleButton extends JRadioButton {

    private static final long serialVersionUID = 1L;
    private boolean hover;
    private boolean borders;

    public AToggleButton(String iconClassPath) {
        super();
        setIcon(new ImageIcon(Util.loadImage(iconClassPath)));
        setAlignmentX(Component.CENTER_ALIGNMENT);
        setSelected(true);
        setBorderPainted(false);
        setFocusable(true);
        setFocusPainted(false);
        setOpaque(false);
        setForeground(ThemeManager.COLOR_FOREGROUND);
        hover = false;

        addMouseListener(new MouseListener() {

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
                hover = false;
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                hover = true;
            }

            @Override
            public void mouseClicked(MouseEvent e) {
            }
        });
    }

    public AToggleButton(String iconClassPath, String text, boolean borders) {
        this(iconClassPath);
        this.borders = borders;
        setText(text);
        setFont(new Font("Segoe UI", Font.PLAIN, 14));
    }

    @Override
    protected void paintComponent(Graphics g) {
        boolean value = isSelected();
        if (value) {
            if (borders) {
                g.setColor(ThemeManager.COLOR_ITEM);
            } else {
                g.setColor(ThemeManager.COLOR_ITEM_DISABLED);
            }
        } else if (hover && !borders) {
            g.setColor(ThemeManager.COLOR_ITEM);
        } else {
            g.setColor(ThemeManager.COLOR_PANEL);
        }
        g.fillRect(0, 0, getWidth(), getHeight());
        if (value || hover) {
            g.setColor(ThemeManager.COLOR_ITEM_DISABLED);
        } else {
            g.setColor(ThemeManager.COLOR_ITEM);
        }
        if (borders) {
            g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
        }
        super.paintComponent(g);
    }

    @Override
    public JToolTip createToolTip() {
        return new AToolTip(this);
    }
}
