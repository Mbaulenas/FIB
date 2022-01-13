package Presentacio;

import Controladors.Controlador_Presentacio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//COMENTARI CLASSE:
//Classe que correspon al panel del Ranquing. Permet a l'usuari navegar pels dos tipus de rànquing per mostrarne un.

public class Vista_Ranquing extends JFrame{

    Controlador_Presentacio CP;

    public Vista_Ranquing(Controlador_Presentacio CP) {
        getContentPane().setLayout(null);
        setSize(700, 500);
        setLocationRelativeTo(null);
        setTitle("Kakuros");

        JLabel label = new JLabel("RÀNQUING");
        label.setFont(new Font("Tahoma", Font.PLAIN, 30));
        label.setBounds(270, 11, 285, 37);
        getContentPane().add(label);

        JButton Enrere = new JButton("Enrere");
        Enrere.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CP.tornar_menu("Ranquing");
            }
        });
        Enrere.setFont(new Font("Tahoma", Font.PLAIN, 15));
        Enrere.setBounds(30, 54, 90, 23);
        getContentPane().add(Enrere);

        JButton Temps = new JButton("Per temps mitjà");
        Temps.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object[] opcions = {"Fàcil", "Mitjana", "Difícil"};
                int resp = JOptionPane.showOptionDialog(null,
                        "Seleccioni una dificultat:",
                        "Selector de dificultats",
                        JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null, opcions, null);
                switch (resp) {
                    case 0:
                        JFrame f = new JFrame("Fàcil");
                        String dataf[][] = new String[10][3];
                        for(int i = 0; i < 10; ++i) {
                            String s = String.valueOf(i+1);
                            dataf[i][0] = s;
                            dataf[i][1] = CP.user_ranquing_temps(i, "Fàcil");
                            dataf[i][2] = CP.temps_ranquing(i, "Fàcil");
                        }
                        String columnf[]={"Posició","Usuari","Temps"};
                        JTable tf = new JTable(dataf,columnf) {
                            public boolean isCellEditable(int row, int column) {
                                return false;
                            }
                        };
                        tf.setRowHeight(30);
                        tf.setBounds(30,40,200,300);
                        JScrollPane spf=new JScrollPane(tf);
                        f.add(spf);
                        f.setSize(300,400);
                        f.setLocationRelativeTo(null);
                        f.setVisible(true);
                        break;
                    case 1:
                        JFrame m=new JFrame("Mitjana");
                        String datam[][] = new String[10][3];
                        for(int i = 0; i < 10; ++i) {
                            String s = String.valueOf(i+1);
                            datam[i][0] = s;
                            datam[i][1] = CP.user_ranquing_temps(i, "Mitjana");
                            datam[i][2] = CP.temps_ranquing(i, "Mitjana");
                        }
                        String columnm[]={"Posició","Usuari","Temps"};
                        JTable tm=new JTable(datam,columnm) {
                            public boolean isCellEditable(int row, int column) {
                                return false;
                            }
                        };
                        tm.setRowHeight(30);
                        tm.setBounds(30,40,200,300);
                        JScrollPane spm=new JScrollPane(tm);
                        m.add(spm);
                        m.setSize(300,400);
                        m.setLocationRelativeTo(null);
                        m.setVisible(true);
                        break;
                    case 2:
                        JFrame d=new JFrame("Difícil");
                        String datad[][] = new String[10][3];
                        for(int i = 0; i < 10; ++i) {
                            String s = String.valueOf(i+1);
                            datad[i][0] = s;
                            datad[i][1] = CP.user_ranquing_temps(i, "Difícil");
                            datad[i][2] = CP.temps_ranquing(i, "Difícil");
                        }
                        String columnd[]={"Posició","Usuari","Temps"};
                        JTable td=new JTable(datad,columnd) {
                            public boolean isCellEditable(int row, int column) {
                                return false;
                            }
                        };
                        td.setRowHeight(30);
                        td.setBounds(30,40,200,300);
                        JScrollPane spd=new JScrollPane(td);
                        d.add(spd);
                        d.setSize(300,400);
                        d.setLocationRelativeTo(null);
                        d.setVisible(true);
                        break;
                }
            }
        });
        Temps.setFont(new Font("Tahoma", Font.PLAIN, 20));
        Temps.setBounds(190, 130, 300, 42);
        getContentPane().add(Temps);

        JButton info1 = new JButton("i");
        info1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Mostra el rànquing per temps mitjà emprat en resoldre els kakuros en la dificultat seleccionada.");
            }
        });
        info1.setFont(new Font("Tahoma", Font.PLAIN, 25));
        info1.setBounds(500, 130, 42, 42);
        getContentPane().add(info1);

        JButton Nombre = new JButton("Per nombre");
        Nombre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object[] opcions = {"Fàcil", "Mitjana", "Difícil"};
                int resp = JOptionPane.showOptionDialog(null,
                        "Seleccioni una dificultat:",
                        "Selector de dificultats",
                        JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null, opcions, null);
                switch (resp) {
                    case 0:
                        JFrame f=new JFrame("Fàcil");
                        String dataf[][] = new String[10][3];
                        for(int i = 0; i < 10; ++i) {
                            String s = String.valueOf(i+1);
                            dataf[i][0] = s;
                            dataf[i][1] = CP.user_ranquing_nombre(i, "Fàcil");
                            dataf[i][2] = CP.nombre_ranquing(i, "Fàcil");
                        }
                        String columnf[]={"Posició","Usuari","Nombre"};
                        JTable tf=new JTable(dataf,columnf) {
                            public boolean isCellEditable(int row, int column) {
                                return false;
                            }
                        };
                        tf.setRowHeight(30);
                        tf.setBounds(30,40,200,300);
                        JScrollPane spf=new JScrollPane(tf);
                        f.add(spf);
                        f.setSize(300,400);
                        f.setLocationRelativeTo(null);
                        f.setVisible(true);
                        break;
                    case 1:
                        JFrame m=new JFrame("Mitjana");
                        String datam[][] = new String[10][3];
                        for(int i = 0; i < 10; ++i) {
                            String s = String.valueOf(i+1);
                            datam[i][0] = s;
                            datam[i][1] = CP.user_ranquing_nombre(i, "Mitjana");
                            datam[i][2] = CP.nombre_ranquing(i, "Mitjana");
                        }
                        String columnm[]={"Posició","Usuari","Nombre"};
                        JTable tm=new JTable(datam,columnm) {
                            public boolean isCellEditable(int row, int column) {
                                return false;
                            }
                        };
                        tm.setRowHeight(30);
                        tm.setBounds(30,40,200,300);
                        JScrollPane spm=new JScrollPane(tm);
                        m.add(spm);
                        m.setSize(300,400);
                        m.setLocationRelativeTo(null);
                        m.setVisible(true);
                        break;
                    case 2:
                        JFrame d=new JFrame("Difícil");
                        String datad[][] = new String[10][3];
                        for(int i = 0; i < 10; ++i) {
                            String s = String.valueOf(i+1);
                            datad[i][0] = s;
                            datad[i][1] = CP.user_ranquing_nombre(i, "Difícil");
                            datad[i][2] = CP.nombre_ranquing(i, "Difícil");
                        }
                        String columnd[]={"Posició","Usuari","Nombre"};
                        JTable td=new JTable(datad,columnd) {
                            public boolean isCellEditable(int row, int column) {
                                return false;
                            }
                        };
                        td.setRowHeight(30);
                        td.setBounds(30,40,200,300);
                        JScrollPane spd=new JScrollPane(td);
                        d.add(spd);
                        d.setSize(300,400);
                        d.setLocationRelativeTo(null);
                        d.setVisible(true);
                        break;
                }
            }
        });
        Nombre.setFont(new Font("Tahoma", Font.PLAIN, 20));
        Nombre.setBounds(190, 220, 300, 42);
        getContentPane().add(Nombre);

        JButton info2 = new JButton("i");
        info2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Mostra el rànquing per nombre de kakuros resolts en la dificultat seleccionada.");
            }
        });
        info2.setFont(new Font("Tahoma", Font.PLAIN, 25));
        info2.setBounds(500, 220, 42, 42);
        getContentPane().add(info2);

        JButton Reinicia = new JButton("Reiniciar");
        Reinicia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int resp = JOptionPane.showConfirmDialog(null, "Aquesta opció eliminarà permanentment el rànquing.",
                        "Està segur?", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
                if (resp == 0) {
                    CP.reiniciar_ranquing();
                }
            }
        });
        Reinicia.setFont(new Font("Tahoma", Font.PLAIN, 16));
        Reinicia.setBounds(392, 300, 98, 25);
        getContentPane().add(Reinicia);

    }
}
