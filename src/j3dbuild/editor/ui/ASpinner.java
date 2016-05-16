package j3dbuild.editor.ui;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicSpinnerUI;

import j3dbuild.utils.ThemeUtils;

public class ASpinner extends JSpinner {

    private static final long serialVersionUID = 1L;

    public ASpinner() {
        super();
        setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
        setBackground(ThemeUtils.COLOR_BACKGROUND);
        setBorder(new LineBorder(ThemeUtils.COLOR_ITEM));
        setUI(new ASpinnerUI());
        setFocusable(true);
        getEditor().setBackground(ThemeUtils.COLOR_BACKGROUND);
    }

    class ASpinnerUI extends BasicSpinnerUI {

        protected Component createNextButton() {
            JButton btn = (JButton) super.createNextButton();
            btn.setBackground(ThemeUtils.COLOR_ITEM_DISABLED);
            btn.setBorder(new LineBorder(ThemeUtils.COLOR_ITEM_DISABLED));
            btn.addMouseListener(new MouseAdapter() {
                public void mouseReleased(MouseEvent e) {
                    btn.setBackground(ThemeUtils.COLOR_ITEM_DISABLED);
                    btn.setBorder(new LineBorder(ThemeUtils.COLOR_ITEM_DISABLED));
                }

                public void mousePressed(MouseEvent e) {
                    btn.setBackground(ThemeUtils.COLOR_ITEM_SELECTED);
                    btn.setBorder(new LineBorder(ThemeUtils.COLOR_ITEM_SELECTED));
                }
            });
            return btn;
        }

        protected Component createPreviousButton() {
            JButton btn = (JButton) super.createPreviousButton();
            btn.setBackground(ThemeUtils.COLOR_ITEM_DISABLED);
            btn.setBorder(new LineBorder(ThemeUtils.COLOR_ITEM_DISABLED));
            btn.addMouseListener(new MouseAdapter() {
                public void mouseReleased(MouseEvent e) {
                    btn.setBackground(ThemeUtils.COLOR_ITEM_DISABLED);
                    btn.setBorder(new LineBorder(ThemeUtils.COLOR_ITEM_DISABLED));
                }

                public void mousePressed(MouseEvent e) {
                    btn.setBackground(ThemeUtils.COLOR_ITEM_SELECTED);
                    btn.setBorder(new LineBorder(ThemeUtils.COLOR_ITEM_SELECTED));
                }
            });
            return btn;
        }
    }
}
