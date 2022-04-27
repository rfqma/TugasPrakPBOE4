import java.security.MessageDigest;
import java.sql.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Register extends JFrame {
    Connector connect = new Connector();
    String user[] = new String[2];

    JLabel lusername = new JLabel("Username : ");
    JTextField fusername = new JTextField();

    JLabel lpass = new JLabel("Password : ");
    JPasswordField fpass = new JPasswordField();

    JButton bsignup = new JButton("Register");

    public Register() {
        setTitle("Sign Up!");
        setSize(270, 210);
        setVisible(true);
        setDefaultCloseOperation(3);
        setLocationRelativeTo(null);

        setLayout(null);
        add(lusername);
        add(fusername);
        add(lpass);
        add(fpass);
        add(bsignup);

        lusername.setBounds(10,10,120,20);
        fusername.setBounds(80, 10, 150, 20);
        lpass.setBounds(10, 40, 120, 20);
        fpass.setBounds(80, 40, 150, 20);
        bsignup.setBounds(80, 70, 100, 20);

        bsignup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                String uname = fusername.getText();
                String pass = String.valueOf(fpass.getPassword());

                if(!uname.isEmpty() && !pass.isEmpty()){
                    try{
                        if(checkUname(uname)){
                            JOptionPane.showMessageDialog(null,"Username Sudah Tersedia");
                        }else{
                            String sql = "INSERT INTO `users`(`username`, `password`) VALUES ('"+uname+"','"+pass+"')";
                            connect.statement = connect.koneksi.createStatement();
                            connect.statement.executeUpdate(sql);

                            System.out.println("Register Berhasil!");
                            JOptionPane.showMessageDialog(null,"Register Berhasil!");
                        }
                    } catch(Exception ex){
                        System.out.println(ex);
                        JOptionPane.showMessageDialog(null,"Register Gagal!");
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null, "Isi Kolom Data!");
                }
            }
        });
    }
    boolean checkUname(String username){
        try{
            String query = "SELECT * FROM `users` WHERE username = '"+username+"'";
            connect.statement = connect.koneksi.createStatement();
            ResultSet resultSet = connect.statement.executeQuery(query);
            while(resultSet.next()){
                user[0] = resultSet.getString("username");
            }
            connect.statement.close();
            user[0].toString();
            return true;
        }catch(Exception ex){
            System.out.println("Tidak Ditemukan");
            return false;
        }
    }


}
