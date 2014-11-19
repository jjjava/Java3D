package teste;

import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.geometry.Cylinder;
import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.universe.SimpleUniverse;
import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import javax.media.j3d.*;
import javax.vecmath.Vector3f;

public class Body extends Applet {

    public Body() {

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

        Frame frame = new MainFrame(new Body(), 256, 256);
        frame.setLocationRelativeTo(null);
    }

    private BranchGroup createSceneGraph() {

        BranchGroup objRoot = new BranchGroup();

        Appearance red = new Appearance();
        red.setColoringAttributes(new ColoringAttributes(1.0f, 0.0f, 0.0f, 10));
        
        Appearance blue = new Appearance();
        blue.setColoringAttributes(new ColoringAttributes(0.0f, 0.0f, 1.0f, 10));
        
        Appearance green = new Appearance();
        green.setColoringAttributes(new ColoringAttributes(0.0f, 1.0f, 0.0f, 10));

        Transform3D translate_top = new Transform3D();
        translate_top.setTranslation(new Vector3f(0.0f, 0.3f, 0.0f));
        
        Transform3D translate_midi = new Transform3D();
        translate_midi.setTranslation(new Vector3f(0.0f, 0.2f, 0.0f));
        
        Transform3D translate_base = new Transform3D();
        translate_base.setTranslation(new Vector3f(0.0f, 0.0f, 0.0f));

        
        TransformGroup objTG_top = new TransformGroup(translate_top);
        TransformGroup objTG_midi = new TransformGroup(translate_midi);
        TransformGroup objTG_base = new TransformGroup(translate_base);
        
        
        
        objTG_top.addChild(new Sphere(0.1f,red));
        objTG_midi.addChild(new Sphere(0.2f,blue));
        objTG_base.addChild(new Sphere(0.3f,green));
        
        objRoot.addChild(objTG_top);
        objRoot.addChild(objTG_midi);
        objRoot.addChild(objTG_base);
        

        // Let Java 3D perform optimizations on this scene graph.
        objRoot.compile();

        return objRoot;
    }
}