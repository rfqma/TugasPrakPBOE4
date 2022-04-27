import java.sql.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Login extends JFrame{
    Connector connect = new Connector();
    String user[] = new String[2];

    JLabel lusername = new JLabel("Username : ");
    JTextField fusername = new JTextField();

    JLabel lpass = new JLabel("Password : ");
    JPasswordField fpass = new JPasswordField();

    JButton blogin = new JButton("Login");

    public Login(){
        setTitle("Login");
        setSize(270, 210);
        setVisible(true);
        setDefaultCloseOperation(3);
        setLocationRelativeTo(null);

        setLayout(null);
        add(lusername);
        add(fusername);
        add(lpass);
        add(fpass);
        add(blogin);

        lusername.setBounds(10,10,120,20);
        fusername.setBounds(80, 10, 150, 20);
        lpass.setBounds(10, 40, 120, 20);
        fpass.setBounds(80, 40, 150, 20);
        blogin.setBounds(90, 70, 80, 20);

        blogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                String uname = fusername.getText();
                String pass = String.valueOf(fpass.getPassword());

                if(!uname.isEmpty() && !pass.isEmpty()){
                    if(checkLogin(uname, pass)){
                        JOptionPane.showMessageDialog(null, "Login Berhasil!");
                    }else if(!checkUname(uname)){
                        JOptionPane.showMessageDialog(null, "Username Tidak Ditemukan");
                    }else{
                        JOptionPane.showMessageDialog(null, "Password Salah");
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Silahkan isi terlebih dahulu");
                }
            }
        });

    }

    boolean checkUname(String username){
        try{
            String query = "SELECT * FROM `users` WHERE username = '"+username+"' ";
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

    boolean checkLogin(String username, String password){
        try{
            String sql = "SELECT * FROM `users` WHERE username = '"+username+"' ";
            connect.statement = connect.koneksi.createStatement();
            ResultSet resultSet = connect.statement.executeQuery(sql);
            while(resultSet.next()){
                user[0] = resultSet.getString("username");
                user[1] = resultSet.getString("password");
            }
            connect.statement.close();
            if(user[1].equals(password)){
                return true;
            }else{
                return false;
            }
        }catch(Exception ex){
            System.out.println("Tidak Ditemukan");
            return false;
        }
    }
}
