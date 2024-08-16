
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ERRORPeople {
    private Frame frame;
    PM mp = new PM(); 
    TextField tfField = new TextField("");
    Button btnOK = new Button("ok");
    public void showERRORNUMBER() {
        frame.setSize(300,100);
        frame.setLayout(new GridLayout(2,1));

        btnOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                frame.dispose(); 
                Page.setPage(2);
            }
        });
       

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                frame.dispose(); 
                Page.setPage(2);
            }
        });

        frame.setVisible(true);
    }
    
    public void closeCreator() {
        frame.setVisible(false);
    }
    
}
