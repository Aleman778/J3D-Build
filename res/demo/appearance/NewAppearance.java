package demo.appearance;

import javax.media.j3d.Appearance;
import javax.media.j3d.Material;
import javax.vecmath.Color3f;

public class NewAppearance extends Appearance {
    
    public NewAppearance() {
        Material material = new Material();
        material.setAmbientColor(new Color3f(0, 0.5f, 1.0f));
        material.setDiffuseColor(new Color3f(0, 0.5f, 1.0f));
        material.setSpecularColor(new Color3f(1.0f, 1.0f, 1.0f));
        material.setEmissiveColor(new Color3f(0, 0.1f, 0.2f));
        material.setShininess(30);
        
        setMaterial(material);
    }
    
}
