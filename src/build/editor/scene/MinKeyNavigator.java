package build.editor.scene;

/* Exempel fr�n JAVA fr�n A till O. Copyright Niklas Gyulai: JavAtillO och Eklelunds F�rlag
*/
import com.sun.j3d.utils.universe.*; 
import java.awt.event.*;
import javax.media.j3d.*;
import javax.vecmath.*;

// Detta tangentbordbeteende ger anv�ndaren knappar f�r fram, rotation, 
// �t v�nster, �t h�ger, upp och ned. 
public class MinKeyNavigator extends BranchGroup implements Runnable, KeyListener {
    
    // den grupp som beteendet ska styra
    private TransformGroup viewTransform;

    // flaggor f�r tangentbordet
    private boolean leftDown=false, rightDown=false, upDown=false, downDown= false;
    private boolean AltDown=false, PgUpDown= false, PgDownDown=false;

    public MinKeyNavigator( SimpleUniverse universum){
        super(); // BranchGroup-konstruktorn
        universum.addBranchGraph( this); // h�ng p� oss p� universum
        // h�mta den transform som styr kamerans position
        viewTransform= universum.getViewingPlatform().getViewPlatformTransform();

        // Ge universums 3D-duk fokus och anm�l att vi vill lyssna p� tangentbordet
        Canvas3D c3d= universum.getCanvas();
        c3d.addKeyListener( this);
        c3d.requestFocus();

        // Skapa en ny tr�d och starta beteendet
        Thread t= new Thread( this);
        t.start();

    }

    public void run(){
        // skalfaktor f�r f�rflyttning
        float skala= 0.1f, dFiY=0.01f;
        // vektorer som pekar ut fram, �t h�ger och upp f�r beteendet
        Vector3f v_fwd= new Vector3f( 0,0,-skala);
        Vector3f v_right= new Vector3f( -skala,0,0);
        Vector3f v_up= new Vector3f( 0,-skala,0);
        // Aktuell position: B�rja 10 m framf�r origo
        Vector3f position= new Vector3f( 0,0.8f,10); 
        // rotationsvinkel i sida
        double fiY= 0, fiXZ=0;
        // Transformation f�r translationen och rotationen
        Transform3D t3d1= new Transform3D();
        Transform3D t3d2= new Transform3D();

        while( true)
        {
            if( AltDown){ // glid i sidled
                if( leftDown) position.add( v_right);
                if( rightDown) position.sub( v_right);
            } else {      // rotera kameran
                if( leftDown || rightDown){
                    if( leftDown)  fiY+= dFiY;
                    if( rightDown) fiY-= dFiY;
                    v_fwd.x= -(float) Math.sin( fiY)*skala;
                    v_fwd.z= -(float) Math.cos( fiY)*skala;
                    v_right.x=  v_fwd.z;
                    v_right.z= -v_fwd.x;
                }
            }
                
            if( upDown)   position.add( v_fwd);
            if( downDown) position.sub( v_fwd);
            
            if( AltDown){ // glid i h�jdled
                if( PgUpDown)   position.sub( v_up);
                if( PgDownDown) position.add( v_up);
            } else {      // rotera i h�jdled
                if( PgUpDown)   fiXZ+= 0.02;
                if( PgDownDown) fiXZ-= 0.02;
            }

            // Modifiera transformen och kopiera dess v�rde till kamerans transform
            t3d1.rotX( fiXZ);
            t3d2.rotY( fiY);
            t3d2.mul( t3d1);
            t3d2.setTranslation( position);
            viewTransform.setTransform( t3d2);

            try {Thread.sleep(5);} catch( InterruptedException ie){}
        }
    }

    public void keyTyped( KeyEvent ke){}

    public void keyPressed( KeyEvent ke){
        int keycode= ke.getKeyCode();
        if( keycode == KeyEvent.VK_ESCAPE) System.exit(0);
        else if( keycode == KeyEvent.VK_LEFT) leftDown= true;
        else if( keycode == KeyEvent.VK_RIGHT) rightDown= true;
        else if( keycode == KeyEvent.VK_UP) upDown= true;
        else if( keycode == KeyEvent.VK_DOWN) downDown= true;
        else if( keycode == KeyEvent.VK_ALT) AltDown= true;
        else if( keycode == KeyEvent.VK_PAGE_UP) PgUpDown= true;
        else if( keycode == KeyEvent.VK_PAGE_DOWN) PgDownDown= true;
    }

    public void keyReleased( KeyEvent ke){
        int keycode= ke.getKeyCode();
        if( keycode == KeyEvent.VK_LEFT) leftDown= false; 
        else if( keycode == KeyEvent.VK_RIGHT) rightDown= false;
        else if( keycode == KeyEvent.VK_UP) upDown= false; 
        else if( keycode == KeyEvent.VK_DOWN) downDown= false; 
        else if( keycode == KeyEvent.VK_ALT) AltDown= false;
        else if( keycode == KeyEvent.VK_PAGE_UP) PgUpDown= false;
        else if( keycode == KeyEvent.VK_PAGE_DOWN) PgDownDown= false;
    }
}
