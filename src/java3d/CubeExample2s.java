package java3d;

import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.geometry.ColorCube;
import com.sun.j3d.utils.universe.SimpleUniverse;
import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import javax.media.j3d.*;
import javax.vecmath.Point3d;
import teste.Planet2;

/**
 *
 * @authorHudson Schumaker
 */
public class CubeExample2s extends Applet {

    public CubeExample2s() {
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

        Frame frame = new MainFrame(new CubeExample2s(), 256, 256);
        frame.setLocationRelativeTo(null);
    }

    private BranchGroup createSceneGraph() {
        BranchGroup objRoot = new BranchGroup();
        Transform3D rotate = new Transform3D();
        Transform3D tempRotate = new Transform3D();
        rotate.rotX(Math.PI / 4.0d);
        tempRotate.rotY(Math.PI / 5.0d);
        rotate.mul(tempRotate);
        TransformGroup objRotate = new TransformGroup(rotate);
//Spinning starts here
        TransformGroup objSpin = new TransformGroup();
        objSpin.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        objRoot.addChild(objRotate);
        objRotate.addChild(objSpin);
        objSpin.addChild(new ColorCube(0.4));
        Transform3D yAxis = new Transform3D();
        Transform3D xAxis = new Transform3D();
        xAxis.rotZ(-Math.PI / 4.0f);
        Alpha rotationAlpha = new Alpha(-1, 4000);
        Alpha rotationAlpha2 = new Alpha(-1, 2000);
        RotationInterpolator rotatorY =
                new RotationInterpolator(rotationAlpha, objSpin, yAxis,
                0.0f, (float) Math.PI * 2.0f);
        RotationInterpolator rotatorX =
                new RotationInterpolator(rotationAlpha2, objSpin, xAxis,
                0.0f, (float) Math.PI * 2.0f);
        BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 100.0);
        rotatorY.setSchedulingBounds(bounds);
        rotatorX.setSchedulingBounds(bounds);
        objSpin.addChild(rotatorY);
        objSpin.addChild(rotatorX);
        return objRoot;
    }
}
