package build.editor.scene;

import build.editor.scene.graph.SceneGraph;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.PhysicalBody;
import javax.media.j3d.PhysicalEnvironment;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.View;
import javax.media.j3d.ViewPlatform;
import javax.vecmath.Vector3f;

public class UniverseView extends View {

    protected final TransformGroup vpTransform;
    protected final Transform3D transform;
    protected final BranchGroup vpRoot;
    protected final ViewPlatform vp;
    
    public UniverseView() {
        PhysicalBody pb = new PhysicalBody();
        PhysicalEnvironment pe = new PhysicalEnvironment();
        
        vp = new ViewPlatform();
        vp.setViewAttachPolicy(View.RELATIVE_TO_FIELD_OF_VIEW);
        vp.setActivationRadius(100.0f);
        vpRoot = new BranchGroup();
        SceneGraph.setCapabilities(vpRoot);
        transform = new Transform3D();
        transform.rotX(Math.toRadians(-35));
        transform.setTranslation(new Vector3f(0, 8, 13));
        vpTransform = new TransformGroup(transform);
        SceneGraph.setCapabilities(vpTransform);
        vpTransform.addChild(vp);
        vpRoot.addChild(vpTransform);
        
        setPhysicalBody(pb);
        setPhysicalEnvironment(pe);
        attachViewPlatform(vp);
        setBackClipDistance(100.0f);
        setFrontClipDistance(0.1f);
        setMinimumFrameCycleTime(15);
    }
    
    public BranchGroup getBranchGraph() {
        return vpRoot;
    }
    
    public void setTransform(Transform3D transform) {
        vpTransform.setTransform(transform);
    }
}
