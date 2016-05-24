package j3dbuild.editor.ui;

import j3dbuild.utils.ThemeUtils;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class ADialog extends JFrame {

    private final ALabel titleLabel;
    private final ALabel subjectLabel;
    private int x, y;
    
    public ADialog() {
        setUndecorated(true);
        
        setLayout(new BorderLayout());
        setBackground(ThemeUtils.COLOR_PANEL);
        ((JPanel) getContentPane()).setBorder(new LineBorder(ThemeUtils.COLOR_ITEM_SELECTED, 1));
        APanel titlebar = new APanel();
        titlebar.setPreferredSize(new Dimension(100, 28));
        titlebar.setBackground(ThemeUtils.COLOR_ITEM);
        titlebar.setLayout(new FlowLayout(FlowLayout.LEFT, 4, 7));
        add(titlebar, BorderLayout.NORTH);
        titlebar.add(Box.createHorizontalStrut(2));
        
        subjectLabel = new ALabel("");
        subjectLabel.setForeground(ThemeUtils.COLOR_ITEM_SELECTED);
        titlebar.add(subjectLabel);
        
        titleLabel = new ALabel("");
        titlebar.add(titleLabel);
        
        titlebar.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent me) {
                x = me.getX();
                y = me.getY();
            }
        });
        
        titlebar.addMouseMotionListener(new MouseAdapter() {

            @Override
            public void mouseDragged(MouseEvent me) {
                setLocation(getLocation().x + me.getX() - x, getLocation().y + me.getY() - y);
            }
        });
    }
    
    public void setSubject(String subject) {
        subjectLabel.setText(subject);
    }

    public String getSubject() {
        return subjectLabel.getText();
    }

    @Override
    public void setTitle(String title) {
        super.setTitle(title);
        titleLabel.setText(title);
    }
}
