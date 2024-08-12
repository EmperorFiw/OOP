import java.awt.Button;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFileChooser;

public class btnEvent {
    PM pm = new PM();

    public void OnClick(Button btn, int btnID)
    {
        btn.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                if (btnID == 1) {
                    JFileChooser fileChooser = new JFileChooser();
                    int result = fileChooser.showOpenDialog(null);
        
                    if (result == JFileChooser.APPROVE_OPTION) {
                        // รับไฟล์ที่เลือกมาเก็บในตัวแปร
                        String selectedFilePath = fileChooser.getSelectedFile().getAbsolutePath();
        
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
                        
        
                        // แสดงผลในคอนโซล (สามารถเปลี่ยนเป็นแสดงใน GUI)
                        for (String[] row : dataGrid) {
                            for (String element : row) {
                                System.out.print(element + " "); /// element คือเลขที่นำเข้าจากไฟล์  ////////// + " " คือเว้นช่องให้มัน
                            }
                            System.out.println(); //เว้นบรรทัด
                        }
                    }
                }
                else if (btnID == 2) {
                    pm.clearBox();
                }
            }
        });
    }


    void addMoseEventBtn(Button btn, int r, int g, int b) {
        btn.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {
                // เปลี่ยนสีปุ่มเมื่อเมาส์เข้าไปในปุ่ม
                btn.setBackground(Color.WHITE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                //ออก
                btn.setBackground(new Color(r, g, b)); 
            }

        });
    }

    void MouseButtonColor (Button btn, int r, int g, int b ,int r2 ,int g2 ,int b2){

        btn.addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {
                // เปลี่ยนสีปุ่มเมื่อเมาส์เข้าไปในปุ่ม
                btn.setBackground(new Color(r2, g2, b2));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                //ออก
                btn.setBackground(new Color(r, g, b)); 
            }

        });
    }
}
