package teste;

import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.geometry.ColorCube;
import com.sun.j3d.utils.universe.SimpleUniverse;
import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import javax.media.j3d.*;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3f;

public class Planet5 extends Applet {

    public Planet5() {
        setLayout(new BorderLayout());
        GraphicsConfiguration config =
                SimpleUniverse.getPreferredConfiguration();
        Canvas3D canvas3D = new Canvas3D(config);
        add("Center", canvas3D);
        BranchGroup scene = createSceneGraph();
        SimpleUniverse simpleU = new SimpleUniverse(canvas3D);
        simpleU.getViewingPlatform().setNominalViewingTransform();
        simpleU.addBranchGraph(scene);
    }

    private BranchGroup createSceneGraph() {

        BranchGroup objRoot = new BranchGroup();

        TransformGroup objSpin_hud = new TransformGroup();
        objSpin_hud.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

        TransformGroup objSpin_luiz = new TransformGroup();
        objSpin_luiz.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

        TransformGroup objSpin_sales = new TransformGroup();
        objSpin_sales.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

        TransformGroup objSpin_schum = new TransformGroup();
        objSpin_schum.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);


        BoundingSphere bounds =
                new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 100.0);
        BoundingSphere bounds2 =
                new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 100.0);

        Transform3D yAxis = new Transform3D();

        Alpha rotationAlpha = new Alpha(-1, 8000);
        Alpha rotationAlpha2 = new Alpha(-1, 2000);
        Alpha rotationAlpha3 = new Alpha(-1, 4000);

        RotationInterpolator rotator_sales =
                new RotationInterpolator(rotationAlpha, objSpin_sales);
        rotator_sales.setSchedulingBounds(bounds);

        Transform3D trans_sales = new Transform3D();
        trans_sales.set(new Vector3f(0.8f, -0.3f, 0.0f));
        TransformGroup objTrans_sales = new TransformGroup(trans_sales);
       // objRoot.addChild(objSpin_sales);
        objSpin_sales.addChild(objTrans_sales);
        objSpin_sales.addChild(rotator_sales);
        objTrans_sales.addChild(new ColorCube(0.1f));


        RotationInterpolator rotator_schum =
                new RotationInterpolator(rotationAlpha, objSpin_schum, yAxis, 0.0f, -(float) Math.PI * 2.0f);
        rotator_schum.setSchedulingBounds(bounds);

        Transform3D trans_schum = new Transform3D();
        trans_schum.set(new Vector3f(-0.8f, 0.3f, 0.0f));
        TransformGroup objTrans_schum = new TransformGroup(trans_schum);
       // objRoot.addChild(objSpin_schum);
        objSpin_schum.addChild(objTrans_schum);
        objSpin_schum.addChild(rotator_schum);
        objTrans_schum.addChild(new ColorCube(0.1f));


        RotationInterpolator rotator_luiz =
                new RotationInterpolator(rotationAlpha3, objSpin_luiz, yAxis, 0.0f, -(float) Math.PI * 2.0f);
        rotator_luiz.setSchedulingBounds(bounds);

        Transform3D trans_luiz = new Transform3D();
        trans_luiz.set(new Vector3f(0.8f, 0.5f, 0.0f));
        TransformGroup objTrans_luiz = new TransformGroup(trans_luiz);
      //  objRoot.addChild(objSpin_luiz);
        objSpin_luiz.addChild(objTrans_luiz);
        objSpin_luiz.addChild(rotator_luiz);
        objTrans_luiz.addChild(new ColorCube(0.1f));


        objSpin_hud.addChild(new ColorCube(0.3f));
        RotationInterpolator rotator_hud =
                new RotationInterpolator(rotationAlpha2, objSpin_hud, yAxis,
                0.0f, -(float) Math.PI * 2.0f);

        rotator_hud.setSchedulingBounds(bounds2);
        objSpin_hud.addChild(rotator_hud);
        objRoot.addChild(objSpin_hud);

        objRoot.compile();

        return objRoot;
    }

    public static void main(String args[]) {

        Frame frame = new MainFrame(new Planet5(), 256, 256);
        frame.setLocationRelativeTo(null);
    }
}
