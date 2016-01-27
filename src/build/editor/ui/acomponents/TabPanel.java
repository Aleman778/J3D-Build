package build.editor.ui.acomponents;

import java.awt.FlowLayout;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import build.editor.manager.ThemeManager;

public class TabPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private AButton button;
    private boolean hover = false;
    private String title;

    public TabPanel(String title) {
        this.title = title;
        setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        setOpaque(false);

        ALabel titleLabel = new ALabel(title + "  ");
        add(titleLabel);

        button = new AButton("", ThemeManager.COLOR_ITEM_SELECTED, ThemeManager.COLOR_ITEM_HOVER, ThemeManager.COLOR_ITEM_BORDER);
        button.setIcon(new ImageIcon("res/icons/iconClose.png"));
        add(button);
    }

    public AButton getButton() {
        return button;
    }

    public boolean isHover() {
        return hover;
    }

    public void setHover(boolean hover) {
        this.hover = hover;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
