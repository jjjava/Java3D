
package solar;

import javax.swing.JFrame;

/**
 *
 * @author Hudson Schumaker
 */
public class Run {

    public static void main(String args[]) {

        SolarSystem0 frame = new SolarSystem0();
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
