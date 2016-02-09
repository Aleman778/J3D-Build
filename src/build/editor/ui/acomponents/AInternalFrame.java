package build.editor.ui.acomponents;

import build.editor.manager.ThemeManager;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JRootPane;
import javax.swing.border.LineBorder;

public class AInternalFrame extends JInternalFrame {

    private ALabel labelTitle;
    private Point mouse;
    
    public AInternalFrame() {
        putClientProperty("JInternalFrame.isPalette", Boolean.TRUE);
        getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        final APanel panelTitlebar = new APanel();
        panelTitlebar.setPreferredSize(new Dimension(getPreferredSize().width, 26));
        panelTitlebar.setLayout(new BoxLayout(panelTitlebar, BoxLayout.LINE_AXIS));
        panelTitlebar.setBackground(ThemeManager.COLOR_ITEM);
        AButton buttonClose = new AButton(false);
        buttonClose.setBG(ThemeManager.COLOR_ITEM);
        buttonClose.setHighlightBg(ThemeManager.COLOR_ERROR);
        buttonClose.setIcon(new ImageIcon(getClass().getResource("/gui/icons/iconClose2.png")));
        buttonClose.setPreferredSize(new Dimension(28, 24));
        labelTitle = new ALabel(getTitle());
        labelTitle.setForeground(ThemeManager.COLOR_ITEM_SELECTED);
        panelTitlebar.add(Box.createHorizontalStrut(8));
        panelTitlebar.add(labelTitle);
        panelTitlebar.add(Box.createHorizontalGlue());
        panelTitlebar.add(buttonClose);
        panelTitlebar.add(Box.createHorizontalStrut(2));
        ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);
        setBorder(new LineBorder(ThemeManager.COLOR_ITEM_SELECTED, 1));
        getContentPane().setBackground(ThemeManager.COLOR_PANEL);
        getContentPane().add(panelTitlebar, BorderLayout.NORTH);
        panelTitlebar.addMouseMotionListener(new MouseAdapter() {

            @Override
            public void mouseDragged(MouseEvent me) {
                setLocation(getLocation().x + me.getXOnScreen() - mouse.x,
                        getLocation().y + me.getYOnScreen() - mouse.y);  
                mouse = new Point(me.getXOnScreen(), me.getYOnScreen());
            }
            
        });
        panelTitlebar.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                mouse = new Point(me.getXOnScreen(), me.getYOnScreen());
            }
        });
        buttonClose.addActionListener((ActionEvent ae) -> {
            dispose();
        });
        setVisible(true);
    }

    @Override
    public void setTitle(String string) {
        super.setTitle(string);
        labelTitle.setText(string);
    }
}
