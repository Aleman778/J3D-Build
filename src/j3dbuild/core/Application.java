package j3dbuild.core;

import j3dbuild.editor.ui.*;
import j3dbuild.editor.Editor;
import j3dbuild.editor.SceneEditor;
import j3dbuild.project.Item;
import j3dbuild.utils.ThemeUtils;
import j3dbuild.project.ProjectGraphRenderer;
import j3dbuild.project.Project;
import j3dbuild.project.ProjectGraph;
import j3dbuild.utils.FileUtils;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Application extends JFrame implements ActionListener {
    
    public static ProjectGraph projects = null;
    public static Application instance = null;
    public static File workspace = new File("C:\\Users\\alema\\Documents\\NetBeansProjects");
    
    private boolean running;
    private final Timer timer;
    
    //Singleton class
    private Application() {
        setLocationByPlatform(true);
        initComponents();
        
        projects = new ProjectGraph(jTreeProjects);
        jTreeProjects.setModel(projects);
        timer = new Timer(100, this);
        timer.start();
        
        addEditor(new SceneEditor(new Item("Scene Editor", new File("/res"))));
    }
    
    public void addEditor(Editor editor) {
        TabPanel tab = new TabPanel(editor.getItem().getTitle());
        jTabbedContent.addTab(editor.getItem().getTitle(), editor);
        jTabbedContent.setSelectedIndex(jTabbedContent.getTabCount() - 1);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelHeader = new javax.swing.JPanel();
        jPanelToolbar = new APanel();
        jToolBar1 = new AToolBar();
        jButtonUndo = new AButton();
        jButtonRedo = new AButton();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        jButtonNewFile = new AButton();
        jButtonNewProject = new AButton();
        jButtonLoad = new AButton();
        jButtonSave = new AButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        jSplitPane1 = new ASplitPane();
        jTabbedBottom = new ATabbedPane();
        jPanel1 = new APanel();
        jSplitPane3 = new ASplitPane();
        jTabbedProject = new ATabbedPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTreeProjects = new ATree();
        jPanelContent = new APanel();
        jTabbedContent = new ATabbedPane();
        jMenuBar2 = new AMenuBar();
        jMenu1 = new AMenu();
        jMenuItemNewProject = new AMenuItem();
        jMenuItemNewFile = new AMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItemOpenProject = new AMenuItem();
        jMenu2 = new AMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("J3D Build");
        setLocationByPlatform(true);
        setPreferredSize(new java.awt.Dimension(1280, 720));

        jPanelHeader.setBackground(j3dbuild.utils.ThemeUtils.COLOR_BACKGROUND);
        jPanelHeader.setLayout(new java.awt.BorderLayout(4, 4));

        jPanelToolbar.setBackground(j3dbuild.utils.ThemeUtils.COLOR_BACKGROUND);
        jPanelToolbar.setMaximumSize(new java.awt.Dimension(32767, 58));
        jPanelToolbar.setMinimumSize(new java.awt.Dimension(0, 58));
        jPanelToolbar.setPreferredSize(new java.awt.Dimension(966, 26));
        jPanelToolbar.setRequestFocusEnabled(false);

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);
        jToolBar1.setMaximumSize(new java.awt.Dimension(13, 32));
        jToolBar1.setMinimumSize(new java.awt.Dimension(13, 32));
        jToolBar1.setPreferredSize(new java.awt.Dimension(2, 32));

        jButtonUndo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/icons/iconUndo.png"))); // NOI18N
        jButtonUndo.setToolTipText("Undo (Ctrl + Z)");
        jButtonUndo.setFocusable(false);
        jButtonUndo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonUndo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonUndo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUndoActionPerformed(evt);
            }
        });
        jToolBar1.add(jButtonUndo);

        jButtonRedo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/icons/iconRedo.png"))); // NOI18N
        jButtonRedo.setToolTipText("Redo (Ctrl + Y)");
        jButtonRedo.setFocusable(false);
        jButtonRedo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonRedo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonRedo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRedoActionPerformed(evt);
            }
        });
        jToolBar1.add(jButtonRedo);
        jToolBar1.add(jSeparator3);

        jButtonNewFile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/icons/iconNew.png"))); // NOI18N
        jButtonNewFile.setToolTipText("New File... (Ctrl + N)");
        jButtonNewFile.setFocusable(false);
        jButtonNewFile.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonNewFile.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonNewFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNewFileActionPerformed(evt);
            }
        });
        jToolBar1.add(jButtonNewFile);

        jButtonNewProject.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/icons/iconNewProject.png"))); // NOI18N
        jButtonNewProject.setToolTipText("New Project... (Ctrl + Shift + N)");
        jButtonNewProject.setFocusable(false);
        jButtonNewProject.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonNewProject.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonNewProject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNewProjectActionPerformed(evt);
            }
        });
        jToolBar1.add(jButtonNewProject);

        jButtonLoad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/icons/iconLoad.png"))); // NOI18N
        jButtonLoad.setToolTipText("Open Project... (Ctrl + Shift + O)");
        jButtonLoad.setFocusable(false);
        jButtonLoad.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonLoad.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonLoad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLoadActionPerformed(evt);
            }
        });
        jToolBar1.add(jButtonLoad);

        jButtonSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/icons/iconSaveAll.png"))); // NOI18N
        jButtonSave.setToolTipText("Save All (Ctrl + Shift + S)");
        jButtonSave.setFocusable(false);
        jButtonSave.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonSave.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jButtonSave);
        jToolBar1.add(jSeparator2);

        javax.swing.GroupLayout jPanelToolbarLayout = new javax.swing.GroupLayout(jPanelToolbar);
        jPanelToolbar.setLayout(jPanelToolbarLayout);
        jPanelToolbarLayout.setHorizontalGroup(
            jPanelToolbarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 975, Short.MAX_VALUE)
        );
        jPanelToolbarLayout.setVerticalGroup(
            jPanelToolbarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelToolbarLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanelHeader.add(jPanelToolbar, java.awt.BorderLayout.PAGE_START);

        jSplitPane1.setDividerLocation(400);
        jSplitPane1.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        jSplitPane1.setResizeWeight(1.0);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 968, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jTabbedBottom.addTab("Console", jPanel1);

        jSplitPane1.setBottomComponent(jTabbedBottom);

        jSplitPane3.setDividerLocation(300);

        jScrollPane1.setBorder(null);

        jTreeProjects.setCellRenderer(new ProjectGraphRenderer());
        jTreeProjects.setRootVisible(false);
        jTreeProjects.setShowsRootHandles(true);
        jScrollPane1.setViewportView(jTreeProjects);

        jTabbedProject.addTab("Projects", jScrollPane1);

        jSplitPane3.setTopComponent(jTabbedProject);

        jPanelContent.setLayout(new java.awt.BorderLayout());

        jTabbedContent.setMaximumSize(new java.awt.Dimension(32767, 30));
        jTabbedContent.setMinimumSize(new java.awt.Dimension(105, 30));
        jPanelContent.add(jTabbedContent, java.awt.BorderLayout.CENTER);

        jSplitPane3.setRightComponent(jPanelContent);

        jSplitPane1.setTopComponent(jSplitPane3);

        jPanelHeader.add(jSplitPane1, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanelHeader, java.awt.BorderLayout.CENTER);

        jMenu1.setText("File");

        jMenuItemNewProject.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemNewProject.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/icons/iconNewProject.png"))); // NOI18N
        jMenuItemNewProject.setText("New Project...");
        jMenuItemNewProject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemNewProjectActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemNewProject);

        jMenuItemNewFile.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemNewFile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/icons/iconNew.png"))); // NOI18N
        jMenuItemNewFile.setText("New File...");
        jMenuItemNewFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemNewFileActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemNewFile);
        jMenu1.add(jSeparator1);

        jMenuItemOpenProject.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemOpenProject.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/icons/iconFolderOpen.png"))); // NOI18N
        jMenuItemOpenProject.setText("Open Project...");
        jMenuItemOpenProject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemOpenProjectActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemOpenProject);

        jMenuBar2.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar2.add(jMenu2);

        setJMenuBar(jMenuBar2);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItemNewProjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemNewProjectActionPerformed
        newProject();
    }//GEN-LAST:event_jMenuItemNewProjectActionPerformed

    private void jMenuItemNewFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemNewFileActionPerformed
        newFile();
    }//GEN-LAST:event_jMenuItemNewFileActionPerformed

    private void jMenuItemOpenProjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemOpenProjectActionPerformed
        Project project = loadProject();
        if (project != null) {
            project.open();
        }
    }//GEN-LAST:event_jMenuItemOpenProjectActionPerformed

    private void jButtonLoadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLoadActionPerformed
        Project project = loadProject();
        if (project != null) {
            project.open();
        }
    }//GEN-LAST:event_jButtonLoadActionPerformed

    private void jButtonNewFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNewFileActionPerformed
        newFile();
    }//GEN-LAST:event_jButtonNewFileActionPerformed

    private void jButtonUndoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUndoActionPerformed
        
    }//GEN-LAST:event_jButtonUndoActionPerformed

    private void jButtonRedoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRedoActionPerformed
        
    }//GEN-LAST:event_jButtonRedoActionPerformed

    private void jButtonNewProjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNewProjectActionPerformed
        newProject();
    }//GEN-LAST:event_jButtonNewProjectActionPerformed

    private void updateTitle(Project project) {
        if (project == null) {
            setTitle("J3D Build");
        } else {
            setTitle("J3D Build - " + project.getTitle());
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
        ThemeUtils.setTheme(ThemeUtils.CLASSIC_THEME);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            UIManager.getLookAndFeelDefaults().put("MenuItem.acceleratorForeground", ThemeUtils.COLOR_FOREGROUND);
            UIManager.getLookAndFeelDefaults().put("MenuItem.acceleratorSelectionForeground", ThemeUtils.COLOR_FOREGROUND);
            //UIManager.put("Menu.arrowIcon", new ImageIcon("res/gui/icons/iconArrowRight.png"));
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Application.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        Preloader preloader = new Preloader();
        preloader.setLocationRelativeTo(null);
        preloader.setVisible(true);

        java.awt.EventQueue.invokeLater(() -> {
            instance = new Application();
            instance.setLocationRelativeTo(null);
            
            //Init stuff
            
            preloader.setVisible(false);
            instance.setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonLoad;
    private javax.swing.JButton jButtonNewFile;
    private javax.swing.JButton jButtonNewProject;
    private javax.swing.JButton jButtonRedo;
    private javax.swing.JButton jButtonSave;
    private javax.swing.JButton jButtonUndo;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuItem jMenuItemNewFile;
    private javax.swing.JMenuItem jMenuItemNewProject;
    private javax.swing.JMenuItem jMenuItemOpenProject;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelContent;
    private javax.swing.JPanel jPanelHeader;
    private javax.swing.JPanel jPanelToolbar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JSplitPane jSplitPane3;
    private javax.swing.JTabbedPane jTabbedBottom;
    private javax.swing.JTabbedPane jTabbedContent;
    private javax.swing.JTabbedPane jTabbedProject;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JTree jTreeProjects;
    // End of variables declaration//GEN-END:variables

    @Override
    public void actionPerformed(ActionEvent ae) {
        int index = jTabbedContent.getSelectedIndex();
        if (index >= 0) {
            jPanelContent.repaint();
        }
    }
}
