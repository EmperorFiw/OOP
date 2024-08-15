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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.rmi.AccessException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
public class PM {
    MainPage mp = new MainPage();
    Frame frame = new Frame("Main Program");
    Button btnSelect = new Button("SELECT FILE");
    Button btnClear = new Button("CLEAR DATA");
    Button btnOK = new Button("OK");
    Button btnRain = new Button("Rain");
    Button btnARain = new Button("ARTIFICIALA RAIN");
    Label[] labels; // อาร์เรย์สำหรับเก็บ Label
    String[] texts = {"Dust", "Population", "Healthy", "Parent", "Sicks"};
    String[] data = {"No data", "No data", "No data", "No data", "No data"};
    TextField tField1 = new TextField("");
    TextField tField2 = new TextField();
    Button [][] btn = new Button[10][20];
    GridBagConstraints gbc = new GridBagConstraints();
    Panel panel1 = new Panel(new GridLayout(10, 20)); // area 20*10
    Panel panel2 = new Panel(new GridLayout(2, 1)); // panel 2*1 box ครอบช่องที่2จาก setLayout row-2
    Panel panelR1 = new Panel(new GridBagLayout()); //ครอบ R1 แบ่งเป็น3ส่วน ในเเนวนอน
    Panel panelR2 = new Panel(new GridBagLayout());
    Panel emptyCenter = new Panel();
    Panel status = new Panel(new GridBagLayout());
    HashMap<Integer, String> pmValue = new HashMap<>();
    int people[][] = new int[10][20];
    int dust[][] = new int[10][20];
    int Healthy[][] = new int[10][20];
    int Parent[][] = new int[10][20];
    double Avg[][] = new double[10][20];
    

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
                btn[i][x].addActionListener(new ButtonClickListener(this, i, x)); // เพิ่ม ActionListener ให้กับแต่ละปุ่ม
                panel1.add(btn[i][x]); //สร้าง area
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
        labels = new Label[texts.length]; // สร้างอาร์เรย์ Label ขนาดเท่ากับ texts

        for (int i = 0; i < texts.length; i++) {
            gbc.gridx = 1;
            gbc.gridy = i;
            gbc.fill = GridBagConstraints.WEST;
            labels[i] = new Label(texts[i] + ": " + data[i]);
            labels[i].setFont(new Font("Arial", Font.BOLD, 15));
            status.add(labels[i], gbc);
        }
    }

    public void fillR2() 
    {
        int Default = 0;
        setButton(1, 1, 10, 60, btnSelect);// ปรับปุ่ม
        setColorButton(btnSelect, 211, 211, 211, 255, 255, 255);//ปรับสีปุ่ม
        
        
        setButton(1, 3, 10, 60, btnClear);// ปรับปุ่ม
        setColorButton(btnClear, 211, 211, 211, 255, 255, 255);//ปรับสีปุ่ม
        
        setButton(1, 2, 10, 60, btnOK);// ปรับปุ่ม
        setColorButton(btnOK, 211, 211, 211, 255, 255, 255);//ปรับสีปุ่ม

        setButton(0, 3, 10, 110, btnRain);// ปรับปุ่ม
        setColorButton(btnRain, 211, 211, 211, 180, 232, 255);//ปรับสีปุ่ม

        setButton(0, 4, 10, 60, btnARain);// ปรับปุ่ม
        setColorButton(btnARain, 211, 211, 211, 180, 232, 255); //ปรับสีปุ่ม
        
        setText(tField1, 0, 1, 10, 200);//ปรับ text
        tField1.setEditable(false);// ห้ามเเก้ ไข text
        
        setText(tField2, 0, 2, 10, 125);//ปรับ text

        
        btnSelect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(null);
                
                if (result == JFileChooser.APPROVE_OPTION) {
                    // รับไฟล์ที่เลือกมาเก็บในตัวแปร
                    String selectedFilePath = fileChooser.getSelectedFile().getAbsolutePath();
                    tField1.setText(selectedFilePath); //set path file

                    String[][] dataGrid = new String[10][20]; // สร้างอาร์เรย์ 2 มิติขนาด 10x20 เพื่อเก็บข้อมูลจากไฟล์
    
                    try (BufferedReader br = new BufferedReader(new FileReader(selectedFilePath))) {
                        String line; // ตัวแปรเพื่อเก็บแต่ละบรรทัดที่อ่านมาจากไฟล์
                        int row = 0; // ตัวแปรเพื่อระบุแถวใน dataGrid ที่กำลังจัดการ
                    
                        while ((line = br.readLine()) != null && row < 10) { // อ่านไฟล์ทีละบรรทัดจนกว่าจะหมด หรือจนกว่าแถวจะครบ 10 แถว
                            String[] numbers = line.split("\t"); // แยกบรรทัดที่อ่านมาเป็นส่วนๆ โดยใช้แท็บเป็นตัวคั่น
                            for (int col = 0; col < numbers.length && col < 20; col++) { // วนลูปตามจำนวนคอลัมน์ในบรรทัดหรือจนกว่าจะครบ 20 คอลัมน์
                                dataGrid[row][col] = numbers[col]; // เก็บข้อมูลแต่ละส่วนใน dataGrid ตำแหน่งที่สอดคล้องกัน
                            }
                            row++; // เพิ่มค่า row เพื่อไปจัดการแถวถัดไปใน dataGrid
                        }
                    } catch (IOException ex) {
                        ex.printStackTrace(); // ถ้าเกิดข้อผิดพลาดในการอ่านไฟล์ จะพิมพ์รายละเอียดของข้อผิดพลาดออกมาในคอนโซล
                    }
                    
                    int index = 0;

                    for (String[] row : dataGrid) {
                        for (String element : row) {
                            pmValue.put(index, element); // เก็บค่าลงในแฮชแมป pmValue โดยใช้ index เป็นคีย์
                            index++; // เพิ่ม index เพื่อเก็บค่าในตำแหน่งถัดไป
                            
                        }
                        pmProcess();
                    }
                }
                     
            }
        });
        
        btnOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                String s = tField2.getText();
                int x = Integer.parseInt(s);
                setPeople(x);
            }
        });

        btnClear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clearArea();
            }
        });
        setPeople(5000);
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

    public void clearArea() {
        for (int i = 0; i < 10; i++) {
            for (int x = 0; x < 20; x++) {
                if (btn[i][x] != null) { // ตรวจสอบว่าไม่เป็น null ก่อน
                    btn[i][x].setBackground(Color.WHITE); 
                }
            }
        }
        for (int i=0;i < labels.length;i++)
        {
            updateLabel(i, texts[i]+" : " +data[i]);
        }
        tField1.setText("");
    }
    public void setPeople(int addpeople)
    {
        int pop = 0;
        for (int i = 0; i < 10; i++) {
            for (int x = 0; x < 20; x++) {
                Random random = new Random();
                int randomNumber = (addpeople - random.nextInt(1000)) + random.nextInt(1000);
                people[i][x] = randomNumber;
                pop += randomNumber;
            }
        }
        //updateLabel(1, "Population : " + pop);
    }

    public void pmProcess()
    {
        int row = 0, col = 0;
        for (Map.Entry<Integer, String> entry : pmValue.entrySet()) {
            try {
                // แปลงค่าจาก String เป็น int
                int value = Integer.parseInt(entry.getValue());
                
                String colorName;
                Random random = new Random();

                if (col > 19) { //จัดการ array ให้เป้น row 10* col 20
                    col = 0;
                    row++;
                    if (row >= 10) { 
                        break;
                    }
                }

                if (value >= 0 && value <= 50) {
                    colorName = "GREEN"; // 0-9%
                    int randomSicks = (int) (Math.random() * 10); 
                    Parent[row][col] = (int) Math.round(people[row][col] * (randomSicks / 100.0));
                    ImageIcon icon = resizeIcon("p1.png", 150, 150);
                     JLabel background = new JLabel();
                     background.setIcon(icon);
                     panelR1.add(background);
                
                } else if (value > 50 && value <= 100) {
                    colorName = "YELLOW"; // 10-19%
                    int randomSicks = random.nextInt(10) + 10; 
                    Parent[row][col] = (int) Math.round(people[row][col] * (randomSicks / 100.0));
                
                } else if (value > 100 && value <= 150) {
                    colorName = "ORANGE"; // 20-29%
                    int randomSicks = random.nextInt(10) + 20; 
                    Parent[row][col] = (int) Math.round(people[row][col] * (randomSicks / 100.0));
                
                } else {
                    colorName = "RED"; // More than 30%
                    int randomSicks = random.nextInt(61) + 30; 
                    Parent[row][col] = (int) Math.round(people[row][col] * (randomSicks / 100.0));
                }
                
                Healthy[row][col] = people[row][col]-Parent[row][col]; // หาจำนวนผู้ป่วย
                Avg[row][col] = (Parent[row][col] / (double) people[row][col]) * 100; //หาค่าเฉลี่ย ผู้ป่วย/จำนวนประชากร
                dust[row][col] = value; //ค่าPM
                showAreaColor(row, col, colorName);
                col ++;
            } catch (NumberFormatException e) {
                System.err.println("Invalid number format for value: " + entry.getValue());
            }
        }
    }

    public void showAreaColor(int row, int col, String colorName) {
        // สร้างแผนที่สี
        Map<String, Color> colorMap = new HashMap<>();
        colorMap.put("RED", Color.RED);
        colorMap.put("ORANGE", Color.ORANGE);
        colorMap.put("YELLOW", Color.YELLOW);
        colorMap.put("GREEN", Color.GREEN);
    
        Color color = colorMap.get(colorName.toUpperCase());
        if (color != null) {
            if (row >= 0 && row < 10 && col >= 0 && col < 20) { // ตรวจสอบขอบเขตของแถวและคอลัมน์
                btn[row][col].setBackground(color);
            } else {
                System.err.println("Index out of bounds: row=" + row + ", col=" + col);
            }
        }
    }

    public void updateLabel(int index, String newText) {
        if (labels == null) {
            return;
        }
        if (index >= 0 && index < labels.length) {
            labels[index].setText(newText);
        } else {
            System.err.println("Index out of bounds");
        }
    }
    
    public int getPeple(int r, int c)
    {   
        return people[r][c];
    }
    public String getDust(int r, int c) {
        return String.valueOf(dust[r][c]); // แปลงค่า int เป็น String และ return
    }
    public String getParent(int r, int c) {
        return String.valueOf(Parent[r][c]);
    }
    public String getHealthy(int r, int c) {
        return String.valueOf(Healthy[r][c]);
    }
    public String getPercent(int r, int c) { 
        DecimalFormat df = new DecimalFormat("0.00"); //แปลงเป็นทศนิยม 2 ตำแหน่ง
        return df.format(Avg[r][c]); //to string
    }
}

class ButtonClickListener implements ActionListener {
    private PM pm;
    private int row;
    private int col;

    public ButtonClickListener(PM pm ,int row, int col) {
        this.pm = pm;
        this.row = row;
        this.col = col;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        pm.updateLabel(0, "Dust : " + pm.getDust(row, col)); //ดึงdustจาก array row col
        pm.updateLabel(1, "Population : " + pm.getPeple(row, col));
        pm.updateLabel(2, "Healthy : " + pm.getHealthy(row, col));
        pm.updateLabel(3, "Parent : " + pm.getParent(row, col));
        pm.updateLabel(4, "Sicks : " + pm.getPercent(row, col) + "%  ");
    }
}