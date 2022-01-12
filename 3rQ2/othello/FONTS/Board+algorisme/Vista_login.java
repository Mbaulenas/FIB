import javax.swing.*;
import java.awt.*;
public class Vista_login extends JFrame{

    private Container container = getContentPane();

    private JLabel usertext = new JLabel("Nom d'usuari");
    private JLabel passtext = new JLabel("Contrasenya");

    private JTextField username= new JTextField(20);
    private JPasswordField contrasenya = new JPasswordField();

    private JButton botologin = new JButton("ENTRA");
    private JButton botoalta = new JButton("REGISTRA'T");

    private void posicions(){

        usertext.setBounds(150,150,100,30);
        passtext.setBounds(150,220,100,30);
        username.setBounds(300,150,150,30);
        contrasenya.setBounds(300,220,150,30);
        botologin.setBounds(300,300,120,30);
        botoalta.setBounds(150,300,120,30);

    }

    private void afegir(){

        container.setLayout(null);
        container.add(usertext);
        container.add(passtext);
        container.add(username);
        container.add(contrasenya);
        container.add(botologin);
        container.add(botoalta);

    }

    public Vista_login(){

        posicions();
        afegir();

    }



    public static void main(String[] args) {

        JFrame f = new Vista_login();
        f.setTitle("test");
        f.setSize(600,600);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);

    }

}