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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
public class PM {
    ERRORPeople ep = new ERRORPeople();
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
    ImageIcon icon = resizeIcon("img/p1.png", 150, 150);
    JLabel background = new JLabel();
    GridBagConstraints gbc = new GridBagConstraints();
    Panel panel1 = new Panel(new GridLayout(10, 20));
    Panel panel2 = new Panel(new GridLayout(2, 1)); 
    Panel panelR1 = new Panel(new GridBagLayout()); 
    Panel panelR2 = new Panel(new GridBagLayout());
    Panel emptyCenter = new Panel();
    Panel status = new Panel(new GridBagLayout());
    HashMap<Integer, String> pmValue = new HashMap<>();
    int people[][] = new int[10][20];
    int dust[][] = new int[10][20];
    int Healthy[][] = new int[10][20];
    int Parent[][] = new int[10][20];
    double Avg[][] = new double[10][20];
    int clickPosition[] = new int[2];
    

    public void showPM() {

        Image icon = Toolkit.getDefaultToolkit().getImage("img/icon.png");
        frame.setIconImage(icon);
        frame.setBackground(new Color(211, 211, 211)); 
        frame.setSize(1000, 700);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new GridLayout(2, 1, 10, 10)); // layput background frame

        setArea(); 
        fillR1(); 
        fillR2(); 

        frame.add(panel1);
        frame.add(panel2);        
        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                frame.dispose(); 
                mp.showMain();
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
        gbc.weightx = 0.5;
        gbc.fill = GridBagConstraints.HORIZONTAL; // กำหนดให้เต็มพื้นที่แนวนอน
        panelR1.add(panelBtnLb, gbc);
    
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
        labels = new Label[texts.length]; 

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
        setButton(1, 1, 10, 60, btnSelect);
        setColorButton(btnSelect, 211, 211, 211, 255, 255, 255);//

        setButton(1, 3, 10, 60, btnClear); 
        setColorButton(btnClear, 211, 211, 211, 255, 255, 255);
        
        setButton(1, 2, 10, 60, btnOK);
        setColorButton(btnOK, 211, 211, 211, 255, 255, 255);

        setButton(0, 3, 10, 110, btnRain);
        setColorButton(btnRain, 211, 211, 211, 180, 232, 255);

        setButton(0, 4, 10, 60, btnARain);
        setColorButton(btnARain, 211, 211, 211, 180, 232, 255); 
        
        setText(tField1, 0, 1, 10, 200);
        tField1.setEditable(false);
        
        setText(tField2, 0, 2, 10, 125);

        // Select File
        btnSelect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(null);
                
                if (result == JFileChooser.APPROVE_OPTION) {

                    String selectedFilePath = fileChooser.getSelectedFile().getAbsolutePath();
                    tField1.setText(selectedFilePath); 

                    String[][] dataGrid = new String[10][20]; 
    
                    try (BufferedReader br = new BufferedReader(new FileReader(selectedFilePath))) {
                        String line; 
                        int row = 0; 
                    
                        while ((line = br.readLine()) != null && row < 10) { 
                            String[] numbers = line.split("\t"); 
                            for (int col = 0; col < numbers.length && col < 20; col++) { 
                                dataGrid[row][col] = numbers[col]; 
                            }
                            row++; 
                        }
                    } catch (IOException ex) {
                        ex.printStackTrace(); 
                    }

                    int maxSize = 200;
                    int index = 0;

                    for (String[] row : dataGrid) {
                        for (String element : row) {
                            if (pmValue.size() < maxSize) {
                                pmValue.put(index, element); 
                                index++; 
                            } else {
                                break; 
                            }
                        }
                        if (pmValue.size() >= maxSize) {
                            break; 
                        }
                    }
                }
                pmProcess();
            }
        });
        // OK
        btnOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                String s = tField2.getText();
                char[] ch = s.toCharArray();
                int x = 0;
                int y = 0;
                for (int i = 0; i < ch.length; i++) {
                    if (Character.isDigit(ch[i])) {
                        y = 0;
                    }
                    else{
                        y = 1;
                        break;
                    }
                }
                if(y == 0){
                    x = Integer.parseInt(s);
                }
                else{
                    x = 0;
                }
                
                pmProcess(); // process when change population
                
                if(x != 0){
                    setPeople(x);
                }
                else{
                    Page.setPage(2);
                    ep.showERROR();
                }
                ImageIcon icon = null;
                icon = resizeIcon("img/pop.png", 150, 150);
                if (icon != null) {
                    background.setIcon(icon);
                    panelR1.revalidate();
                    panelR1.repaint();
                }
            }
        });
        /////// clear
        btnClear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clearArea();
            }
        });
        /// rain
        btnRain.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) {
                ImageIcon icon = null;
                icon = resizeIcon("img/rain.png", 150, 150);
                if (icon != null) {
                    background.setIcon(icon); 
                    panelR1.revalidate();
                    panelR1.repaint();
                }
                //Random random;
                int randomRain = (int) (Math.random() * 3) + 1;
                rain(randomRain);
            }
        });
        /// ARain
        btnARain.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) {
                ImageIcon icon = null;
                icon = resizeIcon("img/rain.png", 150, 150);
                if (icon != null) {
                    background.setIcon(icon); 
                    panelR1.revalidate();
                    panelR1.repaint();
                }
                aRain();
                pmProcess();
            }
        });
        setPeople(0);
    }

    //
    public void setColorButton(Button button ,int r ,int g ,int b,int r2, int g2 ,int b2){

        btnEvent BEvent = new btnEvent();

        BEvent.MouseButtonColor(button, r, g, b, r2, g2, b2); 
        button.setBackground(new Color(r,g,b)); 

    }
    // 
    public void  setButton(int y, int x,int w,int h,Button button){
        gbc.anchor = GridBagConstraints.SOUTHWEST;
        gbc.insets = new Insets(5, 5, 10, 10); 
        gbc.ipadx = h;
        gbc.ipady = w; 
        gbc.gridy = y;
        gbc.gridx = x;
        panelR2.add(button, gbc);
        panel2.add(panelR2);
    }
    
    //ปรับ text
    public void setText(TextField tField,int y, int x,int w,int h){
        gbc.anchor = GridBagConstraints.SOUTHWEST;
        gbc.insets = new Insets(5, 5, 10, 10);
        gbc.ipadx = h;
        gbc.ipady = w; 
        gbc.gridy = y;
        gbc.gridx = x;
        panelR2.add(tField, gbc);
        panel2.add(panelR2);
    }

    public void clearArea() {
        for (int i = 0; i < 10; i++) {
            for (int x = 0; x < 20; x++) {
                if (btn[i][x] != null) { // ตรวจสอบว่าไม่เป็น null ก่อน
                    btn[i][x].setBackground(Color.WHITE); 
                    people[i][x] = 0;
                    dust[i][x] = 0;
                    Healthy[i][x] = 0;
                    Parent[i][x] = 0;
                    Avg[i][x] = 0;
                }
            }
        }
        pmValue.clear();

        for (int i=0;i < labels.length;i++)
        {
            updateLabel(i, texts[i]+" : " +data[i]);
        }
        tField1.setText("");
    }
    public void setPeople(int addpeople)
    {

        int digitCount = String.valueOf(addpeople).length(); 
        int sum = (int) Math.pow(10, digitCount - 1); 
        
        for (int i = 0; i < 10; i++)  //
        {
            for (int x = 0; x < 20; x++) 
            {
                if (addpeople == 0) 
                {
                    people[i][x] = 0;
                    break;
                }
                
                Random random = new Random();
                int randomNumber = addpeople + random.nextInt(sum); 
                people[i][x] = randomNumber;
            }
        }
        if (addpeople > 0)
        {
            pmProcess();
        }
    }

    public void pmProcess()
    {
        int row = 0, col = 0;
        for (Map.Entry<Integer, String> entry : pmValue.entrySet()) {
            try {
                int value = Integer.parseInt(entry.getValue());
                String colorName;
                Random random = new Random();

                if (col > 19) { 
                    col = 0;
                    row++;
                    if (row >= 10) { 
                        break;
                    }
                }

                if (value < 0 )
                {
                    value = 0; ////fix < 0
                }
                else if (value > 250)
                    value = 250; // fix > 250

                if (value >= 0 && value <= 50) {
                    colorName = "GREEN"; // 0-9%
                    int randomSicks = (int) (Math.random() * 10); 
                    Parent[row][col] = (int) Math.round(people[row][col] * (randomSicks / 100.0));
                
                } else if (value > 50 && value <= 100) {
                    colorName = "YELLOW"; // 10-19%
                    int randomSicks = random.nextInt(10) + 10; 
                    Parent[row][col] = (int) Math.round(people[row][col] * (randomSicks / 100.0));
                
                } else if (value > 100 && value <= 150) {
                    colorName = "ORANGE"; // 20-29%
                    int randomSicks = random.nextInt(10) + 20; 
                    Parent[row][col] = (int) Math.round(people[row][col] * (randomSicks / 100.0));
                
                } else {
                    colorName = "RED"; // More than 30% - 50%
                    int randomSicks = random.nextInt(21) + 30; 
                    Parent[row][col] = (int) Math.round(people[row][col] * (randomSicks / 100.0));
                }
                
                Healthy[row][col] = people[row][col]-Parent[row][col]; 
                Avg[row][col] = (Parent[row][col] / (double) people[row][col]) * 100; 
                dust[row][col] = value; 

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
    
    public int getPeople(int r, int c)
    {   
        return people[r][c];
    }

    public int getDust(int r, int c) {
        return dust[r][c]; 
    }

    public int getParent(int r, int c) {
        if (getPeople(r, c) == 0)
            return 0;
        return Parent[r][c];
    }

    public int getHealthy(int r, int c) {
        if (getPeople(r, c) == 0)
            return 0;
        return Healthy[r][c];
    }

    public Double getPercent(int r, int c) { 
        if (getPeople(r, c) == 0) {
            return 0.0;
        }
        Double value = Avg[r][c];
        if (value == null || value.isNaN()) {
            return 0.0;
        }      
        return Math.round(Avg[r][c] * 100.0) / 100.0;
    }

    public void setEmoji(int r, int c) {
        int value = (int) Math.round(getPercent(r, c));
    
        ImageIcon icon = null;
    
        if (value >= 0 && value <= 9) {
            icon = resizeIcon("img/p1.png", 150, 150);
        } else if (value >= 10 && value <= 19) {
            icon = resizeIcon("img/p2.png", 150, 150);
        } else if (value >= 20 && value <= 29) {
            icon = resizeIcon("img/p3.png", 150, 150);
        } else if (value >= 30) {
            icon = resizeIcon("img/p4.png", 150, 150);
        }
    
        if (icon != null) {
            background.setIcon(icon); // เปลี่ยนไอคอนของ JLabel ที่มีอยู่
            panelR1.revalidate();
            panelR1.repaint();
        }
    }

    public void rain(int rand)
    {
        for (int l=0;l<rand;l++)
        {
            int key = 0;
            for (int i = 0; i < 10; i++) {
                for (int x = 0; x < 20; x++) {
                    dust[i][x] -= 50;
                    if (dust[i][x] < 0)
                    {
                        dust[i][x] = 0; //fix < 0
                    }
                    // อัปเดตค่าใน pmValue ตามค่าใน dust และ key
                    if (pmValue.containsKey(key)) {
                        // อัปเดตค่าใน pmValue
                        pmValue.replace(key, Integer.toString(dust[i][x]));
                    }
                    key++; // เพิ่ม key เพื่อให้ไม่ซ้ำกัน
                }
            }
        }
        pmProcess();
    }
    
    public void setPosition(int row, int col)
    {
        clickPosition[0] = row;
        clickPosition[1] = col;
    }

    public void aRain()
    {
        int pmSelectDown = (int)(dust[clickPosition[0]][clickPosition[1]] * 0.5);
        dust[clickPosition[0]][clickPosition[1]] = dust[clickPosition[0]][clickPosition[1]] - pmSelectDown;
        int clickKey = clickPosition[0] * 20 + clickPosition[1]; // สร้าง `key` สำหรับตำแหน่ง clickPosition
        if (pmValue.containsKey(clickKey)) {
            // อัปเดตค่าใน pmValue สำหรับ clickPosition
            pmValue.replace(clickKey, Integer.toString(dust[clickPosition[0]][clickPosition[1]]));
        }
        
        for (int x = clickPosition[0] - 1; x <= clickPosition[0] + 1; x++) {
            for (int y = clickPosition[1] - 1; y <= clickPosition[1] + 1; y++) {
                // ตรวจสอบขอบเขตของ array และข้ามตำแหน่งที่เป็น clickPosition เอง
                if (x >= 0 && x < 10 && y >= 0 && y < 20 && !(x == clickPosition[0] && y == clickPosition[1])) {

                    int pmDown = (int)(dust[x][y] * 0.3);
                    dust[x][y] = dust[x][y] - pmDown;
                    if (dust[x][y] < 0)
                        dust[x][y] = 0;

                    int key = x * 20 + y;
                    if (pmValue.containsKey(key)) {
                        // อัปเดตค่าใน pmValue
                        pmValue.replace(key, Integer.toString(dust[x][y]));
                    }
                }
            }
        }
    }
}

class ButtonClickListener implements ActionListener { //area
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
        pm.updateLabel(1, "Population : " + pm.getPeople(row, col));
        pm.updateLabel(2, "Healthy : " + pm.getHealthy(row, col));
        pm.updateLabel(3, "Parent : " + pm.getParent(row, col));
        pm.updateLabel(4, "Sicks : " + pm.getPercent(row, col) + "%  ");
        pm.setEmoji(row, col);
        pm.setPosition(row, col);

    }
}