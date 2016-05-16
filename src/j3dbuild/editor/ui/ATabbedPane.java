package j3dbuild.editor.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import javax.swing.plaf.metal.MetalTabbedPaneUI;

import j3dbuild.utils.ThemeUtils;

public class ATabbedPane extends JTabbedPane {

    private static final long serialVersionUID = 1L;
    private boolean dragging = false;
    private int dragTabIndex = -1;

    public ATabbedPane(int tabPlacement) {
        super(tabPlacement);

        setUI(new ATabbedPaneUI());
        setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        setForeground(ThemeUtils.COLOR_SELECTION_FOREGROUND);
        setBackground(ThemeUtils.COLOR_BACKGROUND);
        setFocusable(false);

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    if (!dragging) {
                        dragTabIndex = getUI().tabForCoordinate(ATabbedPane.this, e.getX(), e.getY());
                        if (dragTabIndex >= 0 && dragTabIndex < getTabCount()) {
                            dragging = true;
                        }
                    }
                    if (dragging && dragTabIndex >= 0) {
                        int insertIndex = getUI().tabForCoordinate(ATabbedPane.this, e.getX(), e.getY());
                        Component component = getComponentAt(dragTabIndex);
                        TabPanel tab = (TabPanel) getTabComponentAt(dragTabIndex);
                        if (insertIndex >= 0 && dragTabIndex != insertIndex && tab != null) {

                            removeTabAt(dragTabIndex);
                            insertTab(tab.getTitle(), null, component, null, insertIndex);
                            setTabComponentAt(insertIndex, tab);
                            setSelectedIndex(insertIndex);
                            dragTabIndex = insertIndex;
                        } else if (dragTabIndex != insertIndex && !getBounds().contains(e.getX(), 10)) {
                            //Create a TabbedWindow
                        }
                    }
                }
            }
        });

        addMouseListener(new MouseAdapter() {

            @Override
            public void mouseReleased(MouseEvent e) {
                dragging = false;
            }
        });
    }

    public ATabbedPane() {
        this(ATabbedPane.TOP);
    }

    @Override
    public Component add(String title, Component component) {
        Component comp = super.add(title, component);

        //Create tab panel
        TabPanel tab = new TabPanel(title);

        setTabComponentAt(indexOfComponent(component), tab);

        return comp;
    }

    class ATabbedPaneUI extends MetalTabbedPaneUI {

        public ATabbedPaneUI() {
            setBorder(null);

        }

        @Override
        protected void paintContentBorder(Graphics g, int tabPlacement, int selectedIndex) {
            g.setColor(getBackground());
            g.fillRect(0, 0, getWidth(), getHeight());

            if (getTabCount() > 0) {
                g.setColor(ThemeUtils.COLOR_ITEM_SELECTED);
                g.fillRect(2, 23, getWidth() - 5, 4);
            }
        }

        @Override
        protected void paintTabBackground(Graphics g, int tabPlacement,
                int tabIndex, int x, int y, int w, int h, boolean isSelected) {
            if (isSelected) {
                g.setColor(ThemeUtils.COLOR_ITEM_SELECTED);
            } else {
                g.setColor(getBackground());
            }
            
            g.fillRect(Math.max(2, x), y - 3, Math.min(getWidth() - 5, w - 4), h + 3);
        }

        @Override
        protected void paintTabBorder(Graphics g, int tabPlacement,
                int tabIndex, int x, int y, int w, int h, boolean isSelected) {
        }

        @Override
        protected JButton createScrollButton(int direction) {
            JButton button = super.createScrollButton(direction);
            button.setBorder(new LineBorder(Color.WHITE, 8));
            return button;
        }
    }
}
