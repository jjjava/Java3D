
package teste;

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
public class Planet extends Applet {

    public Planet() {

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

        Frame frame = new MainFrame(new Planet(), 256, 256);
        frame.setLocationRelativeTo(null);
    }

    private BranchGroup createSceneGraph() {

        BranchGroup objRoot = new BranchGroup();
        
        
         
        TransparencyAttributes t_attr = new TransparencyAttributes(TransparencyAttributes.BLENDED,0.9f,
                TransparencyAttributes.BLEND_SRC_ALPHA,

        TransparencyAttributes.BLEND_ONE);
        Appearance app = new Appearance();
        app.setTransparencyAttributes( t_attr ); 
        
        
        
        
        objRoot.addChild(new Sphere(0.3f,app));

        // Create the transform group node and initialize it to the
        // identity.  Enable the TRANSFORM_WRITE capability so that
        // our behavior code can modify it at runtime.  Add it to the
        // root of the subgraph.
        TransformGroup objSpin = new TransformGroup();
        objSpin.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

        // Create a new Behavior object that will perform the desired
        // operation on the specified transform object and add it into
        // the scene graph.
        Transform3D yAxis = new Transform3D();
        Alpha rotationAlpha = new Alpha(-1, 4000);

        RotationInterpolator rotator =
                new RotationInterpolator(rotationAlpha, objSpin);
        BoundingSphere bounds =
                new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 100.0);
        rotator.setSchedulingBounds(bounds);

        Color3f light1Color = new Color3f(1.8f, 0.1f, 0.1f);
        Vector3f light1Direction = new Vector3f(4.0f, -7.0f, -12.0f);
        DirectionalLight light1 = new DirectionalLight(light1Color, light1Direction);
        light1.setInfluencingBounds(bounds);
        objRoot.addChild(light1);
        
        
       
        

        Transform3D trans = new Transform3D();
        trans.set(new Vector3f(0.5f, 0.0f, 0.0f));
        TransformGroup objTrans = new TransformGroup(trans);
        objRoot.addChild(objSpin);
        objSpin.addChild(objTrans);
        objSpin.addChild(rotator);
        objTrans.addChild(new Sphere(0.1f));

        // Let Java 3D perform optimizations on this scene graph.
        objRoot.compile();

        return objRoot;
    }
}