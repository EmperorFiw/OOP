import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class close implements WindowListener {

    @Override
    public void windowOpened(WindowEvent e) {}

    @Override
    public void windowClosing(WindowEvent e) {
        if (Page.getPage() == 1) {
            System.exit(0);
        } else if (Page.getPage() == 2 || Page.getPage() == 3) {
            Page.setPage(1);
        }
    }

    @Override
    public void windowClosed(WindowEvent e) {}

    @Override
    public void windowIconified(WindowEvent e) {}

    @Override
    public void windowDeiconified(WindowEvent e) {}

    @Override
    public void windowActivated(WindowEvent e) {}

    @Override
    public void windowDeactivated(WindowEvent e) {}
}
