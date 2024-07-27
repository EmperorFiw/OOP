import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPage extends Frame{
    
    public void showMain() {
        setTitle("Main Menu");
        setSize(800, 700);
        setLocationRelativeTo(null);
        addWindowListener(new close());
        setLayout(new GridBagLayout());
        Image icon = Toolkit.getDefaultToolkit().getImage("icon.png");
        setIconImage(icon);
        setBackground(new Color(54, 54, 54));
        showMainButton();
    }

    public void showMainButton() {
        PM pm = new PM();
        btnEvent be = new btnEvent();
        Creator cr = new Creator();
        
        Button btnStart = new Button("Start");
        Button btnCreator = new Button("Creator");
        GridBagConstraints gbc = new GridBagConstraints();

        btnStart.setBackground(new Color(211, 211, 211)); 
        btnCreator.setBackground(new Color(211, 211, 211)); 
        btnStart.setFont(new Font("Arial", Font.PLAIN, 60)); //font
        btnCreator.setFont(new Font("Arial", Font.PLAIN, 60));
        gbc.insets = new Insets(20, 10, 10, 10); // เพิ่มระยะห่างรอบปุ่ม
        gbc.ipadx = 280; //ความกว้าง
        gbc.ipady = 100; //ความสูง
        gbc.gridx = 0; //col
        gbc.gridy = 1; //row
        gbc.fill = GridBagConstraints.HORIZONTAL; //ขยายปุ่มในแนวนอนให้เท่ากัน
        add(btnStart, gbc);
        gbc.gridy = 2;
        add(btnCreator, gbc);

        addWindowListener(new close());
        setVisible(true);

        //Click
        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Page.setPage(2);
                setVisible(false); // ปิดหน้าต่างหลัก MainPage
                pm.showPM();
            }
        });
        
        
        btnCreator.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Page.setPage(3);
                cr.showCreator(); // show creator
                setVisible(false); // ปิดหน้าต่างหลัก MainPage
            }
        });

        //Event
        be.addMoseEventBtn(btnStart);
        be.addMoseEventBtn(btnCreator);
    }
}

