package j3dbuild.editor.ui;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicComboBoxUI;

import j3dbuild.utils.ThemeUtils;

public class AComboBox extends JComboBox<Object> {

    private static final long serialVersionUID = 1L;

    public AComboBox() {
        super();
        setForeground(ThemeUtils.COLOR_FOREGROUND);
        setBorder(new LineBorder(ThemeUtils.COLOR_ITEM));
        setUI(new AComboBoxUI());
        setFocusable(false);
    }

    class AComboBoxUI extends BasicComboBoxUI {

        public AComboBoxUI() {
            setBackground(ThemeUtils.COLOR_PANEL);
        }

        @Override
        protected JButton createArrowButton() {
            AButton btn = new AButton("", false);
            btn.setIcon(new ImageIcon("res/hud/btnArrowDown.png"));
            return btn;
        }
    }
}
