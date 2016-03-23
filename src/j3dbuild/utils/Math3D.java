package j3dbuild.utils;

import static java.lang.Math.*;
import javax.vecmath.Matrix3d;
import javax.vecmath.Matrix3f;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;

public class Math3D {
    
    private Math3D() {
        
    }
    
    public static Matrix3f eulerToMatrix3f(Vector3f euler) {
        Matrix3f result;
        Matrix3f rotX = new Matrix3f();
        rotX.setIdentity();
        Matrix3f rotY = new Matrix3f();
        rotY.setIdentity();
        Matrix3f rotZ = new Matrix3f();
        rotZ.setIdentity();
        
        rotX.m11 = (float) cos(euler.x); rotX.m12 = (float) -sin(euler.x);
        rotX.m21 = (float) sin(euler.x); rotX.m22 = (float) cos(euler.x);
        
        rotY.m00 = (float) cos(euler.y); rotY.m02 = (float) sin(euler.y);
        rotY.m20 = (float) -sin(euler.y); rotY.m22 = (float) cos(euler.y);
                
        rotZ.m00 = (float) cos(euler.x); rotZ.m01 = (float) -sin(euler.x);
        rotZ.m10 = (float) sin(euler.x); rotZ.m11 = (float) cos(euler.x);
        
        rotY.mul(rotZ);
        rotX.mul(rotY);
        result = rotX;
        
        return result;
    }
    
    public static void matrix3fToEuler(Matrix3f matrix, Vector3f euler ) {
        Vector3f v3d = new Vector3f();
        
        Vector3f zAxis = new Vector3f( 0, 0, -1 );
        Vector3f yAxis = new Vector3f( 0, 1, 0 );
        Vector3f xAxis = new Vector3f( 1, 0, 0 );

        v3d.set( xAxis );
        matrix.transform( v3d );
        v3d.x = Math.abs( v3d.x );
        v3d.z = 0;
        v3d.normalize();

        euler.x = xAxis.angle( v3d );

        v3d.set( yAxis );
        matrix.transform( v3d );
        v3d.z = Math.abs( v3d.z );
        v3d.x = 0;
        v3d.normalize();

        euler.y = yAxis.angle( v3d );

        v3d.set( zAxis );
        matrix.transform( v3d );
        v3d.y = 0;
        v3d.normalize();

        euler.z = zAxis.angle( v3d );
        if (v3d.x<0)
            euler.z = (float) (2*Math.PI-euler.z);
    }
    
    public static void matrix3dToEuler(Matrix3d matrix, Vector3d euler ) {
        Vector3d v3d = new Vector3d();
        
        Vector3d zAxis = new Vector3d( 0, 0, -1 );
        Vector3d yAxis = new Vector3d( 0, 1, 0 );
        Vector3d xAxis = new Vector3d( 1, 0, 0 );

        v3d.set( xAxis );
        matrix.transform( v3d );
        v3d.x = Math.abs( v3d.x );
        v3d.z = 0;
        v3d.normalize();

        euler.x = xAxis.angle( v3d );

        v3d.set( yAxis );
        matrix.transform( v3d );
        v3d.z = Math.abs( v3d.z );
        v3d.x = 0;
        v3d.normalize();

        euler.y = yAxis.angle( v3d );

        v3d.set( zAxis );
        matrix.transform( v3d );
        v3d.y = 0;
        v3d.normalize();

        euler.z = zAxis.angle( v3d );
        if (v3d.x<0)
            euler.z = 2*Math.PI-euler.z;
    }
}
