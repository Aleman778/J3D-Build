package build.editor.scene;

import com.sun.j3d.utils.geometry.Cone;
import com.sun.j3d.utils.image.TextureLoader;
import javax.media.j3d.Appearance;
import javax.media.j3d.PolygonAttributes;
import javax.media.j3d.QuadArray;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Texture;
import javax.media.j3d.TextureAttributes;
import javax.vecmath.Point3d;

public class TexturedFloor extends Shape3D {

    public TexturedFloor() {
        super();
        Cone cone = new Cone();
        
        QuadArray quad = new QuadArray(4, QuadArray.COORDINATES | QuadArray.TEXTURE_COORDINATE_2);
        quad.setCoordinate(0, new Point3d(-50, 0, -50));
        quad.setCoordinate(1, new Point3d(-50, 0, 50));
        quad.setCoordinate(2, new Point3d(50, 0, 50));
        quad.setCoordinate(3, new Point3d(50, 0, -50));
        quad.setTextureCoordinate(0, new float[] {0, 0});
        quad.setTextureCoordinate(1, new float[] {0, 50});
        quad.setTextureCoordinate(2, new float[] {50, 50});
        quad.setTextureCoordinate(3, new float[] {50, 0});
        
        setGeometry(quad);
        setAppearance(textureAppearance("src/build/editor/scene/green-grass-texture.jpg"));
        setBoundsAutoCompute(true);
    }
    
    public static Appearance textureAppearance(String path) {
        Appearance appearance = new Appearance();
        Texture tex = new TextureLoader(path, null).getTexture();
        appearance.setTexture(tex);
        tex.setMinFilter(Texture.NICEST);
        PolygonAttributes polyattri = new PolygonAttributes();
        polyattri.setCullFace(PolygonAttributes.CULL_NONE);
        appearance.setPolygonAttributes(polyattri);
        TextureAttributes texAttr = new TextureAttributes();
        texAttr.setTextureMode(TextureAttributes.MODULATE);
        texAttr.setPerspectiveCorrectionMode(TextureAttributes.NICEST);
        appearance.setTextureAttributes(texAttr);
        return appearance;
    }
    
}
