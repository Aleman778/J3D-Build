package j3dbuild.editor.scene;

import j3dbuild.editor.scene.graph.SceneGraph;
import j3dbuild.editor.ui.JTreeSceneGraph;
import j3dbuild.editor.ui.TransformUtility;
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
import javax.media.j3d.Shape3D;
import javax.media.j3d.Transform3D;
import javax.swing.SwingUtilities;
import javax.vecmath.Point2i;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;

public class SceneView extends UniverseView implements MouseListener, MouseMotionListener, MouseWheelListener {

    public static final float ZOOM_SENSIBILITY = 6f;
    public static final float ROTATE_SENSIBILITY = 6f;
    public static final float TARGET_DISTANCE = 18f;
    
    private SceneGraph graph = null;
    private TransformUtility transformutility = null;
    private Robot robot = null;
    private PickCanvas pickCanvas = null;
    private Point2i mouse;
    private double rotX, rotY;
    
    public void setSceneGraph(SceneGraph node) {
        graph = node;
        rotX = 0;
        rotY = 0;
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
    
    public void setTransformUtility(TransformUtility utility) {
        transformutility = utility;
    }
    
    public void updateTransformUtility() {
        if (transformutility != null) {
            Vector3f translation = new Vector3f();
            transform.get(translation);

            transformutility.update(translation);
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
            
            rotX += Math.toRadians(mouse.y - me.getY()) / ROTATE_SENSIBILITY;
            rotY += Math.toRadians(mouse.x - me.getX()) / ROTATE_SENSIBILITY;
       
            Vector3f translation = new Vector3f();
            transform.get(translation);
            
            transform.setEuler(new Vector3d(rotX, rotY, 0));
            transform.setTranslation(translation);
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
            
            updateTransformUtility();
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
                if (graph != null && !me.isControlDown()) {
                    graph.clearSelectedNodes();
                }
            } else {
                Primitive primitive = (Primitive) result.getNode(PickResult.PRIMITIVE);
                Shape3D shape3D = (Shape3D) result.getNode(PickResult.SHAPE3D);
                if (graph != null) {
                    if (primitive != null) {
                        handlePickResults(primitive, me);
                    } else if (shape3D != null) {
                        handlePickResults(shape3D, me);
                    }
                }
            }
        }
    }
    
    private void handlePickResults(Node node, MouseEvent me) {
        if (SwingUtilities.isLeftMouseButton(me)) {
            if (me.isControlDown()) {
                if (graph.isObjectSelected(node)) {
                    graph.removeSelectionObject(node);
                } else {
                    graph.addSelectionObject(node);
                }
            } else {
                graph.setSelectionObject(node);
            }
        } else if (SwingUtilities.isRightMouseButton(me)) {
            graph.setSelectionObject(node);
            JTreeSceneGraph.showPopupMenu(graph.findObject(node), me);
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
        
        updateTransformUtility();
    }

    @Override
    public void repaint() {
        super.repaint();
    }
}
