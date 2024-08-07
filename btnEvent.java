import java.awt.Button;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class btnEvent {
    PM pm = new PM();

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
}
