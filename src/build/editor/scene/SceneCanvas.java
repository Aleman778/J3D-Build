package build.editor.scene;

import com.sun.j3d.utils.picking.PickCanvas;
import java.awt.GraphicsConfigTemplate;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.GraphicsConfigTemplate3D;
import javax.media.j3d.Locale;

public class SceneCanvas extends Canvas3D {
    
    public SceneCanvas() {
        this(getBestGraphicsConfiguration());
    }

    public SceneCanvas(boolean offScreen) {
        this(getBestGraphicsConfiguration(), offScreen);
    }

    public SceneCanvas(GraphicsConfiguration graphicsConfiguration) {
        this(graphicsConfiguration, false);
    }

    public SceneCanvas(GraphicsConfiguration graphicsConfiguration, boolean offScreen) {
        super(graphicsConfiguration, offScreen);
    }
    
    public static GraphicsConfiguration getBestGraphicsConfiguration() {
        GraphicsConfigTemplate3D gc3D = new GraphicsConfigTemplate3D();
        gc3D.setSceneAntialiasing(GraphicsConfigTemplate.PREFERRED);
        GraphicsDevice devices[] = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices();
        GraphicsConfiguration gc = devices[0].getBestConfiguration(gc3D);
        return gc;
    }
}
