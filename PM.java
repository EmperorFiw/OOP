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
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class PM {
    MainPage mp = new MainPage();
    Frame frame = new Frame("Main Program");
    Button [][] btn = new Button[10][20];
    GridBagConstraints gbc = new GridBagConstraints();
    Panel panel1 = new Panel(new GridLayout(10, 20)); // area 20*10
    Panel panel2 = new Panel(new GridLayout(2, 1)); // panel 2*1 box ครอบช่องที่2จาก setLayout row-2
    Panel panelR1 = new Panel(new GridBagLayout()); //ครอบ R1 แบ่งเป็น3ส่วน ในเเนวนอน
    Panel panelR2 = new Panel(new GridBagLayout());
    Panel emptyCenter = new Panel();
    Panel status = new Panel(new GridBagLayout());
    
    
    public void showPM() {

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
        for (int i=0;i<10;i++)
        {
            for (int x=0;x<20;x++)
            {
                btn[i][x] = new Button();
                btn[i][x].setBackground(new Color(255, 255, 255)); 
                panel1.add(btn[i][x]); //สร้าง area
                panel1.removeAll();
            }
        }
    }

    public void fillR1() {
        Panel panelBtnLb = new Panel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        String[] names = {"0-9% of people are sick", "10-19% of people are sick", "20-99% of people are sick", "More than 30% of people are sick."};
        Color[] colors = {Color.GREEN, Color.YELLOW, Color.ORANGE, Color.RED};
    
        for (int i = 0; i < 4; i++) {
            Button btnColor = new Button();
            btnColor.setPreferredSize(new Dimension(30, 30));
            btnColor.setBackground(colors[i]);
            
            gbc.gridx = 0;
            gbc.gridy = i;
            gbc.anchor = GridBagConstraints.WEST;
            gbc.insets = new Insets(2, 0, 2, 10);
            panelBtnLb.add(btnColor, gbc);
    
            Label lb = new Label(names[i]);
            lb.setFont(new Font("Arial", Font.BOLD, 15));
            
            gbc.gridx = 1;
            gbc.insets = new Insets(2, 10, 2, 0);
            panelBtnLb.add(lb, gbc);
        }
        

    
        // สร้าง panelR1 และ emptyCenter ก่อนหน้านี้
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.5; // ลดค่า weightx
        gbc.fill = GridBagConstraints.HORIZONTAL; // กำหนดให้เต็มพื้นที่แนวนอน
        panelR1.add(panelBtnLb, gbc);
    
        ImageIcon icon = resizeIcon("p1.png", 150, 150);
        JLabel background = new JLabel();
        background.setIcon(icon);
        panelR1.add(background);

        addStatus();
        gbc.gridx = 2;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        panelR1.add(status, gbc);
        
        panel2.add(panelR1);
    }
    
    private static ImageIcon resizeIcon(String path, int width, int height) {
        try {
            BufferedImage img = ImageIO.read(new File(path));
            Image resizedImage = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            return new ImageIcon(resizedImage);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public void addStatus() {
        String[] texts = {"Dust", "Poppulation", "Healthy", "Parent", "Percent sicks"}; // ข้อความทั้งหมด 6 ข้อความ
        String[] data = {"200000", "200000", "200000", "200000", "10%"};

        for (int i = 0; i < texts.length; i++) {
            gbc.gridx = 1;
            gbc.gridy = i;
            gbc.fill = GridBagConstraints.WEST;
            Label lb = new Label(texts[i] + ": " + data[i]); // ใส่ชื่อ label
            lb.setFont(new Font("Arial", Font.BOLD, 15)); // ปรับเป็นขนาด Font
            status.add(lb, gbc); // เพิ่ม label เข้า panelR1
        }
    }
    
    public void fillR2() 
    {
        Button btnSelect = new Button("SELECT FILE");
        setButton(1, 1, 10, 60, btnSelect);// ปรับปุ่ม
        setColorButton(btnSelect, 211, 211, 211, 255, 255, 255);//ปรับสีปุ่ม
        
        Button btnClear = new Button("CLEAR DATA");
        setButton(1, 3, 10, 60, btnClear);// ปรับปุ่ม
        setColorButton(btnClear, 211, 211, 211, 255, 255, 255);//ปรับสีปุ่ม
        
        Button btnOK = new Button("OK");
        setButton(1, 2, 10, 60, btnOK);// ปรับปุ่ม
        setColorButton(btnOK, 211, 211, 211, 255, 255, 255);//ปรับสีปุ่ม

        Button btnRain = new Button("Rain");
        setButton(0, 3, 10, 110, btnRain);// ปรับปุ่ม
        setColorButton(btnRain, 211, 211, 211, 180, 232, 255);//ปรับสีปุ่ม

        Button btnARain = new Button("ARTIFICIALA RAIN");
        setButton(0, 4, 10, 60, btnARain);// ปรับปุ่ม
        setColorButton(btnARain, 211, 211, 211, 180, 232, 255); //ปรับสีปุ่ม
        
        TextField tField1 = new TextField("FILE");
        setText(tField1, 0, 1, 10, 200);//ปรับ text
        tField1.setEditable(false);// ห้ามเเก้ ไข text
        
        TextField tField2 = new TextField();
        setText(tField2, 0, 2, 10, 125);//ปรับ text
        
        btnEvent event = new btnEvent();

        event.OnClick(btnSelect, 1);
        event.OnClick(btnClear, 2);
        event.OnClick(btnOK, 3);
        event.OnClick(btnRain, 4);
        event.OnClick(btnARain, 5);
    }

    //ปรับสีปุ่ม
    public void setColorButton(Button button ,int r ,int g ,int b,int r2, int g2 ,int b2){

        btnEvent BEvent = new btnEvent();

        BEvent.MouseButtonColor(button, r, g, b, r2, g2, b2); // เปลี่ยนสีปุ่มเมื่อเมาส์เข้าไปในปุ่ม
        button.setBackground(new Color(r,g,b)); // ปรับสีปุ่ม

    }
    // ปรับปุ่ม
    public void  setButton(int y, int x,int w,int h,Button button){
        gbc.anchor = GridBagConstraints.SOUTHWEST;
        gbc.insets = new Insets(5, 5, 10, 10); // เพิ่มระยะห่างรอบปุ่ม
        // ปรับ ipad เพื่อปรับขนาดปุ่ม
        gbc.ipadx = h;
        gbc.ipady = w; 
        gbc.gridy = y;// ปรับ gridy เพื่อปรับแถวของปุ่มถัดไป
        gbc.gridx = x;// ปรับ gridx เพื่อปรับตำแหน่งของปุ่มถัดไป
        panelR2.add(button, gbc);
        panel2.add(panelR2);
    }

    //ปรับ text
    public void setText(TextField tField,int y, int x,int w,int h){
        gbc.anchor = GridBagConstraints.SOUTHWEST;
        gbc.insets = new Insets(5, 5, 10, 10); // เพิ่มระยะห่างรอบ text 
        // ปรับ ipad เพื่อปรับขนาด text 
        gbc.ipadx = h;
        gbc.ipady = w; 
        gbc.gridy = y;// ปรับ gridy เพื่อปรับแถวของ text ถัดไป
        gbc.gridx = x;// ปรับ gridx เพื่อปรับตำแหน่งของ text ถัดไป
        panelR2.add(tField, gbc);
        panel2.add(panelR2);//12
    }

    ///////////////////////////////////////////////////////////////ปัญหามันไม่เปลี่ยนสี ลบไม่ออก////////////////////////////////////////
    public void clearBox() {
        panel1.removeAll(); // ลบปุ่มทั้งหมด
        panel1.revalidate(); // รีเฟรช layout ของ panel1
        panel1.repaint();    // รีเฟรช panel1

    }

}
