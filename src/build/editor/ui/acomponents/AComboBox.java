package build.editor.ui.acomponents;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicComboBoxUI;

import build.editor.manager.ThemeManager;

public class AComboBox extends JComboBox<Object> {

    private static final long serialVersionUID = 1L;

    public AComboBox() {
        super();
        setForeground(ThemeManager.COLOR_FOREGROUND);
        setBorder(new LineBorder(ThemeManager.COLOR_ITEM));
        setUI(new AComboBoxUI());
        setFocusable(false);
    }

    class AComboBoxUI extends BasicComboBoxUI {

        public AComboBoxUI() {
            setBackground(ThemeManager.COLOR_PANEL);
        }

        @Override
        protected JButton createArrowButton() {
            AButton btn = new AButton("", false);
            btn.setIcon(new ImageIcon("res/hud/btnArrowDown.png"));
            return btn;
        }
    }
}
