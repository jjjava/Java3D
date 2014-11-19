package java3d;

import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.geometry.ColorCube;
import javax.media.j3d.*;


public class CompileTest extends Java3dApplet {

    private static int m_kWidth = 256;
    private static int m_kHeight = 256;

    public CompileTest() {
        initJava3d();
    }

    private BranchGroup createColorCubes() {
        BranchGroup bg = new BranchGroup();

        final int kNumCubes = 100000;

        for (int n = 0; n < kNumCubes; n++) {
            ColorCube cube1 = new ColorCube(1.0);

            // switch off pickable attribute so we can compile
            cube1.setPickable(false);
            bg.addChild(cube1);
        }

        bg.compile();

        return bg;
    }

    protected BranchGroup createSceneBranchGroup() {
        BranchGroup objRoot = super.createSceneBranchGroup();

        // do NOT auto compute bounds for this node
        objRoot.setBoundsAutoCompute(false);

        TransformGroup objTrans = new TransformGroup();
        objTrans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        objTrans.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);

        Transform3D yAxis = new Transform3D();
        Alpha rotationAlpha = new Alpha(-1, Alpha.INCREASING_ENABLE, 0, 0,
                4000, 0, 0, 0, 0, 0);

        RotationInterpolator rotator = new RotationInterpolator(rotationAlpha,
                objTrans, yAxis, 0.0f, (float) Math.PI * 2.0f);

        rotator.setSchedulingBounds(createApplicationBounds());
        objTrans.addChild(rotator);

        objTrans.addChild(createColorCubes());

        objRoot.addChild(objTrans);

        return objRoot;
    }

    public static void main(String[] args) {
        CompileTest compileTest = new CompileTest();
        compileTest.saveCommandLineArguments(args);

        new MainFrame(compileTest, m_kWidth, m_kHeight);
    }
}