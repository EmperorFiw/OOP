import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Label;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class PM {

    GridBagConstraints gbc = new GridBagConstraints();
    Panel panel1 = new Panel(new GridLayout(10, 20)); // area 20*10
    Panel panel2 = new Panel(new GridLayout(2, 1)); // panel 2*1 box ครอบช่องที่2จาก setLayout row-2
    Panel panelR1 = new Panel(new GridBagLayout()); //ครอบ R1 แบ่งเป็น3ส่วน ในเเนวนอน
    Panel panelR2 = new Panel(new GridBagLayout());
    Panel emptyCenter = new Panel();
    Panel status = new Panel(new GridBagLayout());
    
    
    public void showPM() {

        MainPage mp = new MainPage();
        Frame frame = new Frame("Main Program");

        Image icon = Toolkit.getDefaultToolkit().getImage("icon.png");
        frame.setIconImage(icon);
        frame.setBackground(new Color(211, 211, 211)); 
        frame.setSize(800, 700);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new GridLayout(2, 1, 10, 10)); // layput background frame

        setArea(); //สร้าตาราง 10*20
        fillR1(); //สร้าง R1
        fillR2(); //สร้าง R2

        frame.add(panel1);
        frame.add(panel2);        
        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                frame.dispose(); // ปิด Frame 
                mp.showMain();//กลับหน้าหลัก
                Page.setPage(1);
            }
        });
        
    }

    public void setArea()
    {
        Button [][] btn = new Button[10][20];
        for (int i=0;i<10;i++)
        {
            for (int x=0;x<20;x++)
            {
                btn[i][x] = new Button();
                panel1.add(btn[i][x]); //สร้าง area
            }
            
        }
    }

    public void fillR1()
    {
        Panel panelBtnLb = new Panel(new GridBagLayout()); // panel 1 ของ row-2   panel2 ช่องButton และ ตัวหนังสือ
        String[] names = {"0-9% people are sick", "10-19% people are sick", "20-99% people are sick", "More than 30% people are sick."};
        Color[] colors = {Color.GREEN, Color.YELLOW, Color.ORANGE, Color.RED};

        for (int i = 0; i < 4; i++) {
            Button btnColor = new Button();
            btnColor.setPreferredSize(new Dimension(30, 30));//size button
            btnColor.setBackground(colors[i]); //set color
            gbc.anchor = GridBagConstraints.WEST;
            gbc.insets = new Insets(2, 0, 0, 10);
            gbc.gridx = 0;
            gbc.gridy = i;
            panelBtnLb.add(btnColor, gbc); // เพิ่มปุ่มเข้า panelBtnLb

            Label lb = new Label(names[i]); // ใส่ชื่อ label
            lb.setFont(new Font("Arial", Font.BOLD, 15)); // ปรับเป็นขนาด Font
            gbc.gridx = 1;
            panelBtnLb.add(lb, gbc); // เพิ่ม text เข้า panelBtnLb



            panelR1.add(panelBtnLb); // เพิ่ม panelBtnLb เข้า panelR1 |  panelR1 = null

        }

        gbc.gridx = 0; //ตั้งค่าให้ชิดซ้าย
        gbc.gridy = 0; 
        panelR1.add(panelBtnLb, gbc); // เพิ่ม panelBtnLb เข้า panelR1

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelR1.add(emptyCenter, gbc); // เพิ่ม emptyCenter เข้า panelR1 โดยให้ขยายเต็มพื้นที่

        addStatus();
        gbc.gridx = 2; //ชิดขวา
        gbc.gridy = 0;
        gbc.weightx = 0; // รีเซ็ต weightx
        panelR1.add(status, gbc);
        


        panel2.add(panelR1); 
    }

    public void addStatus() {
        String[] texts = {"Kuy1", "Hee1", "Kuy2", "Hee2", "Kuy3", "Hee3"}; // ข้อความทั้งหมด 6 ข้อความ
        String[] data = {"123", "123", "123", "123", "123", "1211331212313"};

        for (int i = 0; i < texts.length; i++) {
            gbc.gridx = 1;
            gbc.gridy = i;
            Label lb = new Label(texts[i] + ": " + data[i]); // ใส่ชื่อ label
            lb.setFont(new Font("Arial", Font.BOLD, 15)); // ปรับเป็นขนาด Font
            status.add(lb, gbc); // เพิ่ม label เข้า panelR1
        }
    }
    
    public void fillR2() 
    {
        gbc.anchor = GridBagConstraints.SOUTHWEST;
        gbc.gridy = 1;
        gbc.gridx = 1;
        Button btnSelect = new Button("SELECT FILE");
        panelR2.add(btnSelect, gbc);
        
        // ปรับ gridx เพื่อปรับตำแหน่งของปุ่มถัดไป
        gbc.gridx = 2;
        Button btnInput = new Button("INPUT STATR");
        panelR2.add(btnInput, gbc);
        
        gbc.gridx = 3;
        Button btnOutput = new Button("INPUT STOP");
        panelR2.add(btnOutput, gbc);
        
        gbc.gridx = 4;
        Button btnStart = new Button("OK");
        panelR2.add(btnStart, gbc);
        

        panel2.add(panelR1);
        panel2.add(panelR2);
    }
}
