import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Label;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Creator {
    private Frame frame; 

    public void showCreator() {
        MainPage mp = new MainPage(); // obj MainPage
        frame = new Frame("Creator"); 
        Panel panel = new Panel(new GridBagLayout()); // ใช้ GridBagLayout

        String[] names = {"Name: 1", "Name: 2", "Name: 3"};

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.insets = new Insets(50, 10, 10, 10);

        Image icon = Toolkit.getDefaultToolkit().getImage("icon.png");
        frame.setIconImage(icon);
        frame.setLayout(new GridBagLayout());
        frame.setSize(800, 700);
        frame.setBackground(new Color(211, 211, 211)); 
        frame.setLocationRelativeTo(null);

        for (int i = 0; i < 3; i++) {
            Label lb = new Label(names[i]); //ใส่ชื่อ label
            lb.setFont(new Font("Arial", Font.BOLD, 60)); // ปรับเป็นขนาด Font 
            gbc.gridy = i; // ตำแหน่งrow
            panel.add(lb, gbc);
        }

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                frame.dispose(); // ปิด Frame (Destroy)
                mp.showMain();
                Page.setPage(1);
            }
        });
        frame.add(panel);
        frame.setVisible(true);
    }

    public void closeCreator() {
        frame.setVisible(false);
    }
}
