package build.editor.scene;

import build.editor.scene.graph.SceneGraph;
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
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.Locale;
import javax.media.j3d.Node;
import javax.media.j3d.PhysicalBody;
import javax.media.j3d.PhysicalEnvironment;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.View;
import javax.media.j3d.ViewPlatform;
import javax.swing.SwingUtilities;
import javax.vecmath.Point2i;
import javax.vecmath.Vector3f;

public class SceneView extends View implements MouseListener, MouseMotionListener, MouseWheelListener {

    public static final float ZOOM_SENSIBILITY = 6f;
    public static final float TARGET_DISTANCE = 18f;
    
    private final TransformGroup vpTransform;
    private final Transform3D transform;
    private final BranchGroup vpRoot;
    private final ViewPlatform vp;
    
    private Node selectedNode = null;
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
        SceneGraph.setCapabilities(vpRoot);
        transform = new Transform3D();
        transform.rotX(Math.toRadians(-35));
        transform.setTranslation(new Vector3f(0, 8, 13));
        vpTransform = new TransformGroup(transform);
        vpTransform.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        vpTransform.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
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
    
    public void setCameraOutput(Canvas3D canvas) {
        canvas.addMouseListener(this);
        canvas.addMouseMotionListener(this);
        canvas.addMouseWheelListener(this);
        canvas.requestFocus();
        vpTransform.setTransform(transform);
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
    
    public void interpolateToPoint(Vector3f position) {
        transform.setTranslation(position);
        
        Vector3f movement = new Vector3f();
        movement.z += TARGET_DISTANCE;

        Transform3D translation = new Transform3D();
        translation.setTranslation(movement);
        transform.mul(translation);
        setTransform(transform);
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
                mouse.x = 1;
                robot.mouseMove(me.getXOnScreen() - me.getComponent().getWidth() + 1, me.getYOnScreen());
            } else if (me.getX() < 0) {
                mouse.x = me.getComponent().getWidth() - 1;
                robot.mouseMove(me.getXOnScreen() + me.getComponent().getWidth() - 1, me.getYOnScreen());
            }
            if (me.getY() > me.getComponent().getHeight()) {
                mouse.y = 1;
                robot.mouseMove(me.getXOnScreen(), me.getYOnScreen() - me.getComponent().getHeight() + 1);
            } else if (me.getY() < 0) {
                mouse.y = me.getComponent().getHeight() - 1;
                robot.mouseMove(me.getXOnScreen(), me.getYOnScreen() + me.getComponent().getHeight() - 1);
            }
        }
    }
    
    @Override
    public void mouseDragged(MouseEvent me) {
        if (SwingUtilities.isRightMouseButton(me)) {
            robotMouseMove(me);
            Transform3D trRotX = new Transform3D();
            Transform3D trRotY = new Transform3D();
            Transform3D trRotZ = new Transform3D();
            
            trRotX.rotX((mouse.y - me.getY()) / 360f);
            trRotY.rotY((mouse.x - me.getX()) / 360f);
            trRotZ.rotZ(0);
            
            transform.mul(trRotX);
            transform.mul(trRotY);
            transform.mul(trRotZ);
            
            setTransform(transform);
            
            mouse = new Point2i(me.getX(), me.getY());
        }
        
        if (SwingUtilities.isMiddleMouseButton(me)) {
            robotMouseMove(me);
            Vector3f movement = new Vector3f();
            movement.x -= (me.getX() - mouse.x) / 36f;
            movement.y += (me.getY() - mouse.y) / 36f;
            
            Transform3D translation = new Transform3D();
            translation.setTranslation(movement);
            transform.mul(translation);
            setTransform(transform);
            
            mouse = new Point2i(me.getX(), me.getY());
        }
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
                System.out.println("Nothing Picked!");
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
