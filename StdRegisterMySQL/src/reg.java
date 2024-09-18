import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.Math.log;

public class reg extends JFrame {
    private JTextField idtextfield;
    private JTextField nameTxt;
    private JTextField birthdayTxt;
    private JTextField mobileTxt;
 //   private JButton SAVEButton;
    private JPanel panel1;
    private JLabel headerLabel;
    private JLabel IDLabel;
    private JLabel nameLabel;
    private JLabel birthdayLabel;
    private JLabel mobileLabel;
    private JButton buttonSave;
    private JButton deleteBtn;
    private JButton editBtn;
    private JTable table1;





    public reg() {




        buttonSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String student_id =   idtextfield.getText();
                String student_name =   nameTxt.getText();
                String birthday =   birthdayTxt.getText();
                String phone_number =   mobileTxt.getText();

                System.out.println(student_id + student_name + birthday + phone_number);

                Connection con1;
                PreparedStatement insert;


                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    con1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/School_Registration", "root", "");
                    insert = con1.prepareStatement("insert into Student(student_name,birth_date_std,mobile)values(?,?,?)");

                    insert.setString(1,student_name);
                    insert.setString(2,birthday);

                    insert.setString(3,phone_number);
                    insert.executeUpdate();


JOptionPane.showMessageDialog(buttonSave,"table added");
                    idtextfield.setText("");
                    mobileTxt.setText("");
                    nameTxt.setText("");
                    birthdayTxt.setText("");
                    nameTxt.requestFocus();

                } catch (Exception ex) {
                    Logger.getLogger(reg.class.getName()).log(Level.SEVERE,ex.getLocalizedMessage(),ex);
                }
              /*  JOptionPane.showMessageDialog(buttonSave,
                        "ogrenci numarasi:" + idtextfield.getText() +
                                "\nisim soyisim" + nameTxt.getText() +
                                "\ndogum tarihi:" + birthdayTxt.getText() +
                                "\nmobil no:" + mobileTxt.getText()
               ,"the title" ,JOptionPane.INFORMATION_MESSAGE);
                System.out.println("button tıklandı"); */
            }
        });


        editBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connection con1;
                PreparedStatement updateStatement;
                String student_name = nameTxt.getText();
                String birthday = birthdayTxt.getText();
                String phone_number = mobileTxt.getText();
                String search_name = idtextfield.getText();
                try{

                    Class.forName("com.mysql.cj.jdbc.Driver");
                    con1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/School_Registration", "root", "");
                //    insert = con1.prepareStatement("update Student student_name=? birth_date_std=? mobile=? where student_name=aa and student_id=?" );

                    updateStatement = con1.prepareStatement("UPDATE Student SET student_name=?, birth_date_std=?, mobile=? WHERE student_name=?");

                    updateStatement.setString(1, student_name);      // Yeni isim
                    updateStatement.setString(2, birthday);          // Yeni doğum tarihi
                    updateStatement.setString(3, phone_number);      // Yeni telefon
                    updateStatement.setString(4, search_name);       // Eski öğrenci ismi (arayacağınız isim)

                    int rowsAffected = updateStatement.executeUpdate();

                    if (rowsAffected > 0) {
                        JOptionPane.showMessageDialog(editBtn, "Kayıt başarıyla güncellendi.");
                    } else {
                        JOptionPane.showMessageDialog(editBtn, "Kayıt bulunamadı veya güncellenemedi.");
                    }

                } catch (Exception ex) {
                    System.out.println("there is a problem check it out beybisi");
                    Logger.getLogger(reg.class.getName()).log(Level.SEVERE,ex.getLocalizedMessage(),ex);

                }
            }


        });



        deleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String student_name = nameTxt.getText();
                Connection con1;
                PreparedStatement delete;

                try{
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    con1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/School_Registration","root","");

                    delete = con1.prepareStatement(" delete from Student where student_name=?");
                    delete.setString(1,student_name);

                    int rowsAffected = delete.executeUpdate();
                    if (rowsAffected > 0) {
                        JOptionPane.showMessageDialog(deleteBtn, "Kayıt başarıyla silindi.");
                    } else {
                        JOptionPane.showMessageDialog(deleteBtn, "Kayıt bulunamadı.");
                    }

                } catch (Exception ex) {
                    Logger.getLogger(reg.class.getName()).log(Level.SEVERE,ex.getLocalizedMessage());
                }

            }
        });







    }

    public static void main(String[] args) {

        reg r = new reg();
        r.setContentPane(r.panel1);
        r.setTitle("Registration Panel");
        r.setVisible(true);
        r.setSize(650,300);
        r.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }

    private void createUIComponents() {
        // TODO: place custom component creation code here

    }


}
