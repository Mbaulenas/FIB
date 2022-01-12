package Presentacio;

import Controladors.Controlador_Presentacio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//COMENTARI CLASSE:
//Classe que correspon al panel de la partida. Sencarrega de mostrar una partida amb les seves funcionalitats.

public class Vista_Partida extends JFrame{

    Controlador_Presentacio CP;

    private String[][] k;
    private String[][] sol;
    private String id;
    private int n;
    private int m;
    public JTextField[][] tf;
    public Integer aux;

    public Vista_Partida(Controlador_Presentacio CP) {
        getContentPane().setLayout(null);
        setSize(700, 500);
        setLocationRelativeTo(null);
        setTitle("Partida");

        aux = CP.getTemps();
        JLabel temps = new JLabel(aux.toString());
        temps.setFont(new Font("Tahoma", Font.PLAIN, 25));
        temps.setBounds(335, 35, 285, 37);
        getContentPane().add(temps);
        ActionListener updateClockAction = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ++aux;
                String s = Integer.toString(aux);
                temps.setText(s);
                getContentPane().add(temps);
            }
        };
        Timer time = new Timer(1000, updateClockAction);
        time.start();

        sol = CP.carregar_solucio();
        JButton pista = new JButton("Pista");
        pista.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean trobat = false;
                for(int i = 0; i < n && !trobat; ++i) {
                    for (int j = 0; j < m && !trobat; ++j) {
                        if(k[i][j] == "?" && tf[i][j].getText().length() == 0) {
                            trobat = true;
                            tf[i][j].setText(sol[i][j]);
                        }
                    }
                }
            }
        });
        pista.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pista.setBounds(565, 54, 105, 23);
        getContentPane().add(pista);

        JButton ajuda= new JButton("Ajuda");
        ajuda.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,
                        "Cal omplir les caselles lliures del 1 al 9.\n" +
                        "La suma de cada bloc horitzontal de cel·les blanques ha de ser igual al nombre clau de l'esquerra.\n" +
                        "La suma de cada bloc vertical de cel·les blanques ha de ser igual al nombre clau de dalt.");
            }
        });
        ajuda.setFont(new Font("Tahoma", Font.PLAIN, 15));
        ajuda.setBounds(565, 25, 105, 23);
        getContentPane().add(ajuda);

        JButton comprova= new JButton("Comprova");
        comprova.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean tot = true;
                for(int i = 0; i < n; ++i) {
                    for (int j = 0; j < m; ++j) {
                        if (k[i][j].equals("?") && (tf[i][j].getText().length() == 0 || tf[i][j].getText().equals(sol[i][j]))) {
                            tf[i][j].setBackground(Color.LIGHT_GRAY);
                            if (k[i][j].equals("?") && (tf[i][j].getText().length() == 0))
                                tot = false;
                        }
                        else if (k[i][j].equals("?") && !(tf[i][j].getText().equals(sol[i][j]))) {
                            tf[i][j].setBackground(Color.RED);
                            tot = false;
                        }
                        if (i == n-1 && j == m-1 && tot) {
                            time.stop();
                            JOptionPane.showMessageDialog(null,
                                    "FELICITATS! Has acabat el kakuro correctament.");
                            String id = CP.guardar_partida(aux, k);
                            CP.actualizar_ranquing(aux);
                            CP.eliminar_partida(id);
                            CP.tornar_menu("Partida");
                        }
                    }
                }
            }
        });
        comprova.setFont(new Font("Tahoma", Font.PLAIN, 15));
        comprova.setBounds(565, 83, 105, 23);
        getContentPane().add(comprova);

        k = CP.carregar_kakuro();
        n = k.length;
        m = k[0].length;
        tf = new JTextField[n][n];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                tf[i][j] = new JTextField();
                getContentPane().add(tf[i][j]);
                if(k[i][j].equals("?")) {
                    tf[i][j].setBounds(350-(40*n/2) + (j*40), 80 + (i*40), 40, 40);
                    tf[i][j].setText("");
                    tf[i][j].setEditable(true);
                    tf[i][j].setBackground(Color.LIGHT_GRAY);
                }
                else if (k[i][j].equals("*")) {
                    tf[i][j].setBounds(350-(40*n/2) + (j*40), 80 + (i*40), 40, 40);
                    tf[i][j].setText("");
                    tf[i][j].setEditable(false);
                    tf[i][j].setBackground(Color.BLACK);
                }
                else if (k[i][j].contains("F") || k[i][j].contains("C")){
                    if (k[i][j].contains("F") && k[i][j].contains("C")) {
                        String s = k[i][j];
                        String x = "";
                        String y = "";
                        boolean found = false;
                        for(int t = 1; t < s.length(); t++){
                            if(s.charAt(t) != 'F' && !found) x = x+s.charAt(t);
                            else if(s.charAt(t) != 'F' && found) y = y+s.charAt(t);
                            else found = true;
                        }
                        tf[i][j].setBounds(350-(40*n/2) + (j*40), 80 + (i*40), 40, 40);
                        tf[i][j].setText(x + "\\" + y);
                        tf[i][j].setEditable(false);
                    }
                    else if (k[i][j].contains("C")) {
                        String s = k[i][j];
                        String x = "";
                        for(int t = 1; t < s.length(); t++)
                            x = x+s.charAt(t);
                        tf[i][j].setBounds(350-(40*n/2) + (j*40), 80 + (i*40), 40, 40);
                        tf[i][j].setText(x + "\\ ");
                        tf[i][j].setEditable(false);
                    }
                    else if (k[i][j].contains("F")) {
                        String s = k[i][j];
                        String y = "";
                        for(int t = 1; t < s.length(); t++)
                            y = y+s.charAt(t);
                        tf[i][j].setBounds(350-(40*n/2) + (j*40), 80 + (i*40), 40, 40);
                        tf[i][j].setText(" \\" + y);
                        tf[i][j].setEditable(false);
                    }
                }
                else {
                    tf[i][j].setBounds(350-(40*n/2) + (j*40), 80 + (i*40), 40, 40);
                    tf[i][j].setText(k[i][j]);
                    tf[i][j].setEditable(true);
                    tf[i][j].setBackground(Color.LIGHT_GRAY);
                }
                tf[i][j].setHorizontalAlignment(JTextField.CENTER);
                getContentPane().add(tf[i][j]);
            }
        }

        JButton Enrere = new JButton("Enrere");
        Enrere.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                time.stop();
                String[][] kak = new String[n][m];
                for (int i = 0; i < n; ++i) {
                    for (int j = 0; j < m; ++j) {
                        if (k[i][j].equals("?")){
                            if (tf[i][j].getText().length() == 0)
                                kak[i][j] = "?";
                            else
                                kak[i][j] = tf[i][j].getText();
                        }
                        else
                            kak[i][j] = k[i][j];
                    }
                }
                String id = CP.guardar_partida(aux, kak);
                JOptionPane.showMessageDialog(null,
                        "L'dentificador de la partida és " + id + ".");
                CP.tornar_menu("Partida");
            }
        });
        Enrere.setFont(new Font("Tahoma", Font.PLAIN, 15));
        Enrere.setBounds(30, 25, 90, 23);
        getContentPane().add(Enrere);

        JButton Maquina = new JButton("Màquina");
        Maquina.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                long aux = CP.maquina_resol();
                JOptionPane.showMessageDialog(null,
                        "La màquina ha resolt el kakuro en un temps de: " +
                                aux + " mil·lisegons.\n");
            }
        });
        Maquina.setFont(new Font("Tahoma", Font.PLAIN, 15));
        Maquina.setBounds(30, 410, 90, 23);
        getContentPane().add(Maquina);
    }
}