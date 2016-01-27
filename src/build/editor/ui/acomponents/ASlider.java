package build.editor.ui.acomponents;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;
import javax.swing.JSlider;
import javax.swing.plaf.basic.BasicSliderUI;

import build.editor.manager.ThemeManager;

public class ASlider extends JSlider {

    private static final long serialVersionUID = 1L;

    public ASlider() {
        super();
        setUI(new ASliderUI(this));
    }

    class ASliderUI extends BasicSliderUI {

        @Override
        protected void installListeners(JSlider slider) {
            super.installListeners(slider);

            addMouseMotionListener(new MouseAdapter() {
                @Override
                public void mouseDragged(MouseEvent e) {
                    repaint();
                }
            });
        }

        public ASliderUI(JSlider slider) {
            super(slider);
        }

        @Override
        public void paint(Graphics g, JComponent c) {
            g.setColor(ThemeManager.COLOR_PANEL);
            g.fillRect(0, 0, getWidth(), getHeight());
            super.paint(g, c);
        }

        @Override
        public void paintTrack(Graphics g) {
            g.setColor(ThemeManager.COLOR_BACKGROUND);
            g.fillRect(0, 8, getWidth(), 8);
        }

        @Override
        public void paintThumb(Graphics g) {
            if (isEnabled()) {
                g.setColor(ThemeManager.COLOR_FOREGROUND);
            } else {
                g.setColor(ThemeManager.COLOR_ITEM);
            }
            g.fillRect((int) (getValue() * 1.92), 7, 8, 10);
            if (isEnabled()) {
                g.setColor(ThemeManager.COLOR_ITEM_SELECTED);
            } else {
                g.setColor(ThemeManager.COLOR_ITEM_DISABLED);
            }
            g.fillRect(0, 8, (int) (getValue() * 1.92), 8);
        }

        @Override
        public void paintFocus(Graphics g) {
        }
    }
}
