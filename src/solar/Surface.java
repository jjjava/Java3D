
package solar;

import javax.media.j3d.Appearance;
import javax.media.j3d.ColoringAttributes;

/**
 *
 * @author Hudson Schumaker
 */
public class Surface {

    public static Appearance getRED() {
        Appearance app = new Appearance();
        app.setColoringAttributes(new ColoringAttributes(1.0f, 0.0f, 0.0f, 1));
        return app;
    }

    public static Appearance getGREEN() {
        Appearance app = new Appearance();
        app.setColoringAttributes(new ColoringAttributes(0.0f, 1.0f, 0.0f, 1));
        return app;
    }

    public static Appearance getBLUE() {
        Appearance app = new Appearance();
        app.setColoringAttributes(new ColoringAttributes(0.0f, 0.0f, 1.0f, 1));
        return app;
    }
    public static Appearance getWHITE() {
        Appearance app = new Appearance();
        app.setColoringAttributes(new ColoringAttributes(1.0f, 1.0f, 1.0f, 1));
        return app;
    }
}