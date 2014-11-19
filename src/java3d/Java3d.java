/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package java3d;

import com.sun.j3d.utils.geometry.ColorCube;
import com.sun.j3d.utils.universe.SimpleUniverse;
import java.awt.BorderLayout;
import java.awt.GraphicsConfiguration;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.swing.JFrame;

/**
 *
 * @author bp12078
 */
public class Java3d extends JFrame {

    public Java3d() {
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

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
       Java3d j3d = new Java3d();
       j3d.setTitle("Jrame exemplo 1");
       j3d.setSize(512, 512);
       j3d.setResizable(false);
       j3d.setLocationRelativeTo(null);
       j3d.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       j3d.setVisible(true);
    }

    private BranchGroup createSceneGraph() {
        // Create the root of the branch graph
        BranchGroup objRoot = new BranchGroup();

        objRoot.addChild(new ColorCube(0.4));

        return objRoot;
    }
}
