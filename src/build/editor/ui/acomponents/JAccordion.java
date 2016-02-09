package build.editor.ui.acomponents;

import build.editor.manager.ThemeManager;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class JAccordion extends JPanel {
    
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
        setLayout(new GridLayout(0, 1));
        
        btnDropdown = new AButton("");
        btnDropdown.setPreferredSize(new Dimension(18, 18));
        btnDropdown.setIcon(ICON_EXPANDED);
        btnDropdown.setHighlightBg(ThemeManager.COLOR_SELECTION);
        
        label = new ALabel(title);
        
        topbar = new JPanel(new FlowLayout(FlowLayout.LEFT, 2, 2));
        topbar.setBackground(ThemeManager.COLOR_ITEM);
        
        topbar.add(btnDropdown);
        topbar.setPreferredSize(new Dimension(topbar.getPreferredSize().width, 24));
        topbar.setMaximumSize(new Dimension(Integer.MAX_VALUE, 24));
        topbar.add(Box.createHorizontalStrut(4));
        topbar.add(label);
        
        expanded = true;
        
        add(topbar);
    }
    
    public void setExpanded(boolean enable) {
        btnDropdown.setIcon(enable ? ICON_EXPANDED : ICON_COLLAPSED);
        
    }
    
    public void getExpanded() {
        
    }
    
    public void expandContent() {
        setExpanded(true);
    }
    
    public void collapseContent() {
        setExpanded(false);
    }
    
    public void setContent(JPanel content) {
        this.content = content;
        add(content);
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
