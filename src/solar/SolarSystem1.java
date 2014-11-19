package solar;

import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.geometry.ColorCube;
import com.sun.j3d.utils.universe.SimpleUniverse;
import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import javax.media.j3d.*;
import javax.vecmath.AxisAngle4d;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3f;

/**
 *
 * @author Hudson Schumaker
 */
public class SolarSystem1 extends Applet {

    public static void main(String[] args) {
        Frame frame = new MainFrame(new SolarSystem1(), 640, 480);
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
        BoundingSphere bounds = new BoundingSphere();
        background.setApplicationBounds(bounds);
        root.addChild(background);
        Shape3D shape;
        Appearance ap = new Appearance();
        PolygonAttributes pa = new PolygonAttributes();
        ap.setPolygonAttributes(pa);
        ColoringAttributes ca = new ColoringAttributes(0f, 0f, 0f, ColoringAttributes.SHADE_FLAT);
        ap.setColoringAttributes(ca);

        LineArray axis = new LineArray(2, LineArray.COORDINATES);
        axis.setCoordinate(0, new Point3d(-0.8, -0.8, -0.8));
        axis.setCoordinate(1, new Point3d(0.5, 0.5, 0.5));

        Shape3D axisG = new Shape3D(axis, ap);
        root.addChild(axisG);



        Alpha rotationAlpha = new Alpha(-1, 8000);
        BoundingSphere bounds2[] = new BoundingSphere[8];

        for (int k = 0; k < 8; k++) {
            bounds2[k] = new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 100.0);
        }

        Transform3D tr[] = new Transform3D[8];
        for (int k = 0; k < 8; k++) {
            tr[k] = new Transform3D();
            tr[k].setTranslation(new Vector3f(-0.5f, 0f, 0f));
            tr[k].setScale(0.1);
        }
        TransformGroup tg[] = new TransformGroup[8];
        for (int k = 0; k < 8; k++) {
            tg[k] = new TransformGroup();
            tg[k].setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
            tg[k].setTransform(tr[k]);
        }
        TransformGroup rot[] = new TransformGroup[8];
        for (int k = 0; k < 8; k++) {
            rot[k] = new TransformGroup();
            rot[k].setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        }
        Transform3D trRot[] = new Transform3D[8];
        for (int k = 0; k < 8; k++) {
            trRot[k] = new Transform3D();
        }


        for (int i = 0; i < 8; i++) {
            shape = new ColorCube();
            shape.setAppearance(ap);

            trRot[i].set(new AxisAngle4d(0.5, 0.5, 0.5, Math.PI / 4 * i));
            rot[i].setTransform(trRot[i]);

            root.addChild(rot[i]);
            rot[i].addChild(tg[i]);
            tg[i].addChild(shape);
        }

        RotationInterpolator rotator[] = new RotationInterpolator[8];

        Transform3D yAxis[] = new Transform3D[8];
        for (int k = 0; k < 8; k++) {
            yAxis[k] = new Transform3D();
            yAxis[k].set(trRot[k]);
            yAxis[k].rotY((Math.PI / 2.0d));
        }




        for (int k = 0; k < 3; k++) {

             trRot[k].mul(yAxis[k]);
           // yAxis[k].mul(trRot[k]);
            rotator[k] = new RotationInterpolator(rotationAlpha, rot[k], trRot[k],
                    0.0f, (float) Math.PI * 2.0f);
            rotator[k].setSchedulingBounds(bounds2[k]);
            rot[k].addChild(rotator[k]);
        }






        return root;
    }
}
