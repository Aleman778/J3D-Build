package build.editor.ui.acomponents;

import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JSplitPane;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicSplitPaneDivider;
import javax.swing.plaf.basic.BasicSplitPaneUI;

import build.editor.manager.ThemeManager;

public class ASplitPane extends JSplitPane {

    private static final long serialVersionUID = 1L;

    public ASplitPane() {
        setBorder(BorderFactory.createEmptyBorder());
        setUI(new BasicSplitPaneUI() {
            public BasicSplitPaneDivider createDefaultDivider() {
                return new BasicSplitPaneDivider(this) {
                    private static final long serialVersionUID = 1L;

                    public void setBorder(Border b) {
                    }

                    @Override
                    public void paint(Graphics g) {
                        g.setColor(ThemeManager.COLOR_BACKGROUND);
                        g.fillRect(0, 0, getSize().width, getSize().height);
                        super.paint(g);
                    }
                };
            }
        });
    }

    class ASplitPaneUI extends BasicSplitPaneDivider {

        private static final long serialVersionUID = 1L;

        public ASplitPaneUI(BasicSplitPaneUI ui) {
            super(ui);
        }

        @Override
        public void paint(Graphics g) {
            // TODO Auto-generated method stub
            super.paint(g);
        }
    }
}
