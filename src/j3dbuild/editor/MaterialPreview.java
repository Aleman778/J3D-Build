package j3dbuild.editor;

import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.image.TextureLoader;
import j3dbuild.editor.scene.J3DCanvas;
import j3dbuild.editor.scene.Universe;
import j3dbuild.editor.scene.UniverseView;
import j3dbuild.editor.scene.graph.SceneGraph;
import j3dbuild.editor.ui.APanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.media.j3d.Alpha;
import javax.media.j3d.Appearance;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.Locale;
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

/**
 *
 * @author alema
 */
public class MaterialPreview extends APanel {

    private final Canvas3D canvas;
    private final Universe universe;
    private final UniverseView view;
    private final BranchGroup bgBox;
    private final Box prevBox;
    private final BranchGroup bgSphere;
    private final Sphere prevSphere;
    private final PointLight light;
    private final Locale locale;
    private final TransformGroup trGroup;
    
    private Appearance appearance;
    private RotationInterpolator interpolator;
    
    public MaterialPreview() {
        
        //Init UI
        initComponents();
        
        //Set up Canvas3D
        canvas = new J3DCanvas() {

            @Override
            public void postRender() {
                super.getGraphics2D().setColor(Color.yellow);
                super.getGraphics2D().fillRect(0, 0, 32, 32);
                super.getGraphics2D().flush(false);
            }
            
        };
        universe = new Universe();
        view = new UniverseView();
        Transform3D transform = new Transform3D();
        transform.rotX(Math.toRadians(-30));
        transform.setTranslation(new Vector3f(0, 1, 2));
        view.setTransform(transform);
        view.addCanvas3D(canvas);
        locale = new Locale(universe);
        locale.addBranchGraph(view.getBranchGraph());
        
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
        interpolator.setSchedulingBounds(new BoundingSphere(new Point3d(), 100));
        
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
        locale.addBranchGraph(group);
        
        setLayout(new BorderLayout());
        add(canvas, BorderLayout.CENTER);
        
        canvas.setSize(280, 280);
        System.out.println(canvas);
        
        addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent me) {
                System.out.println("What " + me.getX());
                canvas.repaint();
            }
            
        });
    }

    public Appearance getAppearance() {
        return appearance;
    }
    
    public void setAppearance(Appearance appearance) {
        this.appearance = appearance;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setLayout(new java.awt.BorderLayout());
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
