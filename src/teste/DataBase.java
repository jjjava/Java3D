package teste;

import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.geometry.Cone;
import com.sun.j3d.utils.geometry.Cylinder;
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
 * @author Hudson Schumaker
 */
public class DataBase extends Applet {

    public DataBase() {

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

    public static void main(String args[]) {

        Frame frame = new MainFrame(new DataBase(), 256, 256);
        frame.setLocationRelativeTo(null);
    }

    private BranchGroup createSceneGraph() {

        BranchGroup objRoot = new BranchGroup();

        Appearance app = new Appearance();
        app.setColoringAttributes(new ColoringAttributes(1.0f,0.0f,1.0f,10));

        Transform3D rotate = new Transform3D();
        rotate.rotX(Math.PI/4.0d);
        
        TransformGroup objTG = new TransformGroup(rotate);
        objTG.addChild(new Cylinder(0.3f,0.5f, app));
        objRoot.addChild(objTG);

        // Let Java 3D perform optimizations on this scene graph.
        objRoot.compile();

        return objRoot;
    }
}