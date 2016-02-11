package build.editor;

import build.editor.manager.ThemeManager;
import build.editor.properties.TransformProperty;
import build.editor.ui.JPanelMaterialEditor;
import build.editor.ui.JPanelSceneEditor;
import build.editor.ui.JTreeSceneGraph;
import build.editor.ui.acomponents.*;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;

public class J3DBuild extends JFrame implements Runnable {

    public static J3DBuild instance = null;
    
    private boolean running;
    private JPanelSceneEditor scene;
    
    private J3DBuild() {
        ThemeManager.setTheme(ThemeManager.DARK_THEME);
        setLocationByPlatform(true);
        initComponents();
        jTabbedContent.addChangeListener((ChangeEvent ce) -> {
            Component selected = jTabbedContent.getSelectedComponent();
            
            if (selected instanceof JPanelSceneEditor) {
                jTreeSceneGraph.setModel(((JPanelSceneEditor) selected).getSceneGraph());
            }
        });
        jScrollPaneProperties.revalidate();
        
        JAccordion accodion = new JAccordion("Transform3D");
        accodion.setIcon(new ImageIcon("res/gui/icons/iconTransform.png"));
        jScrollPaneProperties.setViewportView(accodion);
        accodion.setMinimumSize(new Dimension(260, 0));
        accodion.setPreferredSize(new Dimension(260, accodion.getPreferredSize().height));
        TransformProperty property = new TransformProperty();
        accodion.setContent(property);
        
        addContent("Scene Editor", new JPanelSceneEditor(null));
        addContent("Material Editor", new JPanelMaterialEditor());
        new Thread(this, "J3D Build").start();
    }
    
    public void addContent(String title, JPanel panel) {
        jTabbedContent.addTab(title, panel);
        jTabbedContent.setTabComponentAt(jTabbedContent.getTabCount() - 1, new TabPanel(title));
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
        jTabbedPane1 = new ATabbedPane();
        jScrollTree = new AScrollPane();
        jTreeSceneGraph = JTreeSceneGraph.instance;
        jTabbedPane2 = new ATabbedPane();
        jScrollPaneProperties = new AScrollPane(false);
        jTabbedContent = new ATabbedPane();
        jTabbedPane3 = new ATabbedPane();
        jPanel8 = new javax.swing.JPanel();
        jTabbedExplorer = new ATabbedPane();
        jPanel1 = new APanel();
        jPanel4 = new APanel();
        jPanel6 = new APanel();
        jPanel7 = new APanel();
        jPanel10 = new APanel();
        jPanel11 = new APanel();
        jPanel12 = new APanel();
        jPanel2 = new javax.swing.JPanel();
        jToolBar1 = new AToolBar();
        jButton1 = new AButton();
        jButton2 = new AButton();
        jButton3 = new AButton();
        jButton4 = new AButton();
        jMenuBar2 = new AMenuBar();
        jMenu1 = new AMenu();
        jMenu2 = new AMenu();

        jTextField4.setText("jTextField4");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("J3D Build");
        setPreferredSize(new java.awt.Dimension(1280, 720));
        getContentPane().setLayout(new javax.swing.OverlayLayout(getContentPane()));

        jDesktop.setOpaque(false);

        jPanelContent.setBackground(ThemeManager.COLOR_BACKGROUND);
        jPanelContent.setLayout(new java.awt.BorderLayout(4, 4));

        jTabbedPane1.setPreferredSize(new java.awt.Dimension(256, 350));

        jScrollTree.setViewportView(jTreeSceneGraph);

        jTabbedPane1.addTab("Scene Graph", jScrollTree);

        jPanelContent.add(jTabbedPane1, java.awt.BorderLayout.WEST);

        jScrollPaneProperties.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPaneProperties.setPreferredSize(new java.awt.Dimension(300, 2));
        jTabbedPane2.addTab("Properties", jScrollPaneProperties);

        jPanelContent.add(jTabbedPane2, java.awt.BorderLayout.EAST);

        jTabbedContent.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        jPanelContent.add(jTabbedContent, java.awt.BorderLayout.CENTER);

        jTabbedPane3.setPreferredSize(new java.awt.Dimension(1070, 260));

        jPanel8.setLayout(new java.awt.BorderLayout());

        jTabbedExplorer.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1060, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 204, Short.MAX_VALUE)
        );

        jTabbedExplorer.addTab("Scenes", jPanel1);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1060, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 204, Short.MAX_VALUE)
        );

        jTabbedExplorer.addTab("Prefabs", jPanel4);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1060, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 204, Short.MAX_VALUE)
        );

        jTabbedExplorer.addTab("Materials", jPanel6);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1060, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 204, Short.MAX_VALUE)
        );

        jTabbedExplorer.addTab("Textures", jPanel7);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1060, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 204, Short.MAX_VALUE)
        );

        jTabbedExplorer.addTab("Primitives", jPanel10);

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1060, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 204, Short.MAX_VALUE)
        );

        jTabbedExplorer.addTab("Lights", jPanel11);

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1060, Short.MAX_VALUE)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 204, Short.MAX_VALUE)
        );

        jTabbedExplorer.addTab("Behaviors", jPanel12);

        jPanel8.add(jTabbedExplorer, java.awt.BorderLayout.CENTER);

        jTabbedPane3.addTab("Explorer", jPanel8);

        jPanelContent.add(jTabbedPane3, java.awt.BorderLayout.SOUTH);

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
        jMenuBar2.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar2.add(jMenu2);

        setJMenuBar(jMenuBar2);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            UIManager.put("Menu.arrowIcon", new ImageIcon("res/gui/icons/iconArrowRight.png"));
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(J3DBuild.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> {
            instance = new J3DBuild();
            instance.setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JDesktopPane jDesktop;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanelContent;
    private javax.swing.JScrollPane jScrollPaneProperties;
    private javax.swing.JScrollPane jScrollTree;
    private javax.swing.JTabbedPane jTabbedContent;
    private javax.swing.JTabbedPane jTabbedExplorer;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JTree jTreeSceneGraph;
    // End of variables declaration//GEN-END:variables
    
    @Override
    public void run() {
        
        long lastTime = System.nanoTime();
        double delta = 0.0;
        double ns = 1000000000.0 / 30.0;
        int updates = 0;
        int frames = 0;
        long timer = System.currentTimeMillis();
        running = true;
        
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if (delta >= 1) {
                Component comp = jTabbedContent.getSelectedComponent();
                if (comp != null) {
                    comp.repaint();
                }
                frames++;
                delta--;
            }
            
            try {
                Thread.sleep(1);
            } catch (InterruptedException ex) {
            }
            
            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println("fps = " + frames);
                updates = 0;
                frames = 0;
            }
        }
    }
}
