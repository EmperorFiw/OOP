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

public class ERRORPeople {
    private Frame frame; 

    public void showERROR() { 
        frame = new Frame("ERROR"); 
        Panel panel = new Panel(new GridBagLayout()); 

        String[] names = {"ERROR", "The number of people must be in numbers. ","The number of people must be greater than 0."};

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.insets = new Insets(20, 10, 10, 10);

        Image icon = Toolkit.getDefaultToolkit().getImage("img/p4.png");
        frame.setIconImage(icon);
        frame.setLayout(new GridBagLayout());
        frame.setSize(400, 350);
        frame.setBackground(new Color(211, 211, 211)); 
        frame.setLocationRelativeTo(null);

        for (int i = 0; i < names.length; i++) {
            Label lb = new Label(names[i]); //ใส่ชื่อ label
            if(i == 0){
                lb.setFont(new Font("Arial", Font.BOLD, 40));
            }
            else{
                lb.setFont(new Font("Arial", Font.BOLD, 16));
            }
 
            gbc.gridy = i; 
            panel.add(lb, gbc);
        }

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                frame.dispose(); 
                Page.setPage(3);
            }
        });
        frame.add(panel);
        frame.setVisible(true);
    }

    public void closeCreator() {
        frame.setVisible(false);
    }
}
