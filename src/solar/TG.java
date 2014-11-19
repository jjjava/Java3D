package solar;

import javax.media.j3d.TransformGroup;

/**
 *
 * @author Husdon Schumaker
 */
public class TG {

    public static TransformGroup getTG() {
        TransformGroup tg = new TransformGroup();
        tg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        return tg;
    }
}