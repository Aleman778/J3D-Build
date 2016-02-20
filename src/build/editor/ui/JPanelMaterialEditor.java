package build.editor.ui;

import build.editor.manager.ThemeManager;
import build.editor.scene.SceneCanvas;
import build.editor.scene.Universe;
import build.editor.scene.UniverseView;
import build.editor.scene.graph.SceneGraph;
import build.editor.ui.acomponents.*;
import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.image.TextureLoader;
import java.awt.BorderLayout;
import java.io.File;
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
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Point3f;
import javax.vecmath.Vector3f;

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
    private RotationInterpolator interpolator;
    
    private final Appearance apperance;

    public JPanelMaterialEditor() {
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
        apperance = new Appearance();
        apperance.setMaterial(new Material(new Color3f(0.2f, 0.2f, 0.2f), new Color3f(0, 0, 0), new Color3f(1, 0, 0), new Color3f(1, 0, 0), 60));
        SceneGraph.setCapabilities(apperance);
        
        //Box
        bgBox = new BranchGroup();
        SceneGraph.setCapabilities(bgBox);
        prevBox = new Box(1, 1, 1, Box.GENERATE_NORMALS | Box.GENERATE_TEXTURE_COORDS, apperance);
        
        //Sphere
        bgSphere = new BranchGroup();
        SceneGraph.setCapabilities(bgSphere);
        prevSphere = new Sphere(1.5f, Sphere.GENERATE_NORMALS | Sphere.GENERATE_TEXTURE_COORDS, 60, apperance);
        
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
        bgBox.addChild(interpolator);
        bgSphere.addChild(prevSphere);
        trGroup.addChild(bgBox);
        BranchGroup group = new BranchGroup();
        group.addChild(light);
        sceneryTransform.addChild(scenery);
        group.addChild(sceneryTransform);
        group.addChild(trGroup);
        universe.addChild(group);
        
        jPanelPreview.add(canvas, BorderLayout.CENTER);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelSideNav = new APanel();
        jPanelPreview = new javax.swing.JPanel();
        jPanelGraph = new APanel();

        setLayout(new java.awt.BorderLayout());

        jPanelSideNav.setPreferredSize(new java.awt.Dimension(320, 485));
        jPanelSideNav.setLayout(new java.awt.BorderLayout());

        jPanelPreview.setBackground(new java.awt.Color(0, 0, 0));
        jPanelPreview.setPreferredSize(new java.awt.Dimension(320, 320));
        jPanelPreview.setLayout(new java.awt.BorderLayout());
        jPanelSideNav.add(jPanelPreview, java.awt.BorderLayout.PAGE_START);

        add(jPanelSideNav, java.awt.BorderLayout.LINE_START);

        javax.swing.GroupLayout jPanelGraphLayout = new javax.swing.GroupLayout(jPanelGraph);
        jPanelGraph.setLayout(jPanelGraphLayout);
        jPanelGraphLayout.setHorizontalGroup(
            jPanelGraphLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 660, Short.MAX_VALUE)
        );
        jPanelGraphLayout.setVerticalGroup(
            jPanelGraphLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 485, Short.MAX_VALUE)
        );

        add(jPanelGraph, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanelGraph;
    private javax.swing.JPanel jPanelPreview;
    private javax.swing.JPanel jPanelSideNav;
    // End of variables declaration//GEN-END:variables

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
    }

    @Override
    public void export(File target, String type) {
    }
}
