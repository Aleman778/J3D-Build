package j3dbuild.editor.ui;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JToolTip;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import j3dbuild.utils.ThemeUtils;

public class AButton extends JButton {

    private static final long serialVersionUID = 1L;

    private static Border inactiveBorder = new LineBorder(ThemeUtils.COLOR_ITEM);
    private static Border activeBorder = new LineBorder(ThemeUtils.COLOR_ITEM_DISABLED);
    private static Border errorBorder = new LineBorder(ThemeUtils.COLOR_ERROR);
    private boolean useBorders = false;
    private Color bg, highlightBg, pressedBg;

    public AButton() {
        this("");
    }
    
    public AButton(boolean useBorders) {
        this("", useBorders);
    }
    
    public AButton(String text, boolean useBorders) {
        this(text);
        this.useBorders = useBorders;
        if (!useBorders) {
            setBorder(null);
        }
    }

    public AButton(String text, Color bg, Color highlightBg, Color pressedBg) {
        this(text);
        this.useBorders = false;
        this.bg = bg;
        this.highlightBg = highlightBg;
        this.pressedBg = pressedBg;
        setBorder(null);
        setBackground(bg);
    }

    public AButton(String text) {
        super(text);
        this.bg = ThemeUtils.COLOR_PANEL;
        this.highlightBg = ThemeUtils.COLOR_ITEM;
        this.pressedBg = ThemeUtils.COLOR_ITEM_SELECTED;

        if (useBorders) {
            setBorder(inactiveBorder);
        }
        setBackground(ThemeUtils.COLOR_PANEL);
        setForeground(ThemeUtils.COLOR_FOREGROUND);
        setContentAreaFilled(false);
        setOpaque(true);
        setFocusable(true);
        setFocusPainted(false);

        addMouseListener(new MouseListener() {

            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.getButton() == 1) {
                    setBackground(bg);
                }
                if (!isEnabled()) {
                    setBorder(inactiveBorder);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == 1 && isEnabled()) {
                    if (pressedBg == null) {
                        setBackground(ThemeUtils.COLOR_ITEM);
                    } else {
                        setBackground(pressedBg);
                    }
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (useBorders) {
                    setBorder(inactiveBorder);
                } else {
                    if (bg != null) {
                        setBackground(bg);
                    } else {
                        setBackground(getParent().getBackground());
                    }
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if (useBorders && isEnabled()) {
                    setBorder(activeBorder);
                } else {
                    if (highlightBg != null) {
                        setBackground(highlightBg);
                    }
                }
            }

            @Override
            public void mouseClicked(MouseEvent e) {

            }
        });
    }

    @Override
    public JToolTip createToolTip() {
        return new AToolTip(this);
    }

    public void setBG(Color bg) {
        this.bg = bg;
        setBackground(bg);
        repaint();
    }

    public void setHighlightBg(Color highlightBg) {
        this.highlightBg = highlightBg;
    }

    public void setPressedBg(Color pressedBg) {
        this.pressedBg = pressedBg;
    }
}
