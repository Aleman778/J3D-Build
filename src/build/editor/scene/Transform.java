package build.editor.scene;

import javax.media.j3d.Transform3D;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;

public class Transform {
    
    private Vector3f translation;
    private Vector3f rotation;
    private Vector3d scale;

    public Transform(Vector3f translation, Vector3f rotation, Vector3d scale) {
        this.translation = translation;
        this.rotation = rotation;
        this.scale = scale;
    }
    
    public Transform() {
        this(new Vector3f(), new Vector3f(), new Vector3d(1, 1, 1));
    }

    public Vector3f getTranslation() {
        return translation;
    }

    public void setTranslation(Vector3f translation) {
        this.translation = translation;
    }

    public void translate(Vector3f translation) {
        this.translation.add(translation);
    }
    
    public Vector3f getRotation() {
        return rotation;
    }

    public void setRotation(Vector3f rotation) {
        this.rotation = rotation;
    }

    public void rotate(Vector3f rotation) {
        this.rotation.add(rotation);
    }

    public Vector3d getScale() {
        return scale;
    }

    public void setScale(Vector3d scale) {
        this.scale = scale;
    }

    public void scale(Vector3d scale) {
        this.scale.add(scale);
    }
    
    public Transform3D getTransform3D() {
        Transform3D result = new Transform3D();
        Transform3D trRotX = new Transform3D();
        trRotX.rotX(rotation.x);
        Transform3D trRotY = new Transform3D();
        trRotY.rotY(rotation.y);
        Transform3D trRotZ = new Transform3D();
        trRotZ.rotZ(rotation.z);
        
        trRotY.mul(trRotZ);
        trRotX.mul(trRotY);
        result.mul(trRotX);
        result.setTranslation(translation);
        result.setScale(scale);
        
        return result;
    }
}
