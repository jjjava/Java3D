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

public class Planet3 extends Applet {

    public Planet3() {
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

    private BranchGroup createSceneGraph() {
        BranchGroup objRoot = new BranchGroup();
         BoundingSphere bounds =
                new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 100.0);
        
        Color3f light1Color = new Color3f(1.8f, 0.1f, 0.1f);
        Vector3f light1Direction = new Vector3f(4.0f, -7.0f, -12.0f);
        DirectionalLight light1 = new DirectionalLight(light1Color, light1Direction);
        light1.setInfluencingBounds(bounds);
        objRoot.addChild(light1);

       


        Appearance hud_app = new Appearance();
        hud_app.setColoringAttributes(new ColoringAttributes(0.0f, 0.0f, 1.0f, 1));

        Appearance sales_app = new Appearance();
        sales_app.setColoringAttributes(new ColoringAttributes(1.0f, 0.0f, 0.0f, 1));

        Appearance schum_app = new Appearance();
        schum_app.setColoringAttributes(new ColoringAttributes(0.0f, 1.0f, 0.0f, 1));

        Sphere hud = new Sphere(0.4f, hud_app);
        Sphere sales = new Sphere(0.1f, sales_app);
        Sphere schum = new Sphere(0.1f, schum_app);


        TransformGroup tg_hud = new TransformGroup();
        tg_hud.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        tg_hud.addChild(hud);

        TransformGroup tg_sales = new TransformGroup();
        tg_sales.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
       

        TransformGroup tg_sales_spin = new TransformGroup();
        tg_sales_spin.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
         tg_sales_spin.addChild(sales);
        
        TransformGroup tg_schum = new TransformGroup();
        tg_schum.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        tg_schum.addChild(schum);
        
        


        Transform3D t3d_hud = new Transform3D();

        Transform3D t3d_sales_translate = new Transform3D();
        t3d_sales_translate.setTranslation(new Vector3f(0.8f, 0.0f, 0.0f));
        Transform3D t3d_sales_rotate = new Transform3D();
        t3d_sales_rotate.rotX(Math.PI/4.0d);
        t3d_sales_translate.mul(t3d_sales_rotate);
        tg_sales.setTransform(t3d_sales_translate);


        Transform3D t3d_schum = new Transform3D();
        t3d_schum.setTranslation(new Vector3f(-0.8f, 0.0f, 0.0f));
        tg_schum.setTransform(t3d_schum);



        Alpha rotationAlpha = new Alpha(-1, 4000);

        RotationInterpolator rotator =
	    new RotationInterpolator(rotationAlpha, tg_sales_spin);
        rotator.setSchedulingBounds(bounds);
      
        tg_sales_spin.addChild(rotator);
        objRoot.addChild(tg_hud);
        objRoot.addChild(tg_sales);
        tg_sales.addChild(tg_sales_spin);
        objRoot.addChild(tg_schum);
        objRoot.compile();

        return objRoot;
    }

    public static void main(String args[]) {

        Frame frame = new MainFrame(new Planet3(), 256, 256);
        frame.setLocationRelativeTo(null);
    }
}
