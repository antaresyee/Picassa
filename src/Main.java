import java.awt.Dimension;

import model.Model;
import view.AnimatedFrame;

/**
 * 
 * @author Robert Duvall (rcd@cs.duke.edu), Antares Yee
 */
public class Main {
    public static final Dimension SIZE = new Dimension(400, 400);
    public static final String TITLE = "PICASSA!";

    public static void main(String[] args) {
        Model model = new Model();
        AnimatedFrame view = new AnimatedFrame(TITLE, SIZE);
        view.setModel(model);
        view.setVisible(true);
    }
}
