package build.editor;

import build.editor.manager.ThemeManager;
import build.editor.properties.PropertyType;
import build.editor.resource.JTreeResources;
import build.editor.ui.JPanelAppearanceEditor;
import build.editor.ui.JPanelSceneEditor;
import build.editor.ui.JTreeSceneGraph;
import build.editor.ui.acomponents.*;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;

public class J3DBuild extends JFrame implements ActionListener {

    public static final Collection<PropertyType> PROPERTIES_NONE = new ArrayList<>();
    public static final File PROJECT_ROOT = new File("res/");
    public static J3DBuild instance = null;
            
    private boolean running;
    private final Timer timer;
    private JPanelSceneEditor scene;
    
    //Singleton class
    private J3DBuild() {
        setLocationByPlatform(true);
        initComponents();
        jTabbedExplorer.setTabComponentAt(0, new TabPanel("Resources"));
        jTabbedExplorer.setTabComponentAt(1, new TabPanel("Console"));
        jTabbedGraph.setTabComponentAt(0, new TabPanel("Scene Graph"));
        jTabbedProperties.setTabComponentAt(0, new TabPanel("No Properties"));
        jTabbedContent.addChangeListener((ChangeEvent ce) -> {
            Component selected = jTabbedContent.getSelectedComponent();
            
            if (selected instanceof JPanelSceneEditor) {
                jTreeSceneGraph.setModel(((JPanelSceneEditor) selected).getSceneGraph());
            }
        });
        jScrollPaneProperties.revalidate();
        pack();
        
        timer = new Timer(100, this);
        timer.start();
    }
    
    public static void addContent(String title, JPanel panel) {
        instance.jTabbedContent.addTab(title, panel);
        instance.jTabbedContent.setTabComponentAt(instance.jTabbedContent.getTabCount() - 1, new TabPanel(title));
    }
    
    public static void showProperties(Collection<PropertyType> properties) {
        showProperties("Properties", properties);
    }
    
    public static void showProperties(String name, Collection<PropertyType> properties) {
        ((TabPanel) instance.jTabbedProperties.getTabComponentAt(0)).setTitle(name);
        instance.jPanelProperties.removeAll();
        instance.jPanelProperties.setPreferredSize(new Dimension(instance.jScrollPaneProperties.getWidth(), 0));
        for (PropertyType property: properties) {
            JAccordion accodion = new JAccordion(property.getName());
            accodion.setContent(property);
            if (property.getIcon() != null) {
                accodion.setIcon(property.getIcon());
            }
            accodion.setPreferredSize(new Dimension(instance.jScrollPaneProperties.getWidth() - 16, accodion.getPreferredSize().height));
            instance.jPanelProperties.add(accodion);
        }
        
        instance.jPanelProperties.revalidate();
        instance.jPanelProperties.repaint();
        instance.jScrollPaneProperties.revalidate();
        instance.jScrollPaneProperties.repaint();
    }
    
    private static int calculatePropertyHeight() {
        int height = 0;
        for (Component component: instance.jPanelProperties.getComponents()) {
            height += component.getHeight();
        }
        return height;
    }
    
    public JPanelSceneEditor getSceneEditor() {
        Component component = jTabbedContent.getSelectedComponent();
        if (component instanceof JPanelSceneEditor) {
            return (JPanelSceneEditor) component;
        }
        
        return null;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField4 = new javax.swing.JTextField();
        jDesktop = new javax.swing.JDesktopPane();
        jPanelContent = new javax.swing.JPanel();
        jTabbedGraph = new ATabbedPane();
        jScrollTree = new AScrollPane();
        jTreeSceneGraph = JTreeSceneGraph.instance;
        jTabbedProperties = new ATabbedPane();
        jScrollPaneProperties = new AScrollPane(false);
        jPanelProperties = new APanel();
        jTabbedContent = new ATabbedPane();
        jTabbedExplorer = new ATabbedPane();
        jPanel8 = new javax.swing.JPanel();
        jToolBarExplorer = new AToolBar();
        jButton5 = new AButton();
        jButton6 = new AButton();
        jSplitPane1 = new ASplitPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTreeResources = JTreeResources.instance;
        jPanelFolder = new APanel();
        jPanel1 = new APanel();
        jPanel2 = new javax.swing.JPanel();
        jToolBar1 = new AToolBar();
        jButton1 = new AButton();
        jButton2 = new AButton();
        jButton3 = new AButton();
        jButton4 = new AButton();
        jMenuBar2 = new AMenuBar();
        jMenu1 = new AMenu();
        jMenuItem1 = new AMenuItem();
        jMenuItem2 = new AMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenu2 = new AMenu();

        jTextField4.setText("jTextField4");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("J3D Build");
        setPreferredSize(new java.awt.Dimension(1280, 720));
        getContentPane().setLayout(new javax.swing.OverlayLayout(getContentPane()));

        jDesktop.setOpaque(false);

        jPanelContent.setBackground(ThemeManager.COLOR_BACKGROUND);
        jPanelContent.setLayout(new java.awt.BorderLayout(4, 4));

        jTabbedGraph.setPreferredSize(new java.awt.Dimension(256, 350));

        jScrollTree.setViewportView(jTreeSceneGraph);

        jTabbedGraph.addTab("Scene Graph", jScrollTree);

        jPanelContent.add(jTabbedGraph, java.awt.BorderLayout.WEST);

        jScrollPaneProperties.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPaneProperties.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPaneProperties.setPreferredSize(new java.awt.Dimension(300, 2));

        jPanelProperties.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 0, 0));
        jScrollPaneProperties.setViewportView(jPanelProperties);

        jTabbedProperties.addTab("Properties", jScrollPaneProperties);

        jPanelContent.add(jTabbedProperties, java.awt.BorderLayout.EAST);

        jTabbedContent.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        jPanelContent.add(jTabbedContent, java.awt.BorderLayout.CENTER);

        jTabbedExplorer.setPreferredSize(new java.awt.Dimension(1070, 320));

        jPanel8.setBackground(ThemeManager.COLOR_BACKGROUND);
        jPanel8.setLayout(new java.awt.BorderLayout(0, 4));

        jToolBarExplorer.setFloatable(false);
        jToolBarExplorer.setRollover(true);
        jToolBarExplorer.setPreferredSize(new java.awt.Dimension(13, 28));
        jToolBarExplorer.setRequestFocusEnabled(false);

        jButton5.setText("IMPORT");
        jButton5.setFocusable(false);
        jButton5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBarExplorer.add(jButton5);

        jButton6.setText("UPDATE");
        jButton6.setFocusable(false);
        jButton6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton6.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBarExplorer.add(jButton6);

        jPanel8.add(jToolBarExplorer, java.awt.BorderLayout.PAGE_START);

        jSplitPane1.setDividerLocation(250);

        jScrollPane1.setBorder(null);
        jScrollPane1.setViewportView(jTreeResources);

        jSplitPane1.setLeftComponent(jScrollPane1);
        jSplitPane1.setRightComponent(jPanelFolder);

        jPanel8.add(jSplitPane1, java.awt.BorderLayout.CENTER);

        jTabbedExplorer.addTab("Resources", jPanel8);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1065, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 292, Short.MAX_VALUE)
        );

        jTabbedExplorer.addTab("Console", jPanel1);

        jPanelContent.add(jTabbedExplorer, java.awt.BorderLayout.SOUTH);

        jPanel2.setBackground(ThemeManager.COLOR_BACKGROUND);
        jPanel2.setPreferredSize(new java.awt.Dimension(1070, 32));

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);
        jToolBar1.setMaximumSize(new java.awt.Dimension(13, 24));
        jToolBar1.setMinimumSize(new java.awt.Dimension(13, 24));
        jToolBar1.setPreferredSize(new java.awt.Dimension(13, 28));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/icons/iconNew.png"))); // NOI18N
        jButton1.setToolTipText("New Project...");
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jButton1);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/icons/iconLoad.png"))); // NOI18N
        jButton2.setToolTipText("Open Project...");
        jButton2.setFocusable(false);
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jButton2);

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/icons/iconSave.png"))); // NOI18N
        jButton3.setToolTipText("Save Project");
        jButton3.setFocusable(false);
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jButton3);

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/icons/iconPlay.png"))); // NOI18N
        jButton4.setToolTipText("Save Project");
        jButton4.setFocusable(false);
        jButton4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jButton4);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1070, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 4, Short.MAX_VALUE))
        );

        jPanelContent.add(jPanel2, java.awt.BorderLayout.NORTH);

        javax.swing.GroupLayout jDesktopLayout = new javax.swing.GroupLayout(jDesktop);
        jDesktop.setLayout(jDesktopLayout);
        jDesktopLayout.setHorizontalGroup(
            jDesktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelContent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jDesktopLayout.setVerticalGroup(
            jDesktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelContent, javax.swing.GroupLayout.DEFAULT_SIZE, 506, Short.MAX_VALUE)
        );
        jDesktop.setLayer(jPanelContent, javax.swing.JLayeredPane.DEFAULT_LAYER);

        getContentPane().add(jDesktop);

        jMenu1.setText("File");

        jMenuItem1.setText("New Project...");
        jMenu1.add(jMenuItem1);

        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/icons/iconNew.png"))); // NOI18N
        jMenuItem2.setText("New File...");
        jMenu1.add(jMenuItem2);
        jMenu1.add(jSeparator1);

        jMenuBar2.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar2.add(jMenu2);

        setJMenuBar(jMenuBar2);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        ThemeManager.setTheme(ThemeManager.DARK_THEME);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            UIManager.put("Menu.arrowIcon", new ImageIcon("res/gui/icons/iconArrowRight.png"));
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(J3DBuild.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        Preloader preloader = new Preloader();
        preloader.setLocationRelativeTo(null);
        preloader.setVisible(true);

        java.awt.EventQueue.invokeLater(() -> {
            instance = new J3DBuild();
            instance.setLocationRelativeTo(null);
            addContent("Scene Editor", new JPanelSceneEditor(null));
            addContent("Appearance Editor", new JPanelAppearanceEditor(new File("res/demo/appearance/NewAppearance.java"), "demo.appearance.NewAppearance"));
            preloader.setVisible(false);
            instance.setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JDesktopPane jDesktop;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanelContent;
    private javax.swing.JPanel jPanelFolder;
    private javax.swing.JPanel jPanelProperties;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPaneProperties;
    private javax.swing.JScrollPane jScrollTree;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JTabbedPane jTabbedContent;
    private javax.swing.JTabbedPane jTabbedExplorer;
    private javax.swing.JTabbedPane jTabbedGraph;
    private javax.swing.JTabbedPane jTabbedProperties;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JToolBar jToolBarExplorer;
    private javax.swing.JTree jTreeResources;
    private javax.swing.JTree jTreeSceneGraph;
    // End of variables declaration//GEN-END:variables

    @Override
    public void actionPerformed(ActionEvent ae) {
        Component comp = jTabbedContent.getSelectedComponent();
        if (comp != null) {
            comp.repaint();
        }
    }
}
