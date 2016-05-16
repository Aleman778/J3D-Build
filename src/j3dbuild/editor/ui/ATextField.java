package j3dbuild.editor.ui;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import j3dbuild.utils.ThemeUtils;

public class ATextField extends JTextField {

    private static final long serialVersionUID = 1L;
    private Color borderColor;

    public ATextField() {
        super();
        borderColor = ThemeUtils.COLOR_ITEM;
        setBackground(ThemeUtils.COLOR_BACKGROUND);
        setForeground(ThemeUtils.COLOR_FOREGROUND);
        setSelectionColor(ThemeUtils.COLOR_SELECTION);
        setCaretColor(ThemeUtils.COLOR_FOREGROUND);
        setSelectedTextColor(new Color(ThemeUtils.COLOR_SELECTION.getRGB()).brighter().brighter());
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
                setBorder(new LineBorder(ThemeUtils.COLOR_ITEM_BORDER));
            }
        });
    }

    public ATextField(Color borderColor) {
        this();
        this.borderColor = borderColor;
        setBorder(new LineBorder(borderColor));
    }
}
