package j3dbuild.editor.scene;

import com.sun.j3d.utils.image.TextureLoader;
import javax.media.j3d.Appearance;
import javax.media.j3d.Billboard;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Texture;
import javax.media.j3d.QuadArray;
import javax.media.j3d.PolygonAttributes;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.TransparencyAttributes;
import javax.vecmath.Point3d;
import javax.vecmath.Point3f;
import javax.vecmath.TexCoord2f;
import javax.vecmath.Vector3f;

public class Gizmo extends TransformGroup {
    
    private final Appearance appearance;
    private final Billboard billboard;
    private final QuadArray geometry;
    private final Shape3D shape;
    
    public Gizmo() {
        geometry = new QuadArray(4, QuadArray.COORDINATES | QuadArray.TEXTURE_COORDINATE_2);
        geometry.setCoordinate(0, new Point3f(-0.5f, -0.5f, 0));
        geometry.setCoordinate(1, new Point3f(-0.5f,  0.5f, 0));
        geometry.setCoordinate(2, new Point3f( 0.5f,  0.5f, 0));
        geometry.setCoordinate(3, new Point3f( 0.5f, -0.5f, 0));
        geometry.setTextureCoordinate(0, 0, new TexCoord2f(0, 0));
        geometry.setTextureCoordinate(0, 1, new TexCoord2f(0, 1));
        geometry.setTextureCoordinate(0, 2, new TexCoord2f(1, 1));
        geometry.setTextureCoordinate(0, 3, new TexCoord2f(1, 0));
        appearance = createAppearance();
        
        billboard = new Billboard(this, Billboard.ROTATE_ABOUT_AXIS, new Vector3f(0, 1, 0));
        billboard.setSchedulingBounds(new BoundingSphere(new Point3d(), 1000.0));
        shape = new Shape3D(geometry, appearance);
        addChild(billboard);
        addChild(shape);
    }
    
    private Appearance createAppearance() {
        Appearance result = new Appearance();
        TextureLoader loader = new TextureLoader("res/gui/gizmos/gizmoDirectionalLight.png", null);
        Texture texture = loader.getTexture();
        texture.setMinFilter(Texture.NICEST);
        texture.setMagFilter(Texture.NICEST);
        result.setTexture(texture);
        
        PolygonAttributes polygonattri = new PolygonAttributes();
        polygonattri.setCullFace(PolygonAttributes.CULL_NONE);
        result.setPolygonAttributes(polygonattri);
        
        TransparencyAttributes transparancyattri = new TransparencyAttributes();
        transparancyattri.setTransparencyMode(TransparencyAttributes.BLENDED);
        transparancyattri.setTransparency(1.0f);
        result.setTransparencyAttributes(transparancyattri);
        
        return result;
    }
}
