/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package java3d;

import com.sun.j3d.utils.universe.SimpleUniverse;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GraphicsConfiguration;
import javax.media.j3d.*;
import javax.swing.JFrame;
import javax.vecmath.Color3f;
import javax.vecmath.Vector3f;

public class Text3DWorld extends JFrame {

    private Transform3D rotate1 = new Transform3D();
    private Transform3D rotate2 = new Transform3D();

    public Text3DWorld() {
        super("Text3DWorld");
        Canvas3D canvas3D = createCanvas3D();
        BranchGroup scene = createSceneGraph();
        connect(canvas3D, scene);
    }

    private Canvas3D createCanvas3D() {
        setSize(300, 300);
        getContentPane().setLayout(new BorderLayout());
        GraphicsConfiguration config =
                SimpleUniverse.getPreferredConfiguration();
        Canvas3D canvas3D = new Canvas3D(config);
        setSize(300, 300);
        getContentPane().add(canvas3D);
        return canvas3D;
    }

    private BranchGroup createSceneGraph() {
        BranchGroup objRoot = new BranchGroup();
        TransformGroup mover = moveTextBack();
        TransformGroup spinner = createSpinner();
        objRoot.addChild(mover);
        mover.addChild(spinner);
        spinner.addChild(createTextShape());
        spinner.addChild(makeSpin(spinner));
        setLighting(mover);
        return objRoot;
    }

    private TransformGroup createSpinner() {
        TransformGroup spinner = new TransformGroup();
        spinner.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        return spinner;
    }

    private TransformGroup moveTextBack() {
        Transform3D transform3D = new Transform3D();
        transform3D.setTranslation(
                new Vector3f(0.0f, 0.0f, -5.0f));
        return new TransformGroup(transform3D);
    }

    private Shape3D createTextShape() {
        Appearance textAppear = new Appearance();
        textAppear.setMaterial(new Material());
        Font3D font3D = new Font3D(
                new Font("Helvetica", Font.PLAIN, 1),
                new FontExtrusion());
        Text3D textGeom = new Text3D(font3D, "Hudson");
        textGeom.setAlignment(Text3D.ALIGN_CENTER);
        Shape3D textShape = new Shape3D();
        textShape.setGeometry(textGeom);
        textShape.setAppearance(textAppear);
        return textShape;
    }

    private void setLighting(
            TransformGroup objMove) {
        DirectionalLight light =
                new DirectionalLight();
        light.setInfluencingBounds(
                new BoundingSphere());
        light.setDirection(
                new Vector3f(0.0f, 0.0f, -1.0f));
        light.setColor(new Color3f(
                0.0f, 1.0f, 1.0f));
        objMove.addChild(light);
    }

    private RotationInterpolator makeSpin(
            TransformGroup spinner) {
        RotationInterpolator rotator =
                new RotationInterpolator(
                new Alpha(-1, 4000), spinner);
        rotator.setTransformAxis(rotateCube());
        BoundingSphere bounds =
                new BoundingSphere();
        rotator.setSchedulingBounds(bounds);
        return rotator;
    }

    private Transform3D rotateCube() {
        rotate1.rotX(Math.PI / 4.0d);
        rotate2.rotZ(Math.PI / 3.0d);
        rotate1.mul(rotate2);
        return rotate1;
    }

    private void connect(Canvas3D canvas3D,
            BranchGroup scene) {
        SimpleUniverse simpleU =
                new SimpleUniverse(canvas3D);
        simpleU.getViewingPlatform().
                setNominalViewingTransform();
        simpleU.addBranchGraph(scene);
    }

    public static void main(String[] args) {
       Text3DWorld t3D3w = new Text3DWorld();
       t3D3w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       t3D3w.setLocationRelativeTo(null);
       t3D3w.setVisible(true);
    }
}
