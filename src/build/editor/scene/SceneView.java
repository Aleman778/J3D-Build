package build.editor.scene;

import com.sun.j3d.utils.geometry.Primitive;
import com.sun.j3d.utils.picking.PickCanvas;
import com.sun.j3d.utils.picking.PickResult;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.media.j3d.Appearance;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.Locale;
import javax.media.j3d.PhysicalBody;
import javax.media.j3d.PhysicalEnvironment;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.View;
import javax.media.j3d.ViewPlatform;
import javax.swing.SwingUtilities;
import javax.vecmath.Point2i;
import javax.vecmath.Quat4f;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;

public class SceneView extends View implements MouseListener, MouseMotionListener, MouseWheelListener {

    public static final float ZOOM_SENSIBILITY = 6f;
    
    private final TransformGroup vpTransform;
    private final Transform3D transform;
    private final BranchGroup vpRoot;
    private final ViewPlatform vp;
    
    private Robot robot = null;
    private PickCanvas pickCanvas = null;
    private Point2i mouse;
    
    public SceneView() {
        PhysicalBody pb = new PhysicalBody();
        PhysicalEnvironment pe = new PhysicalEnvironment();
        
        vp = new ViewPlatform();
        vp.setViewAttachPolicy(View.RELATIVE_TO_FIELD_OF_VIEW);
        vp.setActivationRadius(100.0f);
        vpRoot = new BranchGroup();
        vpTransform = new TransformGroup();
        vpTransform.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        vpTransform.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
        vpTransform.addChild(vp);
        vpRoot.addChild(vpTransform);
        transform = new Transform3D();
        transform.setTranslation(new Vector3f(0, 0, 8));
        
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
    
    public void setCameraOutput(Canvas3D canvas) {
        canvas.addMouseListener(this);
        canvas.addMouseMotionListener(this);
        canvas.addMouseWheelListener(this);
        canvas.requestFocus();
    }
    
    public void setPickingCanvas(Canvas3D canvas, BranchGroup group) {
        pickCanvas = new PickCanvas(canvas, group);
        pickCanvas.setMode(PickCanvas.BOUNDS);
        try {
            robot = new Robot();
        } catch (AWTException ex) {
        }
    }
    
    public void setPickingCanvas(Canvas3D canvas, Locale locale) {
        pickCanvas = new PickCanvas(canvas, locale);
        pickCanvas.setMode(PickCanvas.BOUNDS);
        try {
            robot = new Robot();
        } catch (AWTException ex) {
        }
    }
    
    public boolean isPickable() {
        return pickCanvas != null;
    }
    
    public void setTransform(Transform3D transform) {
        vpTransform.setTransform(transform);
    }
    
    private void robotMouseMove(MouseEvent me) {
        if (robot != null) {
            if (me.getX() > me.getComponent().getWidth()) {
                mouse = new Point2i(me.getXOnScreen() - me.getComponent().getWidth(), me.getYOnScreen());
                robot.mouseMove(mouse.getX(), mouse.getY());
            } else if (me.getX() < 0) {
                mouse = new Point2i(me.getXOnScreen() + me.getComponent().getWidth(), me.getYOnScreen());
                robot.mouseMove(mouse.getX(), mouse.getY());
            }
            if (me.getY() > me.getComponent().getHeight()) {
                mouse = new Point2i(me.getXOnScreen(), me.getYOnScreen() - me.getComponent().getHeight());
                robot.mouseMove(mouse.getX(), mouse.getY());
            } else if (me.getY() < 0) {
                mouse = new Point2i(me.getXOnScreen(), me.getYOnScreen() + me.getComponent().getHeight());
                robot.mouseMove(mouse.getX(), mouse.getY());
            }
        }
    }
    
    @Override
    public void mouseDragged(MouseEvent me) {
        if (SwingUtilities.isRightMouseButton(me)) {
            Transform3D trRotX = new Transform3D();
            Transform3D trRotY = new Transform3D();
            Transform3D trRotZ = new Transform3D();
            trRotX.rotX((mouse.y - me.getY()) / 360f);
            trRotY.rotY((mouse.x - me.getX()) / 360f);
            trRotZ.rotZ(0);
            
            trRotY.mul(trRotZ);
            trRotX.mul(trRotY);
            transform.mul(trRotX);
            setTransform(transform);
            
            mouse = new Point2i(me.getX(), me.getY());
            //robotMouseMove(me);
        }
        
        if (SwingUtilities.isMiddleMouseButton(me)) {
            Vector3f movement = new Vector3f();
            movement.x -= (me.getX() - mouse.x) / 36f;
            movement.y += (me.getY() - mouse.y) / 36f;
            
            Transform3D translation = new Transform3D();
            translation.setTranslation(movement);
            transform.mul(translation);
            
            setTransform(transform);
            
            mouse = new Point2i(me.getX(), me.getY());
            //robotMouseMove(me);
        }
        System.out.println(me.getX() - mouse.x);
    }
    
    @Override
    public void mouseMoved(MouseEvent me) {
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        if (isPickable()) {
            pickCanvas.setShapeLocation(me);
            PickResult result = pickCanvas.pickClosest();
            
            if (result == null) {
                System.out.println("Noting Picked!");
            } else {
                Primitive primitive = (Primitive) result.getNode(PickResult.PRIMITIVE);
                Shape3D shape3D = (Shape3D) result.getNode(PickResult.SHAPE3D);
                if (primitive != null) {
                    System.out.println(primitive.getClass().getName());
                } else if (shape3D != null) {
                    System.out.println(shape3D.getClass().getName());
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent me) {
        mouse = new Point2i(me.getX(), me.getY());
    }

    @Override
    public void mouseReleased(MouseEvent me) {
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent mwe) {
        Vector3f movement = new Vector3f();
        movement.z += mwe.getUnitsToScroll() / ZOOM_SENSIBILITY;

        Transform3D translation = new Transform3D();
        translation.setTranslation(movement);
        transform.mul(translation);
        
        setTransform(transform);
    }
}
