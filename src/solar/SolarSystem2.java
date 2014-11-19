package solar;

import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.universe.SimpleUniverse;
import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import javax.media.j3d.*;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3f;

/**
 *
 * @author Hudson Schumaker
 */
public class SolarSystem2 extends Applet {

    public static void main(String[] args) {
        Frame frame = new MainFrame(new SolarSystem2(), 640, 480);
        frame.setLocationRelativeTo(null);
    }

    @Override
    public void init() {
        // create canvas
        GraphicsConfiguration gc = SimpleUniverse.getPreferredConfiguration();
        Canvas3D cv = new Canvas3D(gc);
        setLayout(new BorderLayout());
        add(cv, BorderLayout.CENTER);
        BranchGroup bg = createSceneGraph();
        bg.compile();
        SimpleUniverse su = new SimpleUniverse(cv);
        su.getViewingPlatform().setNominalViewingTransform();
        su.addBranchGraph(bg);
    }

    private BranchGroup createSceneGraph() {
        BranchGroup root = new BranchGroup();
        Background background = new Background(1.0f, 1.0f, 1.0f);
        BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 100.0);
        background.setApplicationBounds(bounds);
        root.addChild(background);

        Appearance app_sol = new Appearance();
        ColoringAttributes ca_sol = new ColoringAttributes(1f, 0f, 0f, ColoringAttributes.FASTEST);
        app_sol.setColoringAttributes(ca_sol);

        Appearance app_mer = new Appearance();
        ColoringAttributes ca_mer = new ColoringAttributes(0f, 1f, 0f, ColoringAttributes.FASTEST);
        app_mer.setColoringAttributes(ca_mer);

        Appearance app_ven = new Appearance();
        ColoringAttributes ca_ven = new ColoringAttributes(0f, 0f, 1f, ColoringAttributes.FASTEST);
        app_ven.setColoringAttributes(ca_ven);


        TransformGroup sol = new TransformGroup();

        sol.addChild(new Sphere(0.2f, app_sol));

        TransformGroup mer = new TransformGroup();
        mer.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        mer.addChild(new Sphere(0.1f, app_mer));


        Transform3D tr3d_mer = new Transform3D();
        tr3d_mer.setTranslation(new Vector3f(0.5f, 0.0f, -2.0f));
        Transform3D tr3d_mer2 = new Transform3D();
        
        tr3d_mer2.rotY(Math.PI / 2);
        
        tr3d_mer.mul(tr3d_mer2);
        mer.setTransform(tr3d_mer);
        Alpha rotation_mer = new Alpha(-1, 4000);
        RotationInterpolator rotator = new RotationInterpolator(rotation_mer, mer, tr3d_mer, 0.0f, (float) Math.PI * 2.0f);
        rotator.setSchedulingBounds(bounds);
        mer.addChild(rotator);
        TransformGroup ven = new TransformGroup();
        ven.addChild(new Sphere(0.1f, app_ven));

       // root.addChild(sol);
        root.addChild(mer);
        //   root.addChild(ven);

        return root;
    }
}
