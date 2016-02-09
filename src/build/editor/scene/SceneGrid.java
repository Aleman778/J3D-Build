package build.editor.scene;

import javax.media.j3d.Appearance;
import javax.media.j3d.ColoringAttributes;
import javax.media.j3d.PolygonAttributes;
import javax.media.j3d.QuadArray;
import javax.media.j3d.Shape3D;
import javax.vecmath.Point3d;

public class SceneGrid extends Shape3D {

    private static final int GRID_SIZE = 10;
    
    public SceneGrid() {
        QuadArray quad = new QuadArray(GRID_SIZE * GRID_SIZE * 2 * 4, QuadArray.COORDINATES);
        int index = 0;
        for (int x = -GRID_SIZE / 2; x < GRID_SIZE / 2; x++) {
            for (int z = -GRID_SIZE / 2; z < GRID_SIZE / 2; z++) {
                quad.setCoordinate(index++, new Point3d(x, 0, z));
                quad.setCoordinate(index++, new Point3d(x, 0, z + 1));
                quad.setCoordinate(index++, new Point3d(x + 1, 0, z + 1));
                quad.setCoordinate(index++, new Point3d(x + 1, 0, z));
            }
        }
        for (int x = -GRID_SIZE / 2; x < GRID_SIZE / 2; x++) {
            for (int z = -GRID_SIZE / 2; z < GRID_SIZE / 2; z++) {
                quad.setCoordinate(index++, new Point3d(x * GRID_SIZE - GRID_SIZE / 2, 0, GRID_SIZE - GRID_SIZE / 2));
                quad.setCoordinate(index++, new Point3d(x * GRID_SIZE - GRID_SIZE / 2, 0, z * GRID_SIZE + GRID_SIZE - GRID_SIZE / 2));
                quad.setCoordinate(index++, new Point3d(x * GRID_SIZE + GRID_SIZE - GRID_SIZE / 2, 0, z * GRID_SIZE + GRID_SIZE - GRID_SIZE / 2));
                quad.setCoordinate(index++, new Point3d(x * GRID_SIZE + GRID_SIZE - GRID_SIZE / 2, 0, GRID_SIZE - GRID_SIZE / 2));
            }
        }
        
        Appearance apperance = new Appearance();
        ColoringAttributes colorAttri = new ColoringAttributes();
        colorAttri.setColor(0.8f, 0.8f, 0.8f);
        apperance.setColoringAttributes(colorAttri);
        
        PolygonAttributes polygonAttri = new PolygonAttributes();
        polygonAttri.setPolygonMode(PolygonAttributes.POLYGON_LINE);
        polygonAttri.setCullFace(PolygonAttributes.CULL_NONE);
        apperance.setPolygonAttributes(polygonAttri);
        
        setGeometry(quad);
        setAppearance(apperance);
        setPickable(false);
    }
}
