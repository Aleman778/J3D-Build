package build.editor.ui;

import build.editor.J3DBuild;
import build.editor.manager.ThemeManager;
import build.editor.scene.SceneCanvas;
import build.editor.scene.Universe;
import build.editor.scene.UniverseView;
import build.editor.scene.graph.SceneGraph;
import build.editor.ui.acomponents.*;
import build.utils.ClassCompiler;
import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.image.TextureLoader;
import java.awt.BorderLayout;
import java.io.File;
import java.io.IOException;
import javax.media.j3d.Alpha;
import javax.media.j3d.Appearance;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.Material;
import javax.media.j3d.PointLight;
import javax.media.j3d.PolygonAttributes;
import javax.media.j3d.RotationInterpolator;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Point3f;
import javax.vecmath.Vector3f;
import org.fife.ui.rsyntaxtextarea.RSyntaxDocument;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rsyntaxtextarea.Theme;
import org.fife.ui.rtextarea.RTextScrollPane;

public class JPanelMaterialEditor extends javax.swing.JPanel implements UIEditor {

    private final Canvas3D canvas;
    private final Universe universe;
    private final UniverseView view;
    private final BranchGroup bgBox;
    private final Box prevBox;
    private final BranchGroup bgSphere;
    private final Sphere prevSphere;
    private final PointLight light;
    private final TransformGroup trGroup;
    private final ClassCompiler compiler;
    
    private File file;
    private String classname;
    private Appearance appearance;
    private RotationInterpolator interpolator;

    public JPanelMaterialEditor(File sourcefile, String classname) {
        //Init core
        this.file = sourcefile;
        this.classname = classname;
        this.compiler = new ClassCompiler(J3DBuild.PROJECT_ROOT, this.file, this.classname);
        
        System.out.println(classname);
        
        //Init UI
        initComponents();
        setBackground(ThemeManager.COLOR_BACKGROUND);
        jPanelSideNav.setBackground(ThemeManager.COLOR_BACKGROUND);
        
        //Set up Canvas3D
        canvas = new SceneCanvas();
        universe = new Universe();
        view = new UniverseView();
        Transform3D transform = new Transform3D();
        transform.rotX(Math.toRadians(-30));
        transform.setTranslation(new Vector3f(0, 1, 2));
        view.setTransform(transform);
        view.addCanvas3D(canvas);
        universe.addView(view);
        
        //Appearance
        appearance = new Appearance();
        appearance.setMaterial(new Material(new Color3f(0.2f, 0.2f, 0.2f), new Color3f(0, 0, 0), new Color3f(1, 0, 0), new Color3f(1, 0, 0), 60));
        SceneGraph.setCapabilities(appearance);
        
        //Box
        bgBox = new BranchGroup();
        SceneGraph.setCapabilities(bgBox);
        prevBox = new Box(1, 1, 1, Box.GENERATE_NORMALS | Box.GENERATE_TEXTURE_COORDS, appearance);
        SceneGraph.setCapabilities(prevBox);
        
        //Sphere
        bgSphere = new BranchGroup();
        SceneGraph.setCapabilities(bgSphere);
        prevSphere = new Sphere(1.5f, Sphere.GENERATE_NORMALS | Sphere.GENERATE_TEXTURE_COORDS, 60, appearance);
        SceneGraph.setCapabilities(prevSphere);
        
        //Interpolator
        trGroup = new TransformGroup();
        SceneGraph.setCapabilities(trGroup);
        interpolator = new RotationInterpolator(new Alpha(-1,6000), trGroup);
        interpolator.setSchedulingBounds(new BoundingSphere());
        
        //Lighing
        light = new PointLight(new Color3f(1, 1, 1), new Point3f(0, 2, 2), new Point3f(0.2f, 0.1f, 0.2f));
        light.setInfluencingBounds(new BoundingSphere(new Point3d(), 100));
        
        //Scenery
        Appearance sceneryAppearance = new Appearance();
        sceneryAppearance.setMaterial(new Material(new Color3f(1, 1, 1), new Color3f(1, 1, 1), new Color3f(1, 1, 1), new Color3f(1, 1, 1), 0));
        sceneryAppearance.setTexture(new TextureLoader("res/gui/textures/grid256.png", null).getTexture());
        
        PolygonAttributes polyattri = new PolygonAttributes();
        polyattri.setCullFace(PolygonAttributes.CULL_FRONT);
        sceneryAppearance.setPolygonAttributes(polyattri);
                
        Box scenery = new Box(4, 4, 4, Box.GENERATE_TEXTURE_COORDS, sceneryAppearance);
        Transform3D transform2 = new Transform3D();
        transform2.rotY(Math.toRadians(45));
        TransformGroup sceneryTransform = new TransformGroup(transform2);
        
        //Add nodes
        bgBox.addChild(prevBox);
        bgSphere.addChild(prevSphere);
        bgSphere.addChild(interpolator);
        trGroup.addChild(bgSphere);
        BranchGroup group = new BranchGroup();
        group.addChild(light);
        sceneryTransform.addChild(scenery);
        group.addChild(sceneryTransform);
        group.addChild(trGroup);
        universe.addChild(group);
        
        //Code editor
        RSyntaxTextArea textArea = new RSyntaxTextArea();
        textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
        textArea.setCodeFoldingEnabled(true);
        textArea.setAutoIndentEnabled(true);
        RTextScrollPane sp = new RTextScrollPane(textArea);
        jPanelGraph.add(sp, BorderLayout.CENTER);
        
        //Default material
        String source = loadAppearance();
        
        RSyntaxDocument document = new RSyntaxDocument("text/java");
        try {
            document.insertString(0, source, new SimpleAttributeSet());
            textArea.setDocument(document);
        } catch (BadLocationException ex) {
        }
        
        //Dark theme
        try {
            Theme theme = Theme.load(getClass().getResourceAsStream("/gui/themes/j3dbuild.xml"));
            theme.apply(textArea);
        } catch (IOException ex) {
        }
        
        jPanelPreview.add(canvas, BorderLayout.CENTER);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelSideNav = new APanel();
        jPanelPreview = new javax.swing.JPanel();
        jToolBar1 = new AToolBar();
        jButtonSave = new AButton();
        jButtonRun = new AButton();
        jPanelGraph = new APanel();

        setLayout(new java.awt.BorderLayout());

        jPanelSideNav.setPreferredSize(new java.awt.Dimension(320, 485));
        jPanelSideNav.setLayout(new java.awt.BorderLayout());

        jPanelPreview.setBackground(new java.awt.Color(0, 0, 0));
        jPanelPreview.setPreferredSize(new java.awt.Dimension(320, 320));
        jPanelPreview.setLayout(new java.awt.BorderLayout());

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);
        jToolBar1.setMaximumSize(new java.awt.Dimension(13, 25));
        jToolBar1.setMinimumSize(new java.awt.Dimension(13, 25));

        jButtonSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/icons/iconSave.png"))); // NOI18N
        jButtonSave.setFocusable(false);
        jButtonSave.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonSave.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveActionPerformed(evt);
            }
        });
        jToolBar1.add(jButtonSave);

        jButtonRun.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/icons/iconPlay.png"))); // NOI18N
        jButtonRun.setFocusable(false);
        jButtonRun.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonRun.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonRun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRunActionPerformed(evt);
            }
        });
        jToolBar1.add(jButtonRun);

        jPanelPreview.add(jToolBar1, java.awt.BorderLayout.NORTH);

        jPanelSideNav.add(jPanelPreview, java.awt.BorderLayout.PAGE_START);

        add(jPanelSideNav, java.awt.BorderLayout.WEST);

        jPanelGraph.setLayout(new java.awt.BorderLayout());
        add(jPanelGraph, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveActionPerformed
        save();
    }//GEN-LAST:event_jButtonSaveActionPerformed

    private void jButtonRunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRunActionPerformed
        build();
    }//GEN-LAST:event_jButtonRunActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonRun;
    private javax.swing.JButton jButtonSave;
    private javax.swing.JPanel jPanelGraph;
    private javax.swing.JPanel jPanelPreview;
    private javax.swing.JPanel jPanelSideNav;
    private javax.swing.JToolBar jToolBar1;
    // End of variables declaration//GEN-END:variables

    
    public String loadAppearance() {
        return "";
    }
    
    @Override
    public void repaint() {
        super.repaint();
        if (view != null) {
            view.repaint();
        }
    }
    
    @Override
    public File getFile() {
        return null;
    }

    @Override
    public void save() {
    }

    @Override
    public void build() {
        save();
        try {
            appearance = (Appearance) compiler.compile();
            prevBox.setAppearance(appearance);
            prevSphere.setAppearance(appearance);
        } catch (ClassCastException ex) {
            ex.printStackTrace();
        }
    }
 
   @Override
    public void export(File target, String type) {
    }
}
