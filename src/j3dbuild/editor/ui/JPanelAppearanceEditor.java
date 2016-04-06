package j3dbuild.editor.ui;

import j3dbuild.editor.Editor;
import j3dbuild.editor.manager.ThemeManager;
import j3dbuild.editor.scene.J3DCanvas;
import j3dbuild.editor.scene.Universe;
import j3dbuild.editor.scene.UniverseView;
import j3dbuild.editor.scene.graph.SceneGraph;
import j3dbuild.editor.ui.acomponents.*;
import j3dbuild.utils.ClassCompiler;
import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.image.TextureLoader;
import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.media.j3d.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;
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

public final class JPanelAppearanceEditor extends javax.swing.JPanel implements UIEditor {

    private static final Appearance DEFAULT = new Appearance();
    
    static {
        Material material = new Material();
        material.setAmbientColor(1, 0, 0);
        material.setDiffuseColor(1, 0, 0);
        material.setSpecularColor(1, 0, 0);
        material.setEmissiveColor(0.5f, 0, 0);
        material.setShininess(10);
        DEFAULT.setMaterial(material);
    }
    
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
    private final RSyntaxTextArea codeEditor;
    
    private File file;
    private String classname;
    private Appearance appearance;
    private BranchGroup interpolatorGroup;
    private RotationInterpolator interpolator;
    private boolean shape;

    public JPanelAppearanceEditor(File sourcefile, String classname) {
        //Init core
        this.file = sourcefile;
        this.classname = classname;
        this.compiler = null;//new ClassCompiler(Editor.PROJECT_ROOT, this.file, this.classname);
        this.shape = false;
        
        System.out.println(classname);
        
        //Init UI
        initComponents();
        setBackground(ThemeManager.COLOR_BACKGROUND);
        jPanelSideNav.setBackground(ThemeManager.COLOR_BACKGROUND);
        
        //Set up Canvas3D
        canvas = new J3DCanvas();
        universe = new Universe();
        view = new UniverseView();
        Transform3D transform = new Transform3D();
        transform.rotX(Math.toRadians(-30));
        transform.setTranslation(new Vector3f(0, 1, 2));
        view.setTransform(transform);
        view.addCanvas3D(canvas);
        universe.addView(view);
        
        //Box
        bgBox = new BranchGroup();
        SceneGraph.setCapabilities(bgBox);
        prevBox = new Box(1, 1, 1, Box.GENERATE_NORMALS | Box.GENERATE_TEXTURE_COORDS, appearance);
        SceneGraph.setCapabilities(prevBox);
        
        //Sphere
        bgSphere = new BranchGroup();
        SceneGraph.setCapabilities(bgSphere);
        prevSphere = new Sphere(1.5f, Sphere.GENERATE_NORMALS | Sphere.GENERATE_TEXTURE_COORDS, 100, appearance);
        SceneGraph.setCapabilities(prevSphere);
        
        //Interpolator
        trGroup = new TransformGroup();
        SceneGraph.setCapabilities(trGroup);
        interpolator = new RotationInterpolator(new Alpha(-1,6000), trGroup);
        interpolator.setSchedulingBounds(new BoundingSphere());
        interpolatorGroup = new BranchGroup();
        SceneGraph.setCapabilities(interpolatorGroup);
        interpolatorGroup.addChild(interpolator);
        
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
        bgSphere.addChild(interpolatorGroup);
        trGroup.addChild(bgSphere);
        BranchGroup group = new BranchGroup();
        group.addChild(light);
        sceneryTransform.addChild(scenery);
        group.addChild(sceneryTransform);
        group.addChild(trGroup);
        universe.addChild(group);
        
        //Code editor
        codeEditor = new RSyntaxTextArea();
        codeEditor.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
        codeEditor.setCodeFoldingEnabled(true);
        codeEditor.setBracketMatchingEnabled(true);
        codeEditor.setAutoIndentEnabled(true);
        codeEditor.setTabSize(4);
        System.out.println(codeEditor.getTabSize());
        RTextScrollPane sp = new RTextScrollPane(codeEditor);
        jPanelGraph.add(sp, BorderLayout.CENTER);
        
        //Default material
        String source = loadSource();
        
        RSyntaxDocument document = new RSyntaxDocument("text/java");
        try {
            document.insertString(0, source, new SimpleAttributeSet());
            codeEditor.setDocument(document);
            if (document instanceof PlainDocument) {
                document.putProperty(PlainDocument.tabSizeAttribute, 4);
            }
        } catch (BadLocationException ex) {
        }
        
        //Dark theme
        try {
            Theme theme = Theme.load(getClass().getResourceAsStream("/gui/themes/j3dbuild.xml"));
            theme.apply(codeEditor);
        } catch (IOException ex) {
        }
        
        //Add preview to panel
        jPanelPreview.add(canvas, BorderLayout.CENTER);
        
        //Build Appearance
        build();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelSideNav = new APanel();
        jPanelPreview = new javax.swing.JPanel();
        jToolBar1 = new AToolBar();
        jButtonSave = new AButton();
        jButtonRun = new AButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        jButtonShape = new AButton();
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
        jToolBar1.add(jSeparator1);

        jButtonShape.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/icons/iconShape3DO.png"))); // NOI18N
        jButtonShape.setFocusable(false);
        jButtonShape.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonShape.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonShape.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonShapeActionPerformed(evt);
            }
        });
        jToolBar1.add(jButtonShape);

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

    private void jButtonShapeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonShapeActionPerformed
        shape = !shape;
        trGroup.removeAllChildren();
        
        if (shape) {
            trGroup.addChild(bgBox);
            bgSphere.removeChild(interpolatorGroup);
            bgBox.addChild(interpolatorGroup);
        } else {
            trGroup.addChild(bgSphere);
            bgBox.removeChild(interpolatorGroup);
            bgSphere.addChild(interpolatorGroup);
        }
    }//GEN-LAST:event_jButtonShapeActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonRun;
    private javax.swing.JButton jButtonSave;
    private javax.swing.JButton jButtonShape;
    private javax.swing.JPanel jPanelGraph;
    private javax.swing.JPanel jPanelPreview;
    private javax.swing.JPanel jPanelSideNav;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar jToolBar1;
    // End of variables declaration//GEN-END:variables

    
    public String loadSource() {
        String source = "";
        
        try {
            BufferedReader br;
            try (FileReader reader = new FileReader(getFile())) {
                br = new BufferedReader(reader);
                String line = br.readLine();
                do {
                    source += line + "\n";
                }
                while ((line = br.readLine()) != null);
            }
            br.close();
        } catch (IOException ex) {
            
        }
        
        
        return source;
    }
    
    public String getLoadedSource() {
        try {
            Document document = codeEditor.getDocument();
            if (document != null) {
                int start = document.getStartPosition().getOffset();
                int end = document.getEndPosition().getOffset();
                    return document.getText(start, end);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
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
        return file;
    }

    @Override
    public void save() {
        try {
            FileWriter writer = new FileWriter(getFile());
            String source = getLoadedSource();
            writer.write(source);
            writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
    }

    @Override
    public void build() {
        save();
        try {
            appearance = (Appearance) compiler.compile();
            SceneGraph.setCapabilities(appearance);
            Color3f color = new Color3f();
            appearance.getMaterial().getDiffuseColor(color);
            System.out.println(color.toString() + (appearance.getClass() == prevBox.getAppearance().getClass()));
            prevBox.setAppearance(appearance);
            prevSphere.setAppearance(appearance);
            
        } catch (Exception ex) {
            appearance = DEFAULT;
            prevBox.setAppearance(DEFAULT);
            prevSphere.setAppearance(DEFAULT);
            ex.printStackTrace();
        }
    }
 
   @Override
    public void export(File target, String type) {
    }
}
