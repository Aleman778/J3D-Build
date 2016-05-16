package j3dbuild.editor.ui;

import java.awt.FlowLayout;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import j3dbuild.utils.ThemeUtils;

public class TabPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private AButton button;
    private ALabel titleLabel;
    private boolean hover = false;
    private String title;

    public TabPanel(String title) {
        this.title = title;
        setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        setOpaque(false);

        titleLabel = new ALabel(title + " ");
        add(titleLabel);

        button = new AButton("", ThemeUtils.COLOR_ITEM_SELECTED, ThemeUtils.COLOR_ITEM_HOVER, ThemeUtils.COLOR_ITEM_BORDER);
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
        titleLabel.setText(this.title + " ");
    }
}
