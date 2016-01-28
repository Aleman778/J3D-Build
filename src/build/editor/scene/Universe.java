package build.editor.scene;

import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.ColorCube;
import com.sun.j3d.utils.image.TextureLoader;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.media.j3d.Appearance;
import javax.media.j3d.Background;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Group;
import javax.media.j3d.ImageComponent2D;
import javax.media.j3d.Locale;
import javax.media.j3d.Node;
import javax.media.j3d.TexCoordGeneration;
import javax.media.j3d.Texture;
import javax.media.j3d.Texture2D;
import javax.media.j3d.TextureAttributes;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.VirtualUniverse;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3f;

public class Universe extends VirtualUniverse {
    
    private final BranchGroup root;
    private final Locale locale;
    
    public Universe() {
        //Root
        root = new BranchGroup();
        root.setCapability(Group.ALLOW_CHILDREN_EXTEND);
        root.setCapability(Group.ALLOW_CHILDREN_READ);
        root.setCapability(Group.ALLOW_CHILDREN_WRITE);
        
        //DEBUG REMOVE LATER
        TexturedFloor floor = new TexturedFloor();
        
        ColorCube cube = new ColorCube();
        Background back = new Background(new Color3f(0.2f, 0.5f, 1.0f));
        BoundingSphere sphere = new BoundingSphere(new Point3d(0, 0, 0), 100);
        back.setApplicationBounds(sphere);
        Transform3D transform = new Transform3D();
        transform.setTranslation(new Vector3f(0, 1, 0));
        TransformGroup group = new TransformGroup(transform);
        group.addChild(cube);
        root.addChild(group);
        root.addChild(floor);
        root.addChild(back);
        
        //Locale
        locale = new Locale(this);
        locale.addBranchGraph(root);
    }
    
    public void addSceneView(SceneView view) {
        locale.addBranchGraph(view.getBranchGraph());
    }
    
    public void addChild(Node child) {
        root.addChild(child);
    }
    
    public Locale getLocale() {
        return locale;
    }
    
    public BranchGroup getRoot() {
        return root;
    }
    
    public void load(File file) {
        
    }
    
    public void save(File file) {
        
    }
    
    public void export(File file) {
        
    }
}
