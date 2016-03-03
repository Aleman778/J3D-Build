package build.editor.ui.acomponents;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JScrollBar;
import javax.swing.plaf.metal.MetalScrollBarUI;

import build.editor.manager.ThemeManager;

public class AScrollBar extends JScrollBar {

    private static final long serialVersionUID = 1L;

    public AScrollBar() {
        setBackground(ThemeManager.COLOR_PANEL);
        setForeground(ThemeManager.COLOR_FOREGROUND);
        setUI(new ScrollBarUI());
    }

    class ScrollBarUI extends MetalScrollBarUI {

        private AScrollListener scrollListener;

        public ScrollBarUI() {
        }

        protected JButton createZeroButton() {
            JButton button = new JButton("");
            Dimension zeroDim = new Dimension(0, 0);
            button.setPreferredSize(zeroDim);
            button.setMinimumSize(zeroDim);
            button.setMaximumSize(zeroDim);
            return button;
        }

        @Override
        protected JButton createDecreaseButton(int orientation) {
            return createZeroButton();
        }

        @Override
        protected JButton createIncreaseButton(int orientation) {
            return createZeroButton();
        }

        @Override
        protected void installListeners() {
            super.installListeners();
            scrollListener = new AScrollListener();
            addMouseListener(scrollListener);
            addMouseMotionListener(scrollListener);
        }

        @Override
        protected void paintThumb(Graphics g, JComponent c, java.awt.Rectangle thumbBounds) {
            if (scrollListener.press) {
                g.setColor(ThemeManager.COLOR_ITEM_SELECTED);
            } else if (scrollListener.hover) {
                g.setColor(ThemeManager.COLOR_ITEM_HOVER);
            } else {
                g.setColor(ThemeManager.COLOR_ITEM_BORDER);
            }
            g.fillRect(thumbBounds.x + 4, thumbBounds.y + 4, thumbBounds.width - 8, thumbBounds.height - 8);
        }

        @Override
        protected void paintTrack(Graphics g, JComponent c, java.awt.Rectangle trackBounds) {
            g.setColor(ThemeManager.COLOR_ITEM);
            g.fillRect(trackBounds.x + 4, trackBounds.y + 4, trackBounds.width - 8, trackBounds.height - 8);
        }

        class AScrollListener extends MouseAdapter {

            public boolean hover;
            public boolean press;

            public AScrollListener() {
                hover = false;
                press = false;
            }

            public void mousePressed(MouseEvent e) {
                press = isThumb(e);
            }

            public void mouseReleased(MouseEvent e) {
                press = false;
            }

            public void mouseEntered(MouseEvent e) {
                hover = isThumb(e);
            }

            public void mouseMoved(MouseEvent e) {
                hover = isThumb(e);
            }

            public void mouseExited(MouseEvent e) {
                hover = false;
            }

            private boolean isThumb(MouseEvent e) {
                if (getThumbBounds().contains(e.getX(), e.getY())) {
                    return true;
                }
                return false;
            }
        }
    }
}
