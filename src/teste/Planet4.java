/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package teste;

import ch2.Axis;
import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.geometry.ColorCube;
import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.universe.SimpleUniverse;
import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import javax.media.j3d.*;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3f;

public class Planet4 extends Applet {

    public Planet4() {
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
        
        Appearance hud_app = new Appearance();
        hud_app.setColoringAttributes(new ColoringAttributes(0.0f, 0.0f, 1.0f, 1));
        
        Appearance sales_app = new Appearance();
        sales_app.setColoringAttributes(new ColoringAttributes(1.0f, 0.0f, 0.0f, 1));
        
        Appearance schum_app = new Appearance();
        schum_app.setColoringAttributes(new ColoringAttributes(0.0f, 1.0f, 0.0f, 1));
        
        TransformGroup objSpin_hud = new TransformGroup();
        objSpin_hud.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

        TransformGroup objSpin_sales = new TransformGroup();
        objSpin_sales.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        
        TransformGroup objSpin_schum = new TransformGroup();
        objSpin_schum.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);


        BoundingSphere bounds =
                new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 100.0);
        
        
        Transform3D yAxis = new Transform3D();
        
        Alpha rotationAlpha = new Alpha(-1, 8000);
        Alpha rotationAlpha2 = new Alpha(-1, 6000);

        RotationInterpolator rotator_sales =
                new RotationInterpolator(rotationAlpha, objSpin_sales);
        rotator_sales.setSchedulingBounds(bounds);

        Transform3D trans_sales = new Transform3D();
        trans_sales.set(new Vector3f(0.8f, -0.3f, 0.0f));
        TransformGroup objTrans_sales = new TransformGroup(trans_sales);
        objRoot.addChild(objSpin_sales);
        objSpin_sales.addChild(objTrans_sales);
        objSpin_sales.addChild(rotator_sales);
        objTrans_sales.addChild(new Sphere(0.1f, sales_app));
        
        
        RotationInterpolator rotator_schum =
                new RotationInterpolator(rotationAlpha, objSpin_schum);
        rotator_schum.setSchedulingBounds(bounds);

        Transform3D trans_schum = new Transform3D();
        trans_schum.set(new Vector3f(-0.8f, 0.3f, 0.0f));
        TransformGroup objTrans_schum = new TransformGroup(trans_schum);
        objRoot.addChild(objSpin_schum);
        objSpin_schum.addChild(objTrans_schum);
        objSpin_schum.addChild(rotator_schum);
        objTrans_schum.addChild(new Sphere(0.1f, schum_app));
        
        
        
        objSpin_hud.addChild(new Sphere(0.5f, hud_app));
        RotationInterpolator rotator_hud=
                new RotationInterpolator(rotationAlpha2, objSpin_hud);
        
        rotator_hud.setSchedulingBounds(bounds);
        objSpin_hud.addChild(rotator_hud);
        objRoot.addChild(objSpin_hud);

        // Let Java 3D perform optimizations on this scene graph.
        objRoot.compile();

        return objRoot;
    }

    public static void main(String args[]) {

        Frame frame = new MainFrame(new Planet4(), 256, 256);
        frame.setLocationRelativeTo(null);
    }
}
