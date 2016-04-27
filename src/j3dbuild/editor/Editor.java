package j3dbuild.editor;

import j3dbuild.editor.manager.ThemeManager;
import j3dbuild.editor.ui.ProjectGraphRenderer;
import j3dbuild.editor.ui.SceneEditor;
import j3dbuild.editor.ui.acomponents.*;
import j3dbuild.project.Project;
import j3dbuild.utils.FileUtils;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.HashMap;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Editor extends JFrame implements ActionListener {
    
    public static Project project = null;
    public static Editor instance = null;
    public static File workspace = new File("\\\\hebe-b\\hem77b\\alemen778\\Mina dokument\\NetBeansProjects");

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
    
    public static void setProject(Project project) {
        Editor.project = project;
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
        jPanelToolbar = new APanel();
        jTabbedContent = new ATabbedPane();
        jToolBar1 = new AToolBar();
        jButton1 = new AButton();
        jButton2 = new AButton();
        jButton3 = new AButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        jButton4 = new AButton();
        jSplitPane1 = new ASplitPane();
        jTabbedBottom = new ATabbedPane();
        jPanel1 = new APanel();
        jSplitPane3 = new ASplitPane();
        jTabbedProject = new ATabbedPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTree1 = new ATree();
        jLabel2 = new javax.swing.JLabel();
        jMenuBar2 = new AMenuBar();
        jMenu1 = new AMenu();
        jMenuItem1 = new AMenuItem();
        jMenuItem2 = new AMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItem3 = new AMenuItem();
        jMenu2 = new AMenu();

        jTextField4.setText("jTextField4");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("J3D Build");
        setLocationByPlatform(true);
        setPreferredSize(new java.awt.Dimension(1280, 720));

        jPanelContent.setBackground(ThemeManager.COLOR_BACKGROUND);
        jPanelContent.setLayout(new java.awt.BorderLayout(4, 4));

        jPanelToolbar.setBackground(ThemeManager.COLOR_BACKGROUND);
        jPanelToolbar.setMaximumSize(new java.awt.Dimension(32767, 58));
        jPanelToolbar.setMinimumSize(new java.awt.Dimension(0, 58));
        jPanelToolbar.setPreferredSize(new java.awt.Dimension(966, 58));

        jTabbedContent.setMaximumSize(new java.awt.Dimension(32767, 30));
        jTabbedContent.setMinimumSize(new java.awt.Dimension(105, 30));
        jTabbedContent.setPreferredSize(new java.awt.Dimension(5, 30));

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
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton1);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/icons/iconLoad.png"))); // NOI18N
        jButton2.setToolTipText("Open Project...");
        jButton2.setFocusable(false);
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton2);

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/icons/iconSave.png"))); // NOI18N
        jButton3.setToolTipText("Save Project");
        jButton3.setFocusable(false);
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jButton3);
        jToolBar1.add(jSeparator2);

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/icons/iconPlay.png"))); // NOI18N
        jButton4.setToolTipText("Save Project");
        jButton4.setFocusable(false);
        jButton4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jButton4);

        javax.swing.GroupLayout jPanelToolbarLayout = new javax.swing.GroupLayout(jPanelToolbar);
        jPanelToolbar.setLayout(jPanelToolbarLayout);
        jPanelToolbarLayout.setHorizontalGroup(
            jPanelToolbarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 966, Short.MAX_VALUE)
            .addComponent(jTabbedContent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanelToolbarLayout.setVerticalGroup(
            jPanelToolbarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelToolbarLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jTabbedContent, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanelContent.add(jPanelToolbar, java.awt.BorderLayout.PAGE_START);

        jSplitPane1.setDividerLocation(400);
        jSplitPane1.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        jSplitPane1.setResizeWeight(1.0);

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

        jSplitPane3.setDividerLocation(300);

        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("Projects");
        javax.swing.tree.DefaultMutableTreeNode treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Test Project");
        treeNode1.add(treeNode2);
        jTree1.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        jTree1.setCellRenderer(new ProjectGraphRenderer());
        jScrollPane1.setViewportView(jTree1);

        jTabbedProject.addTab("Projects", jScrollPane1);

        jSplitPane3.setTopComponent(jTabbedProject);

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("There are no files open!");
        jSplitPane3.setRightComponent(jLabel2);

        jSplitPane1.setTopComponent(jSplitPane3);

        jPanelContent.add(jSplitPane1, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanelContent, java.awt.BorderLayout.CENTER);

        jMenu1.setText("File");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setText("New Project...");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/icons/iconNew.png"))); // NOI18N
        jMenuItem2.setText("New File...");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);
        jMenu1.add(jSeparator1);

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/icons/iconLoad.png"))); // NOI18N
        jMenuItem3.setText("Open Project...");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuBar2.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar2.add(jMenu2);

        setJMenuBar(jMenuBar2);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        newProject();
        updateTitle(project);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        newFile();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        Project result = loadProject();
        project = result == null ? project : result;
        updateTitle(project);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        newFile();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Project result = loadProject();
        project = result == null ? project : result;
        updateTitle(project);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void updateTitle(Project project) {
        if (project == null) {
            setTitle("J3D Build");
        } else {
            setTitle("J3D Build - " + project.title);
        }
    }
    
    public void newProject() {
        ProjectSetup setup = new ProjectSetup();
        setup.setVisible(true);
    }
    
    public void newFile() {
        FileSetup setup = new FileSetup();
        setup.setVisible(true);
    }
    
    public Project loadProject() {
        JFileChooser chooser = new JFileChooser(workspace);
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.setFileFilter(new FileNameExtensionFilter("J3DBuild Project", "j3dbp"));
        chooser.setDialogTitle("Load Project");
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            if (FileUtils.getFileExtension(chooser.getSelectedFile()).equals("j3dbp")) {
                return Project.load(chooser.getSelectedFile());
            }
        }
        
        return null;
    }
    
    public static void main(String args[]) {
        ThemeManager.setTheme(ThemeManager.CLASSIC_THEME);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            UIManager.getLookAndFeelDefaults().put("MenuItem.acceleratorForeground", ThemeManager.COLOR_FOREGROUND);
            UIManager.getLookAndFeelDefaults().put("MenuItem.acceleratorSelectionForeground", ThemeManager.COLOR_FOREGROUND);
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
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelContent;
    private javax.swing.JPanel jPanelToolbar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JSplitPane jSplitPane3;
    private javax.swing.JTabbedPane jTabbedBottom;
    private javax.swing.JTabbedPane jTabbedContent;
    private javax.swing.JTabbedPane jTabbedProject;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JTree jTree1;
    // End of variables declaration//GEN-END:variables

    @Override
    public void actionPerformed(ActionEvent ae) {
        Component comp = jTabbedContent.getSelectedComponent();
        if (comp != null) {
            comp.repaint();
        }
    }
}
