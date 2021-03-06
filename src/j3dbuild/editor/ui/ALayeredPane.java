package j3dbuild.editor.ui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.BorderFactory;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import j3dbuild.utils.ThemeUtils;

public class ALayeredPane extends JLayeredPane {

    private static final long serialVersionUID = 1L;
    private int prevX, prevY;
    private JPanel contentPane;

    public ALayeredPane(String title) {
        prevX = 0;
        prevY = 0;
        setBorder(BorderFactory.createTitledBorder(new LineBorder(ThemeUtils.COLOR_ITEM), title, 0, 0, null, ThemeUtils.COLOR_FOREGROUND));
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                prevX = e.getXOnScreen();
                prevY = e.getYOnScreen();
            }
        });
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                setLocation(getX() + (e.getXOnScreen() - prevX), getY() + (e.getYOnScreen() - prevY));
                prevX = e.getXOnScreen();
                prevY = e.getYOnScreen();
            }
        });
        moveToFront(this);
    }

    public ALayeredPane(String title, JPanel content) {
        this(title);
        setContent(content);
    }

    public ALayeredPane(String title, APanel content) {
        this(title);
        setContent(content);
    }

    public void setContent(JPanel panel) {
        this.contentPane = panel;
        contentPane.setBounds(2, 7, getWidth() - 4, getHeight() - 14);
        add(contentPane, JLayeredPane.DRAG_LAYER);
    }

    public void setContent(APanel panel) {
        this.contentPane = panel;
        contentPane.setBounds(2, 7, getWidth() - 4, getHeight() - 14);
        setBackground(ThemeUtils.COLOR_PANEL);
        setForeground(ThemeUtils.COLOR_FOREGROUND);
        add(contentPane, JLayeredPane.DRAG_LAYER);
    }

    public JPanel getContent() {
        return contentPane;
    }
}
