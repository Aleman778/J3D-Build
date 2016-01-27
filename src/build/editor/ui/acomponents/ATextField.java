package build.editor.ui.acomponents;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import build.editor.manager.ThemeManager;

public class ATextField extends JTextField {

    private static final long serialVersionUID = 1L;
    private Color borderColor;

    public ATextField() {
        super();
        borderColor = ThemeManager.COLOR_ITEM;
        setBackground(ThemeManager.COLOR_BACKGROUND);
        setForeground(ThemeManager.COLOR_FOREGROUND);
        setSelectionColor(ThemeManager.COLOR_SELECTION);
        setCaretColor(ThemeManager.COLOR_FOREGROUND);
        setSelectedTextColor(new Color(ThemeManager.COLOR_SELECTION.getRGB()).brighter().brighter());
        setBorder(new LineBorder(borderColor));

        addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
            }
        });

        addFocusListener(new FocusListener() {
            @Override
            public void focusLost(FocusEvent e) {
                setBorder(new LineBorder(borderColor));
            }

            @Override
            public void focusGained(FocusEvent e) {
                setBorder(new LineBorder(ThemeManager.COLOR_ITEM_BORDER));
            }
        });
    }

    public ATextField(Color borderColor) {
        this();
        this.borderColor = borderColor;
        setBorder(new LineBorder(borderColor));
    }
}
