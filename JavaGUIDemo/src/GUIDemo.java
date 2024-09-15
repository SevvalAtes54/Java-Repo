import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIDemo  extends JFrame{
    private JPanel panel1;
    private JTextField nameTxtFld;
    private JButton button1;

    public GUIDemo() {
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(button1,nameTxtFld.getText() + "isim");
                System.out.println("butona tiklandi");
            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }


    public static void main(String[] args) {
GUIDemo guiDemo = new GUIDemo();
guiDemo.setContentPane(guiDemo.panel1);
guiDemo.setTitle("hello");
guiDemo.setSize(300,400);
guiDemo.setVisible(true);
       guiDemo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
