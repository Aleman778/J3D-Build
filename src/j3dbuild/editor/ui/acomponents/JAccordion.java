package j3dbuild.editor.ui.acomponents;

import j3dbuild.editor.manager.ThemeManager;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class JAccordion extends APanel {
    
    private static final ImageIcon ICON_EXPANDED = new ImageIcon("res/gui/icons/iconArrowDown.png");
    private static final ImageIcon ICON_COLLAPSED = new ImageIcon("res/gui/icons/iconArrowRight.png");
    
    private boolean expanded;
    
    private String title;
    private ImageIcon icon;
    private JPanel content;
    private JPanel topbar;
    private AButton btnDropdown;
    private JLabel label;
    
    public JAccordion() {
        this("");
    }
    
    public JAccordion(String title) {
        setLayout(new BorderLayout(0, 0));
        
        btnDropdown = new AButton("");
        btnDropdown.setPreferredSize(new Dimension(18, 18));
        btnDropdown.setIcon(ICON_EXPANDED);
        btnDropdown.setHighlightBg(ThemeManager.COLOR_SELECTION);
        btnDropdown.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseReleased(MouseEvent me) {
                setExpanded(!getExpanded());
            }
            
        });
        
        label = new ALabel(title);
        
        topbar = new JPanel(new FlowLayout(FlowLayout.LEFT, 2, 2));
        topbar.setBackground(ThemeManager.COLOR_ITEM);
        
        topbar.add(btnDropdown);
        topbar.setPreferredSize(new Dimension(getPreferredSize().width, 22));
        topbar.add(Box.createHorizontalStrut(4));
        topbar.add(label);
        
        expanded = true;
        
        add(topbar, BorderLayout.NORTH);
    }
    
    public void setExpanded(boolean enable) {
        expanded = enable;
        btnDropdown.setIcon(expanded ? ICON_EXPANDED : ICON_COLLAPSED);
        setPreferredSize(new Dimension(getPreferredSize().width, expanded ? 24 + content.getPreferredSize().height : 24));
        revalidate();
        repaint();
        content.revalidate();
        content.repaint();
    }
    
    public boolean getExpanded() {
        return expanded;
    }
    
    public void expandContent() {
        setExpanded(true);
    }
    
    public void collapseContent() {
        setExpanded(false);
    }
    
    public void setContent(JPanel content) {
        this.content = content;
        add(content, BorderLayout.CENTER);
        setPreferredSize(new Dimension(getPreferredSize().width, 24 + content.getPreferredSize().height));
        revalidate();
        repaint();
    }
    
    public void setIcon(ImageIcon icon) {
        this.icon = icon;
        AButton btnIcon = new AButton("");
        btnIcon.setPreferredSize(new Dimension(16, 16));
        btnIcon.setIcon(icon);
        btnIcon.setOpaque(false);
        topbar.add(btnIcon, 1);
    }
    
    public void setTitle(String title) {
        this.title = title;
        label.setText(title);
    }

    public void setTopbar(JPanel topbar) {
        this.topbar = topbar;
    }

    public void setBtnDropdown(AButton btnDropdown) {
        this.btnDropdown = btnDropdown;
    }

    public void setLabel(JLabel label) {
        this.label = label;
    }
}
