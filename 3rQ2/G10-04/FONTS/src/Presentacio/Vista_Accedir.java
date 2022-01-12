package Presentacio;

import Controladors.Controlador_Presentacio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//COMENTARI CLASSE:
//Classe que correspon al panel dinici de sessio. Sencarrega de iniciar un usuari i de permetre que un nou usuari es
//registri abans de continuar.

public class Vista_Accedir extends JFrame {
    private Controlador_Presentacio CP;

    private JTextField textUser;
    private JPasswordField passwordField;

    public Vista_Accedir(Controlador_Presentacio CP) {
        getContentPane().setLayout(null);
        setSize(700, 500);
        setLocationRelativeTo(null);
        setTitle("Iniciar Sessió");

        JLabel label = new JLabel("KAKUROS");
        label.setFont(new Font("Tahoma", Font.PLAIN, 30));
        label.setBounds(260, 11, 285, 37);
        getContentPane().add(label);

        JLabel label_1 = new JLabel("Iniciar Sessió");
        label_1.setFont(new Font("Tahoma", Font.PLAIN, 25));
        label_1.setBounds(252, 40, 150, 42);
        getContentPane().add(label_1);

        JLabel label_2 = new JLabel("Usuari");
        label_2.setFont(new Font("Tahoma", Font.PLAIN, 25));
        label_2.setBounds(146, 130, 123, 42);
        getContentPane().add(label_2);
        textUser = new JTextField();
        textUser.setFont(new Font("Tahoma", Font.PLAIN, 18));
        textUser.setColumns(10);
        textUser.setBounds(146, 177, 365, 34);
        getContentPane().add(textUser);

        JLabel label_3 = new JLabel("Contrasenya");
        label_3.setFont(new Font("Tahoma", Font.PLAIN, 25));
        label_3.setBounds(146, 218, 150, 42);
        getContentPane().add(label_3);
        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Tahoma", Font.PLAIN, 18));
        passwordField.setBounds(146, 262, 365, 34);
        getContentPane().add(passwordField);

        JButton Entrar = new JButton("Entrar");
        Entrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textUser.getText().length() == 0 || passwordField.getText().length() == 0) {
                    JOptionPane.showConfirmDialog(null,
                            "Usuari o contrasenya incorrectes.", "Error al iniciar sessió",
                            JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
                }
                else if (!(CP.login(textUser.getText(), passwordField.getText()))) {
                    JOptionPane.showConfirmDialog(null,
                            "Usuari o contrasenya incorrectes.", "Error al iniciar sessió",
                            JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        Entrar.setFont(new Font("Tahoma", Font.PLAIN, 20));
        Entrar.setBounds(280, 310, 100, 30);
        getContentPane().add(Entrar);

        JButton Registrar = new JButton("Registrar-se");
        Registrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField xField = new JTextField(20);
                JPasswordField yField = new JPasswordField(20);
                JPasswordField zField = new JPasswordField(20);

                JPanel myPanel = new JPanel();
                myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.Y_AXIS));

                myPanel.add(new JLabel("Usuari:"), CENTER_ALIGNMENT);
                myPanel.add(xField);
                myPanel.add(new JLabel("Contrasenya:"), CENTER_ALIGNMENT);
                myPanel.add(yField);
                myPanel.add(new JLabel("Repetir contrasenya:"), CENTER_ALIGNMENT);
                myPanel.add(zField);

                int resp = JOptionPane.showConfirmDialog(null, myPanel,
                        "Si us plau introdueixi usuari i contrasenya",
                        JOptionPane.OK_CANCEL_OPTION);
                if (resp == JOptionPane.OK_OPTION) {
                    if (xField.getText().length() == 0 || yField.getText().length() == 0
                        || zField.getText().length() == 0) {
                        JOptionPane.showConfirmDialog(null,
                                "Cal omplir tots els camps.", "Error al registrar-se",
                                JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
                    }
                    else if (!(yField.getText().equals(zField.getText()))) {
                            JOptionPane.showConfirmDialog(null,
                                    "Les contrasenyes no coincideixen.", "Error al registrar-se",
                                    JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
                    } else {
                        Boolean registrat = CP.register(xField.getText(), yField.getText());
                        if (registrat) {
                            JOptionPane.showConfirmDialog(null,
                                        "Compte creat correctament.", "Registre",
                                        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
                        }
                        else {
                            JOptionPane.showConfirmDialog(null,
                                    "Usuari invàlid, si us plau torni a intentar-ho.", "Registre",
                                    JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
        });
        Registrar.setFont(new Font("Tahoma", Font.PLAIN, 15));
        Registrar.setBounds(392, 350, 120, 25);
        getContentPane().add(Registrar);
    }
}



