package j3dbuild.editor.properties;

import j3dbuild.editor.ui.acomponents.ALabel;
import j3dbuild.editor.ui.acomponents.APanel;
import j3dbuild.editor.ui.acomponents.ATextField;
import j3dbuild.utils.Math3D;
import javax.media.j3d.Transform3D;
import javax.swing.ImageIcon;
import javax.vecmath.Matrix3d;
import javax.vecmath.Vector3d;

public class TransformProperty extends PropertyType<Transform3D> {
    
    private static final ImageIcon ICON_TRANFORM = new ImageIcon("res/gui/icons/iconTransform.png");
    
    private final Vector3d position;
    private final Vector3d rotation;
    private final Vector3d scale;
    private final Transform3D transform;

    public TransformProperty(Vector3d position, Vector3d rotation, Vector3d scale) {
        this.position = position;
        this.rotation = rotation;
        this.scale = scale;
        this.transform = buildTransform3D(position, rotation, scale);
        setName("Transform 3D");
        setIcon(ICON_TRANFORM);
        initComponents();
    }
    
    public TransformProperty(Transform3D transform) {
        this.position = getTranslationAxis(transform);
        this.rotation = getRotationAxis(transform);
        this.scale = getScaleAxis(transform);
        this.transform = transform;
        setName("Transform 3D");
        setIcon(ICON_TRANFORM);
        initComponents();
    }
    
    public TransformProperty() {
        this.transform = new Transform3D();
        this.position = new Vector3d();
        this.rotation = new Vector3d();
        this.scale = new Vector3d(1, 1, 1);
        setName("Transform 3D");
        setIcon(ICON_TRANFORM);
        initComponents();
    }
    
    private static Vector3d getTranslationAxis(Transform3D transform) {
        Vector3d result = new Vector3d();
        transform.get(result);
        
        return result;
    }
    
    private static Vector3d getRotationAxis(Transform3D transform) {
        Vector3d result = new Vector3d();
        Matrix3d matrix = new Matrix3d();
        transform.get(matrix);
        Math3D.matrix3dToEuler(matrix, result);
        
        return result;
    }
    
    private static Vector3d getScaleAxis(Transform3D transform) {
        Vector3d result = new Vector3d();
        transform.getScale(result);
        
        return result;
    }
    
    private static Transform3D buildTransform3D(Vector3d pos, Vector3d rot, Vector3d scl) {
        Transform3D result = new Transform3D();
        
        Transform3D trRotX = new Transform3D();
        Transform3D trRotY = new Transform3D();
        Transform3D trRotZ = new Transform3D();
        
        trRotX.rotX(rot.x);
        trRotX.rotY(rot.y);
        trRotX.rotZ(rot.z);
        
        result.mul(trRotX);
        result.mul(trRotY);
        result.mul(trRotZ);
        
        result.setTranslation(pos);
        result.setScale(scl);
        
        return result;
    }
    
    private static double convertToDouble(String value) {
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException ex) {
            return 0;
        }
    }
    
    private void setRotation(Vector3d rotation) {
        Transform3D trRotX = new Transform3D();
        Transform3D trRotY = new Transform3D();
        Transform3D trRotZ = new Transform3D();
        trRotX.rotX(Math.toRadians(rotation.x));
        trRotY.rotY(Math.toRadians(rotation.y));
        trRotZ.rotZ(Math.toRadians(rotation.z));
        
        trRotY.mul(trRotZ);
        trRotX.mul(trRotY);
        
        transform.set(trRotX);
        transform.setTranslation(position);
        transform.setScale(scale);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new ALabel();
        jPanel1 = new APanel();
        jLabel4 = new javax.swing.JLabel();
        jFieldPosX = new ATextField();
        jLabel5 = new javax.swing.JLabel();
        jFieldPosY = new ATextField();
        jLabel6 = new javax.swing.JLabel();
        jFieldPosZ = new ATextField();
        jLabel2 = new ALabel();
        jPanel4 = new APanel();
        jLabel13 = new javax.swing.JLabel();
        jFieldRotX = new ATextField();
        jLabel14 = new javax.swing.JLabel();
        jFieldRotY = new ATextField();
        jLabel15 = new javax.swing.JLabel();
        jFieldRotZ = new ATextField();
        jLabel3 = new ALabel();
        jPanel8 = new APanel();
        jLabel25 = new javax.swing.JLabel();
        jFieldSclX = new ATextField();
        jLabel26 = new javax.swing.JLabel();
        jFieldSclY = new ATextField();
        jLabel27 = new javax.swing.JLabel();
        jFieldSclZ = new ATextField();

        jLabel1.setText("Translation");

        jLabel4.setBackground(new java.awt.Color(153, 0, 0));
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("  X  ");
        jLabel4.setOpaque(true);
        jLabel4.setPreferredSize(new java.awt.Dimension(18, 20));

        jFieldPosX.setText("0.0");
        jFieldPosX.setPreferredSize(new java.awt.Dimension(60, 20));
        jFieldPosX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFieldPosXActionPerformed(evt);
            }
        });

        jLabel5.setBackground(new java.awt.Color(0, 102, 0));
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("  Y  ");
        jLabel5.setOpaque(true);
        jLabel5.setPreferredSize(new java.awt.Dimension(18, 20));

        jFieldPosY.setText("0.0");
        jFieldPosY.setPreferredSize(new java.awt.Dimension(60, 20));
        jFieldPosY.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFieldPosYActionPerformed(evt);
            }
        });

        jLabel6.setBackground(new java.awt.Color(0, 102, 153));
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("  Z  ");
        jLabel6.setOpaque(true);
        jLabel6.setPreferredSize(new java.awt.Dimension(18, 20));

        jFieldPosZ.setText("0.0");
        jFieldPosZ.setPreferredSize(new java.awt.Dimension(60, 20));
        jFieldPosZ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFieldPosZActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jFieldPosX, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(8, 8, 8)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jFieldPosY, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(8, 8, 8)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jFieldPosZ, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jFieldPosX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jFieldPosZ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jFieldPosY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel2.setText("Rotation");

        jLabel13.setBackground(new java.awt.Color(153, 0, 0));
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("  X  ");
        jLabel13.setOpaque(true);
        jLabel13.setPreferredSize(new java.awt.Dimension(18, 20));

        jFieldRotX.setText("0.0");
        jFieldRotX.setPreferredSize(new java.awt.Dimension(60, 20));
        jFieldRotX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFieldRotXActionPerformed(evt);
            }
        });

        jLabel14.setBackground(new java.awt.Color(0, 102, 0));
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("  Y  ");
        jLabel14.setOpaque(true);
        jLabel14.setPreferredSize(new java.awt.Dimension(18, 20));

        jFieldRotY.setText("0.0");
        jFieldRotY.setPreferredSize(new java.awt.Dimension(60, 20));
        jFieldRotY.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFieldRotYActionPerformed(evt);
            }
        });

        jLabel15.setBackground(new java.awt.Color(0, 102, 153));
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("  Z  ");
        jLabel15.setOpaque(true);
        jLabel15.setPreferredSize(new java.awt.Dimension(18, 20));

        jFieldRotZ.setText("0.0");
        jFieldRotZ.setPreferredSize(new java.awt.Dimension(60, 20));
        jFieldRotZ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFieldRotZActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jFieldRotX, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
                .addGap(8, 8, 8)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jFieldRotY, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
                .addGap(8, 8, 8)
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jFieldRotZ, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jFieldRotX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jFieldRotZ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jFieldRotY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel3.setText("Scale");

        jLabel25.setBackground(new java.awt.Color(153, 0, 0));
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("  X  ");
        jLabel25.setOpaque(true);
        jLabel25.setPreferredSize(new java.awt.Dimension(18, 20));

        jFieldSclX.setText("1.0");
        jFieldSclX.setPreferredSize(new java.awt.Dimension(60, 20));
        jFieldSclX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFieldSclXActionPerformed(evt);
            }
        });

        jLabel26.setBackground(new java.awt.Color(0, 102, 0));
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("  Y  ");
        jLabel26.setOpaque(true);
        jLabel26.setPreferredSize(new java.awt.Dimension(18, 20));

        jFieldSclY.setText("1.0");
        jFieldSclY.setPreferredSize(new java.awt.Dimension(60, 20));
        jFieldSclY.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFieldSclYActionPerformed(evt);
            }
        });

        jLabel27.setBackground(new java.awt.Color(0, 102, 153));
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setText("  Z  ");
        jLabel27.setOpaque(true);
        jLabel27.setPreferredSize(new java.awt.Dimension(18, 20));

        jFieldSclZ.setText("1.0");
        jFieldSclZ.setPreferredSize(new java.awt.Dimension(60, 20));
        jFieldSclZ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFieldSclZActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jFieldSclX, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
                .addGap(8, 8, 8)
                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jFieldSclY, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
                .addGap(8, 8, 8)
                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jFieldSclZ, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jFieldSclX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jFieldSclZ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jFieldSclY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel3))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(4, 4, 4))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jFieldPosXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFieldPosXActionPerformed
        Transform3D oldTransform = new Transform3D(transform);
        double value = convertToDouble(jFieldPosX.getText());
        jFieldPosX.setText(value + "");
        
        position.x = value;
        transform.setTranslation(position);
        stateChanged(oldTransform, transform);
    }//GEN-LAST:event_jFieldPosXActionPerformed

    private void jFieldPosYActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFieldPosYActionPerformed
        Transform3D oldTransform = new Transform3D(transform);
        double value = convertToDouble(jFieldPosY.getText());
        jFieldPosY.setText(value + "");
        
        position.y = value;
        transform.setTranslation(position);
        stateChanged(oldTransform, transform);
    }//GEN-LAST:event_jFieldPosYActionPerformed

    private void jFieldPosZActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFieldPosZActionPerformed
        Transform3D oldTransform = new Transform3D(transform);
        double value = convertToDouble(jFieldPosZ.getText());
        jFieldPosZ.setText(value + "");
        
        position.z = value;
        transform.setTranslation(position);
        stateChanged(oldTransform, transform);
    }//GEN-LAST:event_jFieldPosZActionPerformed

    private void jFieldRotXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFieldRotXActionPerformed
        Transform3D oldTransform = new Transform3D(transform);
        double value = convertToDouble(jFieldRotX.getText());
        jFieldRotX.setText(value + "");
        
        rotation.x = value;
        setRotation(rotation);
        stateChanged(oldTransform, transform);
    }//GEN-LAST:event_jFieldRotXActionPerformed

    private void jFieldRotYActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFieldRotYActionPerformed
        Transform3D oldTransform = new Transform3D(transform);
        double value = convertToDouble(jFieldRotY.getText());
        jFieldRotY.setText(value + "");
        
        rotation.y = value;
        setRotation(rotation);
        stateChanged(oldTransform, transform);
    }//GEN-LAST:event_jFieldRotYActionPerformed

    private void jFieldRotZActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFieldRotZActionPerformed
        Transform3D oldTransform = new Transform3D(transform);
        double value = convertToDouble(jFieldRotZ.getText());
        jFieldRotZ.setText(value + "");
        
        rotation.z = value;
        setRotation(rotation);
        stateChanged(oldTransform, transform);
    }//GEN-LAST:event_jFieldRotZActionPerformed

    private void jFieldSclXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFieldSclXActionPerformed
        Transform3D oldTransform = new Transform3D(transform);
        double value = convertToDouble(jFieldSclX.getText());
        jFieldSclX.setText(value + "");
        
        scale.x = value;
        transform.setScale(scale);
        stateChanged(oldTransform, transform);
    }//GEN-LAST:event_jFieldSclXActionPerformed

    private void jFieldSclYActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFieldSclYActionPerformed
        Transform3D oldTransform = new Transform3D(transform);
        double value = convertToDouble(jFieldSclY.getText());
        jFieldSclY.setText(value + "");
        
        scale.y = value;
        transform.setScale(scale);
        stateChanged(oldTransform, transform);
    }//GEN-LAST:event_jFieldSclYActionPerformed

    private void jFieldSclZActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFieldSclZActionPerformed
        Transform3D oldTransform = new Transform3D(transform);
        double value = convertToDouble(jFieldSclZ.getText());
        jFieldSclZ.setText(value + "");
        
        scale.z = value;
        transform.setScale(scale);
        stateChanged(oldTransform, transform);
    }//GEN-LAST:event_jFieldSclZActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField jFieldPosX;
    private javax.swing.JTextField jFieldPosY;
    private javax.swing.JTextField jFieldPosZ;
    private javax.swing.JTextField jFieldRotX;
    private javax.swing.JTextField jFieldRotY;
    private javax.swing.JTextField jFieldRotZ;
    private javax.swing.JTextField jFieldSclX;
    private javax.swing.JTextField jFieldSclY;
    private javax.swing.JTextField jFieldSclZ;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel8;
    // End of variables declaration//GEN-END:variables

    @Override
    public void setValue(Transform3D value) {
        transform.set(value);
    }
    
    @Override
    public Transform3D getValue() {
        return transform;
    }
}
