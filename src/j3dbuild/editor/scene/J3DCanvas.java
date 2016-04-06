package j3dbuild.editor.scene;

import com.sun.j3d.utils.picking.PickCanvas;
import java.awt.AWTException;
import java.awt.GraphicsConfigTemplate;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Robot;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.GraphicsConfigTemplate3D;
import javax.media.j3d.Locale;

public class J3DCanvas extends Canvas3D {
    
    private PickCanvas picking;
    
    public J3DCanvas() {
        this(getBestGraphicsConfiguration());
    }

    public J3DCanvas(boolean offScreen) {
        this(getBestGraphicsConfiguration(), offScreen);
    }

    public J3DCanvas(GraphicsConfiguration graphicsConfiguration) {
        this(graphicsConfiguration, false);
    }

    public J3DCanvas(GraphicsConfiguration graphicsConfiguration, boolean offScreen) {
        super(graphicsConfiguration, offScreen);
        picking = null;
    }
    
    public static GraphicsConfiguration getBestGraphicsConfiguration() {
        GraphicsConfigTemplate3D gc3D = new GraphicsConfigTemplate3D();
        gc3D.setSceneAntialiasing(GraphicsConfigTemplate.PREFERRED);
        GraphicsDevice devices[] = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices();
        GraphicsConfiguration gc = devices[0].getBestConfiguration(gc3D);
        return gc;
    }
    
    public void setPickingCanvas(BranchGroup group) {
        picking = new PickCanvas(this, group);
        picking.setMode(PickCanvas.BOUNDS);
    }
    
    public void setPickingCanvas(Locale locale) {
        picking = new PickCanvas(this, locale);
        picking.setMode(PickCanvas.BOUNDS);
    }

    public PickCanvas getPicking() {
        return picking;
    }
    
    public boolean isPickable() {
        return picking != null;
    }
}
