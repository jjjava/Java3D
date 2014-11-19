
package teste;

/**
 *
 * @author Hudson Schumaker
 */
import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.universe.SimpleUniverse;
import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import javax.media.j3d.*;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3f;

/**
 *
 * @author Hudson
 */
public class Planet2 extends Applet {

    public Planet2() {

        setLayout(new BorderLayout());
        GraphicsConfiguration config =
                SimpleUniverse.getPreferredConfiguration();

        Canvas3D canvas3D = new Canvas3D(config);
        add("Center", canvas3D);

        BranchGroup scene = createSceneGraph();

        // SimpleUniverse is a Convenience Utility class
        SimpleUniverse simpleU = new SimpleUniverse(canvas3D);

        // This will move the ViewPlatform back a bit so the
        // objects in the scene can be viewed.
        simpleU.getViewingPlatform().setNominalViewingTransform();

        simpleU.addBranchGraph(scene);
    }

    public static void main(String args[]) {

        Frame frame = new MainFrame(new Planet2(), 256, 256);
        frame.setLocationRelativeTo(null);
    }

    private BranchGroup createSceneGraph() {

        BranchGroup objRoot = new BranchGroup();
        
        BoundingSphere bounds =
                new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 100.0);
        
        Color3f light1Color = new Color3f(1.8f, 0.1f, 0.1f);
        Vector3f light1Direction = new Vector3f(4.0f, -7.0f, -12.0f);
        DirectionalLight light1 = new DirectionalLight(light1Color, light1Direction);
        light1.setInfluencingBounds(bounds);
        objRoot.addChild(light1);
        
      
        TransformGroup objSpin1 = new TransformGroup();
        objSpin1.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        
        
        
        TransformGroup objSpin2 = new TransformGroup();
        objSpin2.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);


        Alpha rotationAlpha1 = new Alpha(-1, 6000);
        Alpha rotationAlpha2 = new Alpha(-1, 3000);

        RotationInterpolator rotator1 =
                new RotationInterpolator(rotationAlpha2, objSpin1);
        
        rotator1.setSchedulingBounds(bounds);
        
        
        RotationInterpolator rotator2 =
                new RotationInterpolator(rotationAlpha1, objSpin2);
        
        rotator2.setSchedulingBounds(bounds);


        Transform3D trans1 = new Transform3D();
        trans1.rotY(Math.PI/8.0d);
        TransformGroup objTrans1 = new TransformGroup(trans1);
        objRoot.addChild(objSpin1);
        objSpin1.addChild(objTrans1);
        objSpin1.addChild(rotator1);
        objTrans1.addChild(new Sphere(0.3f));

        
        Transform3D trans2 = new Transform3D();
        trans2.set(new Vector3f(0.5f, 0.0f, 0.0f));
        TransformGroup objTrans2 = new TransformGroup(trans2);
        objRoot.addChild(objSpin2);
        objSpin2.addChild(objTrans2);
        objSpin2.addChild(rotator2);
        objTrans2.addChild(new Sphere(0.1f));
        
        
        
        
        
        
        
        // Let Java 3D perform optimizations on this scene graph.
        objRoot.compile();

        return objRoot;
    }
}