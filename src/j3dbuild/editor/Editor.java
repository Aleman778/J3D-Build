package j3dbuild.editor;

import j3dbuild.editor.manager.ThemeManager;
import j3dbuild.editor.ui.SceneEditor;
import j3dbuild.editor.ui.acomponents.*;
import j3dbuild.project.Project;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.Timer;
import javax.swing.UIManager;

public class Editor extends JFrame implements ActionListener {
    
    public static Project project = null;
    public static Editor instance = null;

    private static HashMap<String, Integer> indexes;
    
    private boolean running;
    private final Timer timer;
    private SceneEditor scene;
    
    //Singleton class
    private Editor() {
        setLocationByPlatform(true);
        initComponents();
        
        
        timer = new Timer(100, this);
        timer.start();
    }
    
    public void addComponent(String title, Component component, JTabbedPane destination) {
        int index = jTabbedContent.getTabCount();
        indexes.put(title, index);
        jTabbedContent.addTab(title, component);
        jTabbedContent.setTabComponentAt(index, new TabPanel(title));
    }
    
    public void removeComponent(String title, JTabbedPane destination) {
        Integer index = indexes.get(title);
        if (index != null) {
            destination.removeTabAt(index);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField4 = new javax.swing.JTextField();
        jPanelContent = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jToolBar1 = new AToolBar();
        jButton1 = new AButton();
        jButton2 = new AButton();
        jButton3 = new AButton();
        jButton4 = new AButton();
        jSplitPane1 = new ASplitPane();
        jTabbedProject = new ATabbedPane();
        jSplitPane3 = new ASplitPane();
        jSplitPane4 = new ASplitPane();
        jTabbedContent = new ATabbedPane();
        jTabbedRight = new ATabbedPane();
        jTabbedLeft = new ATabbedPane();
        jTabbedBottom = new ATabbedPane();
        jPanel1 = new APanel();
        jMenuBar2 = new AMenuBar();
        jMenu1 = new AMenu();
        jMenuItem1 = new AMenuItem();
        jMenuItem2 = new AMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenu2 = new AMenu();

        jTextField4.setText("jTextField4");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("J3D Build");
        setLocationByPlatform(true);
        setPreferredSize(new java.awt.Dimension(1280, 720));

        jPanelContent.setBackground(ThemeManager.COLOR_BACKGROUND);
        jPanelContent.setLayout(new java.awt.BorderLayout(4, 4));

        jPanel2.setBackground(ThemeManager.COLOR_BACKGROUND);
        jPanel2.setPreferredSize(new java.awt.Dimension(1070, 32));

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);
        jToolBar1.setMaximumSize(new java.awt.Dimension(13, 28));
        jToolBar1.setMinimumSize(new java.awt.Dimension(13, 28));
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
            .addComponent(jToolBar1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 949, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 4, Short.MAX_VALUE))
        );

        jPanelContent.add(jPanel2, java.awt.BorderLayout.NORTH);

        jSplitPane1.setDividerLocation(400);
        jSplitPane1.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        jSplitPane1.setResizeWeight(1.0);

        jSplitPane3.setDividerLocation(300);

        jSplitPane4.setDividerLocation(getWidth() - 600);
        jSplitPane4.setResizeWeight(1.0);

        jTabbedContent.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        jSplitPane4.setLeftComponent(jTabbedContent);

        jTabbedRight.setPreferredSize(new java.awt.Dimension(300, 2));
        jSplitPane4.setRightComponent(jTabbedRight);

        jSplitPane3.setRightComponent(jSplitPane4);

        jTabbedLeft.setPreferredSize(new java.awt.Dimension(256, 350));
        jSplitPane3.setLeftComponent(jTabbedLeft);

        jTabbedProject.addTab("tab2", jSplitPane3);

        jSplitPane1.setTopComponent(jTabbedProject);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jTabbedBottom.addTab("Console", jPanel1);

        jSplitPane1.setBottomComponent(jTabbedBottom);

        jPanelContent.add(jSplitPane1, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanelContent, java.awt.BorderLayout.CENTER);

        jMenu1.setText("File");

        jMenuItem1.setText("New Project...");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
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

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        ProjectSetup setup = new ProjectSetup();
        setup.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    public static void main(String args[]) {
        ThemeManager.setTheme(ThemeManager.DARK_THEME);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            //UIManager.put("Menu.arrowIcon", new ImageIcon("res/gui/icons/iconArrowRight.png"));
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Editor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        Preloader preloader = new Preloader();
        preloader.setLocationRelativeTo(null);
        preloader.setVisible(true);

        java.awt.EventQueue.invokeLater(() -> {
            instance = new Editor();
            instance.setLocationRelativeTo(null);
            
            //Init stuff
            
            preloader.setVisible(false);
            instance.setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanelContent;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JSplitPane jSplitPane3;
    private javax.swing.JSplitPane jSplitPane4;
    private javax.swing.JTabbedPane jTabbedBottom;
    private javax.swing.JTabbedPane jTabbedContent;
    private javax.swing.JTabbedPane jTabbedLeft;
    private javax.swing.JTabbedPane jTabbedProject;
    private javax.swing.JTabbedPane jTabbedRight;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JToolBar jToolBar1;
    // End of variables declaration//GEN-END:variables

    @Override
    public void actionPerformed(ActionEvent ae) {
        Component comp = jTabbedContent.getSelectedComponent();
        if (comp != null) {
            comp.repaint();
        }
    }
}
