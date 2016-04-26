package j3dbuild.editor;

import j3dbuild.editor.manager.ThemeManager;
import j3dbuild.editor.ui.acomponents.*;
import java.io.File;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.border.LineBorder;

public class FileSetup extends javax.swing.JDialog {

    private String extension;
    
    public FileSetup() {
        extension = "j3ds";
        initComponents();
        
        setModalityType(DEFAULT_MODALITY_TYPE);
        if (Editor.project != null) {
            jTextFieldLocation.setText(Editor.project.folderResources.getPath());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new APanel();
        jPanel2 = new javax.swing.JPanel();
        jButtonCancel = new AButton();
        jButtonCreate = new AButton();
        jButtonBrowse = new AButton();
        jTextFieldTitle = new ATextField();
        jLabel1 = new ALabel();
        jLabel2 = new ALabel();
        jTextFieldLocation = new ATextField();
        jSplitPane1 = new ASplitPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTree1 = new ATree();
        jSplitPane2 = new ASplitPane();
        jPanel3 = new APanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextPane2 = new ATextPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("New Item");
        setLocationByPlatform(true);
        setPreferredSize(new java.awt.Dimension(720, 436));
        setType(java.awt.Window.Type.UTILITY);

        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel2.setBackground(ThemeManager.COLOR_BACKGROUND);

        jButtonCancel.setText("Cancel");
        jButtonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelActionPerformed(evt);
            }
        });

        jButtonCreate.setText("OK");
        jButtonCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCreateActionPerformed(evt);
            }
        });

        jButtonBrowse.setText("Browse...");
        jButtonBrowse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBrowseActionPerformed(evt);
            }
        });

        jTextFieldTitle.setText("New Item");
        jTextFieldTitle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldTitleActionPerformed(evt);
            }
        });
        jTextFieldTitle.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jTextFieldTitlePropertyChange(evt);
            }
        });

        jLabel1.setText("Project Name:");

        jLabel2.setText("Project Location:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addGap(40, 40, 40)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldLocation)
                    .addComponent(jTextFieldTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 485, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonBrowse)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldTitle, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextFieldLocation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonBrowse))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonCancel)
                    .addComponent(jButtonCreate))
                .addContainerGap())
        );

        jPanel1.add(jPanel2, java.awt.BorderLayout.SOUTH);

        jSplitPane1.setDividerLocation(200);

        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("Items");
        javax.swing.tree.DefaultMutableTreeNode treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Scene");
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Scripting");
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Resources");
        treeNode1.add(treeNode2);
        jTree1.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        jScrollPane1.setViewportView(jTree1);

        jSplitPane1.setLeftComponent(jScrollPane1);

        jSplitPane2.setDividerLocation(300);
        jSplitPane2.setResizeWeight(1.0);

        jPanel3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 0, 0));
        jSplitPane2.setLeftComponent(jPanel3);

        jScrollPane3.setViewportView(jTextPane2);

        jSplitPane2.setRightComponent(jScrollPane3);

        jSplitPane1.setRightComponent(jSplitPane2);

        jPanel1.add(jSplitPane1, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelActionPerformed
        dispose();
    }//GEN-LAST:event_jButtonCancelActionPerformed

    private void jButtonCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCreateActionPerformed
        if (isFile()) {
            createFile();
        }
    }//GEN-LAST:event_jButtonCreateActionPerformed

    private void jButtonBrowseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBrowseActionPerformed
        browse();
    }//GEN-LAST:event_jButtonBrowseActionPerformed

    private void jTextFieldTitleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldTitleActionPerformed
        if (isFile()) {
            createFile();
        }
    }//GEN-LAST:event_jTextFieldTitleActionPerformed

    private void jTextFieldTitlePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jTextFieldTitlePropertyChange
        try {
            String path = jTextFieldLocation.getText().substring(0,
                    jTextFieldLocation.getText().lastIndexOf(jTextFieldTitle.getText()));
            jTextFieldLocation.setText(path + jTextFieldTitle.getText());
        } catch (Exception e) {}
    }//GEN-LAST:event_jTextFieldTitlePropertyChange

    public void browse() {
        String location = jTextFieldLocation.getName();
        
        JFileChooser browser = new JFileChooser(location);
        browser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        browser.setDialogTitle("Browse");
        if (browser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            jTextFieldLocation.setText(browser.getSelectedFile().getPath());
            jTextFieldLocation.setBorder(new LineBorder(ThemeManager.COLOR_ITEM));
        }
    }
    
    public void createFile() {
        String location = jTextFieldLocation.getText();
        String filename = jTextFieldTitle.getText() + "." + extension;
        
        File target = new File(location, filename);
        if (target.exists()) {
            AOptionPane.showErrorMessage("Error", "File already exists!");
            return;
        }
        
        try {
            target.createNewFile();
            dispose();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public boolean isFile() {
        String title = jTextFieldTitle.getText();
        File location = new File(jTextFieldLocation.getText());
        
        if (title.isEmpty()) {
            jTextFieldTitle.setBorder(new LineBorder(ThemeManager.COLOR_ERROR));
            return false;
        }
        
        if (!location.exists() || !location.isDirectory()) {
            jTextFieldLocation.setBorder(new LineBorder(ThemeManager.COLOR_ERROR));
            return false;
        }
        
        return true;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonBrowse;
    private javax.swing.JButton jButtonCancel;
    private javax.swing.JButton jButtonCreate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JSplitPane jSplitPane2;
    private javax.swing.JTextField jTextFieldLocation;
    private javax.swing.JTextField jTextFieldTitle;
    private javax.swing.JTextPane jTextPane2;
    private javax.swing.JTree jTree1;
    // End of variables declaration//GEN-END:variables
}
