package j3dbuild.editor.ui;

import j3dbuild.editor.scene.SceneView;
import j3dbuild.editor.scene.graph.SceneGraph;
import com.sun.j3d.utils.geometry.Cylinder;
import com.sun.j3d.utils.geometry.Cone;
import com.sun.j3d.utils.geometry.Box;
import javax.media.j3d.Appearance;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Material;
import javax.media.j3d.RenderingAttributes;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Color3f;
import javax.vecmath.Vector3f;

public class TransformUtility extends TransformGroup {
    
    private static final int CAM_TO_TRANSFORM_DISTANCE = 5;
    
    //Appearance
    private static final Appearance APPEARANCE_RED;
    private static final Appearance APPEARANCE_GREEN;
    private static final Appearance APPEARANCE_BLUE;
    
    static {
        RenderingAttributes depthbuffdisable = new RenderingAttributes();
        depthbuffdisable.setDepthBufferEnable(true);
        
        APPEARANCE_RED = new Appearance();
        APPEARANCE_RED.setMaterial(new Material(
                new Color3f(1, 0, 0), new Color3f(1, 0, 0),
                new Color3f(1, 0, 0), new Color3f(1, 0, 0), 0));
        APPEARANCE_RED.setRenderingAttributes(depthbuffdisable);
        APPEARANCE_GREEN = new Appearance();
        APPEARANCE_GREEN.setMaterial(new Material(
                new Color3f(0, 1, 0), new Color3f(0, 1, 0),
                new Color3f(0, 1, 0), new Color3f(0, 1, 0), 0));
        APPEARANCE_GREEN.setRenderingAttributes(depthbuffdisable);
        APPEARANCE_BLUE = new Appearance();
        APPEARANCE_BLUE.setMaterial(new Material(
                new Color3f(0, 0, 1), new Color3f(0, 0, 1),
                new Color3f(0, 0, 1), new Color3f(0, 0, 1), 0));
        APPEARANCE_BLUE.setRenderingAttributes(depthbuffdisable);
        
    }
    
    private final SceneView view;
    private final BranchGroup root;
    private final BranchGroup translate;
    private final BranchGroup rotate;
    private final BranchGroup scale;
    
    private TransformGroup transformGroup;
    private Transform3D transform;
    
    public TransformUtility(SceneView view) {
        this.view = view;
        
        root = new BranchGroup();
        SceneGraph.setCapabilities(root);
        
        translate = constructTranslation();
        rotate = constructTranslation();
        scale = constructScale();
        root.addChild(translate);
        
        addChild(root);
    }
    
    public void setTransformGroup(TransformGroup group) {
        transformGroup = group;
        transform = new Transform3D();
        transformGroup.getTransform(transform);
        setTransform(transform);
    }
    
    public void update(Vector3f camera) {
        Vector3f position = new Vector3f();
        Transform3D tr3d = new Transform3D();
        getTransform(tr3d);
        tr3d.get(position);
        
    }
    
    private BranchGroup constructTranslation() {
        BranchGroup result = new BranchGroup();
        
        Transform3D tr3dX = new Transform3D();
        tr3dX.rotZ(Math.PI + Math.PI / 2);
        tr3dX.setTranslation(new Vector3f(1, 0, 0));
        TransformGroup tgX = new TransformGroup(tr3dX);
        Transform3D tr3dY = new Transform3D();
        tr3dY.setTranslation(new Vector3f(0, 1, 0));
        TransformGroup tgY = new TransformGroup(tr3dY);
        Transform3D tr3dZ = new Transform3D();
        tr3dZ.rotX(Math.PI / 2.0);
        tr3dZ.setTranslation(new Vector3f(0, 0, 1));
        TransformGroup tgZ = new TransformGroup(tr3dZ);
        
        Cylinder posCylX = new Cylinder(0.01f, 2, APPEARANCE_RED);
        tgX.addChild(posCylX);
        Cylinder posCylY = new Cylinder(0.01f, 2, APPEARANCE_GREEN);
        tgY.addChild(posCylY);
        Cylinder posCylZ = new Cylinder(0.01f, 2, APPEARANCE_BLUE);
        tgZ.addChild(posCylZ);
        
        Transform3D trCone = new Transform3D();
        trCone.setTranslation(new Vector3f(0, 1, 0));
        
        Cone posConeX = new Cone(0.1f, 0.6f, APPEARANCE_RED);
        TransformGroup tg2X = new TransformGroup(trCone);
        tg2X.addChild(posConeX);
        tgX.addChild(tg2X);
        
        Cone posConeY = new Cone(0.1f, 0.6f, APPEARANCE_GREEN);
        TransformGroup tg2Y = new TransformGroup(trCone);
        tg2Y.addChild(posConeY);
        tgY.addChild(tg2Y);
        
        Cone posConeZ = new Cone(0.1f, 0.6f, APPEARANCE_BLUE);
        TransformGroup tg2Z = new TransformGroup(trCone);
        tg2Z.addChild(posConeZ);
        tgZ.addChild(tg2Z);
        
        result.addChild(tgX);
        result.addChild(tgY);
        result.addChild(tgZ);
        
        return result;
    }
    
    private BranchGroup constructScale() {
        BranchGroup result = new BranchGroup();
        
        Transform3D tr3dX = new Transform3D();
        tr3dX.rotZ(Math.PI + Math.PI / 2);
        tr3dX.setTranslation(new Vector3f(1, 0, 0));
        TransformGroup tgX = new TransformGroup(tr3dX);
        Transform3D tr3dY = new Transform3D();
        tr3dY.setTranslation(new Vector3f(0, 1, 0));
        TransformGroup tgY = new TransformGroup(tr3dY);
        Transform3D tr3dZ = new Transform3D();
        tr3dZ.rotX(Math.PI / 2.0);
        tr3dZ.setTranslation(new Vector3f(0, 0, 1));
        TransformGroup tgZ = new TransformGroup(tr3dZ);
        
        Cylinder posCylX = new Cylinder(0.01f, 2, APPEARANCE_RED);
        tgX.addChild(posCylX);
        Cylinder posCylY = new Cylinder(0.01f, 2, APPEARANCE_GREEN);
        tgY.addChild(posCylY);
        Cylinder posCylZ = new Cylinder(0.01f, 2, APPEARANCE_BLUE);
        tgZ.addChild(posCylZ);
        
        Transform3D trCone = new Transform3D();
        trCone.setTranslation(new Vector3f(0, 1, 0));
        
        Box posBoxX = new Box(0.1f, 0.1f, 0.1f, APPEARANCE_RED);
        TransformGroup tg2X = new TransformGroup(trCone);
        tg2X.addChild(posBoxX);
        tgX.addChild(tg2X);
        
        Box posBoxY = new Box(0.1f, 0.1f, 0.1f, APPEARANCE_GREEN);
        TransformGroup tg2Y = new TransformGroup(trCone);
        tg2Y.addChild(posBoxY);
        tgY.addChild(tg2Y);
        
        Box posBoxZ = new Box(0.1f, 0.1f, 0.1f, APPEARANCE_BLUE);
        TransformGroup tg2Z = new TransformGroup(trCone);
        tg2Z.addChild(posBoxZ);
        tgZ.addChild(tg2Z);
        
        result.addChild(tgX);
        result.addChild(tgY);
        result.addChild(tgZ);
        
        return result;
    }    
}
