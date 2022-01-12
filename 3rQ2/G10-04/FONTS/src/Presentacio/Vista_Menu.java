package Presentacio;

import Controladors.Controlador_Presentacio;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

//COMENTARI CLASSE:
//Classe que correspon al panel del menu. Permet al usuari navegar per les funcionalitats del programa i tancar la seva
//sessio.

public class Vista_Menu extends JFrame {
    private Controlador_Presentacio CP;

    public Vista_Menu(Controlador_Presentacio CP) {
        getContentPane().setLayout(null);
        setSize(700, 500);
        setLocationRelativeTo(null);
        setTitle("Kakuros");

        JLabel label = new JLabel("MENÚ");
        label.setFont(new Font("Tahoma", Font.PLAIN, 30));
        label.setBounds(300, 11, 285, 37);
        getContentPane().add(label);

        JButton NouKakuro = new JButton("Nou kakuro");
        NouKakuro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SpinnerNumberModel model = new SpinnerNumberModel(5, 5, 9, 1);
                JSpinner n = new JSpinner(model);

                JComboBox<String> dif = new JComboBox<>(new String[] {"Fàcil", "Mitjana", "Difícil"});

                JPanel myPanel = new JPanel();
                myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.Y_AXIS));

                myPanel.add(new JLabel("Nombre de files i columnes:"), CENTER_ALIGNMENT);
                myPanel.add(n);
                myPanel.add(new JLabel("Dificultat:"), CENTER_ALIGNMENT);
                myPanel.add(dif);

                int resp = JOptionPane.showConfirmDialog(null, myPanel,
                        "Si us plau introdueixi les dades del kakuro",
                        JOptionPane.OK_CANCEL_OPTION);
                if (resp == 0) {
                    CP.nou_kakuro((Integer)n.getValue(), (String)dif.getSelectedItem());
                    CP.jugar();
                }
            }
        });
        NouKakuro.setFont(new Font("Tahoma", Font.PLAIN, 20));
        NouKakuro.setBounds(190, 110, 300, 42);
        getContentPane().add(NouKakuro);

        JButton info1 = new JButton("i");
        info1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Jugar una nova partida amb un kakuro nou.");
            }
        });
        info1.setFont(new Font("Tahoma", Font.PLAIN, 25));
        info1.setBounds(500, 110, 42, 42);
        getContentPane().add(info1);


        JButton Repositori = new JButton("Repositori de kakuros");
        Repositori.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox<String> dif = new JComboBox<>(new String[] {"Jugar", "Eliminar", "Reiniciar"});

                String[] s = CP.llistar_kakuros();

                if(s.length == 0) {
                    JOptionPane.showMessageDialog(null, "No tens cap kakuro al repositori.");
                }
                else {
                    for (int i = 0; i < s.length; ++i) {
                        s[i] = "     " + s[i] + "     ";
                    }
                    JList b= new JList(s);

                    JPanel myPanel = new JPanel();
                    myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.Y_AXIS));
                    myPanel.add(new JLabel("Escull una acció a realitzar:"), CENTER_ALIGNMENT);
                    myPanel.add(dif);
                    myPanel.add(new JLabel("Escull un identificador:"), CENTER_ALIGNMENT);
                    myPanel.add(b);

                    int resp = JOptionPane.showConfirmDialog(null, myPanel,
                            "Si us plau seleccioni un kakuro",
                            JOptionPane.OK_CANCEL_OPTION);
                    if (resp == 0) {
                        if (dif.getSelectedItem().equals("Reiniciar")) {
                            int r = JOptionPane.showConfirmDialog(null, "Aquesta opció eliminarà permanentment el repositori.",
                                    "Està segur?", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
                            if (r == 0) {
                                CP.reiniciar_repositori();
                            }
                        }
                        else if (dif.getSelectedItem().equals("Eliminar") || dif.getSelectedItem().equals("Jugar")) {
                            if (b.getSelectedValue() == null) {
                                JOptionPane.showConfirmDialog(null, "Cal seleccionar un identificador de kakuro vàlid.",
                                        "Error", JOptionPane.OK_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
                            }
                            else {
                                String aux = b.getSelectedValue().toString();
                                aux = aux.replace(" ", "");
                                if (dif.getSelectedItem().equals("Eliminar")) {
                                    int r = JOptionPane.showConfirmDialog(null, "Aquesta opció eliminarà permanentment el kakuro.",
                                            "Està segur?", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
                                    if (r == 0) {
                                        CP.eliminar_kakuro(aux);
                                    }
                                }
                                else {
                                    CP.jugar_kakuro(aux);
                                    CP.jugar();
                                }
                            }
                        }
                    }
                }
            }
        });
        Repositori.setFont(new Font("Tahoma", Font.PLAIN, 20));
        Repositori.setBounds(190, 170, 300, 42);
        getContentPane().add(Repositori);

        JButton info2 = new JButton("i");
        info2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Veure els kakuros desats.");
            }
        });
        info2.setFont(new Font("Tahoma", Font.PLAIN, 25));
        info2.setBounds(500, 170, 42, 42);
        getContentPane().add(info2);

        JButton Proposar = new JButton("Proposar kakuro");
        Proposar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser selectorArchivos = new JFileChooser();
                FileFilter filtro = new FileNameExtensionFilter("Archius de text (.txt)", "txt");
                selectorArchivos.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                selectorArchivos.setFileFilter(filtro);

                int resultado = selectorArchivos.showOpenDialog(null);

                File archivo = selectorArchivos.getSelectedFile();

                if ((archivo == null) || (archivo.getName().equals(""))) {
                    JOptionPane.showMessageDialog(null,
                            "Nom d'arxiu invàlid",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    String contingut = "";
                    Scanner scan = null;
                    try {
                        scan = new Scanner(archivo);
                    } catch (FileNotFoundException fileNotFoundException) {
                        fileNotFoundException.printStackTrace();
                    }
                    while (scan.hasNextLine()) contingut = contingut.concat(scan.nextLine() + "\n");
                    scan.close();
                    if (CP.proposar_kakuro(contingut)) {
                        String aux = CP.guardar_kakuro(contingut);
                        int r = JOptionPane.showConfirmDialog(null,
                                "Desitja jugar el kakuro ara?",
                                "Jugar?",
                                JOptionPane.YES_NO_OPTION);
                        if (r == 0) {
                            CP.nova_proposta();
                            CP.jugar();
                        }
                        else {
                            JOptionPane.showConfirmDialog(null,
                                    "L'identificador de Kakuro es: " + aux + "\n",
                                    "Identificador",
                                    JOptionPane.DEFAULT_OPTION);
                        }
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "El kakauro proposat no és vàlid");
                    }
                }
            }
        });
        Proposar.setFont(new Font("Tahoma", Font.PLAIN, 20));
        Proposar.setBounds(190, 230, 300, 42);
        getContentPane().add(Proposar);

        JButton info3 = new JButton("i");
        info3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Pujar un kakuro amb extensió .txt, " +
                        "primerament cal afegir el nombre de files i columnes separats per una coma.\nA la següent línea " +
                        "el kakuro haurà d'estar en format: * per cel·les negres, ? \n per blanques, F+nombre per " +
                        "restricció de fila i C+nombre per restricció de columna. \n" +
                        "Cada fila haurà d'estar en una fila diferent i cada cel·la separada per una coma (,). \n" +
                        "Al final caurà dir la dificultat: fàcil, mitjana o difícil.");
            }
        });
        info3.setFont(new Font("Tahoma", Font.PLAIN, 25));
        info3.setBounds(500, 230, 42, 42);
        getContentPane().add(info3);

        JButton Partides = new JButton("Partides començades");
        Partides.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox<String> dif = new JComboBox<>(new String[] {"Jugar", "Eliminar", "Eliminar partides a mitjes"});

                String[] s = CP.llistar_partides();
                if (s[0].equals("no")) {
                    JOptionPane.showMessageDialog(null, "No tens cap partida començada.");
                }
                else {
                    for (int i = 0; i < s.length; ++i) {
                        s[i] = "     " + s[i] + "     ";
                    }
                    JList b= new JList(s);

                    JPanel myPanel = new JPanel();
                    myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.Y_AXIS));
                    myPanel.add(new JLabel("Escull una acció a realitzar:"), CENTER_ALIGNMENT);
                    myPanel.add(dif);
                    myPanel.add(new JLabel("Escull un identificador:"), CENTER_ALIGNMENT);
                    myPanel.add(b);

                    int resp = JOptionPane.showConfirmDialog(null, myPanel,
                            "Si us plau seleccioni una partida",
                            JOptionPane.OK_CANCEL_OPTION);
                    if (resp == 0) {
                        if (dif.getSelectedItem().equals("Eliminar") || dif.getSelectedItem().equals("Jugar")) {

                            if (b.getSelectedValue() == null) {
                                JOptionPane.showConfirmDialog(null, "Cal seleccionar un identificador de partida vàlid.",
                                        "Error", JOptionPane.OK_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
                            }
                            else {
                                String aux = b.getSelectedValue().toString();
                                aux = aux.replace(" ", "");
                                if (dif.getSelectedItem().equals("Eliminar")) {
                                    int r = JOptionPane.showConfirmDialog(null, "Aquesta opció eliminarà permanentment la partida.",
                                            "Està segur?", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
                                    if (r == 0) {
                                        CP.eliminar_partida(aux);
                                    }
                                }
                                else {
                                    CP.jugar_partida(aux);
                                    CP.jugar();
                                }
                            }
                        }
                        else {
                            int r = JOptionPane.showConfirmDialog(null, "Aquesta opció eliminarà permanentment totes les partides a mitjes.",
                                    "Està segur?", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
                            if (r == 0) {
                                CP.eliminar_partides_mitjes();
                            }
                        }
                    }
                }
            }
        });
        Partides.setFont(new Font("Tahoma", Font.PLAIN, 20));
        Partides.setBounds(190, 290, 300, 42);
        getContentPane().add(Partides);

        JButton info4 = new JButton("i");
        info4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Veure les partides començades i no finalitzades.");
            }
        });
        info4.setFont(new Font("Tahoma", Font.PLAIN, 25));
        info4.setBounds(500, 290, 42, 42);
        getContentPane().add(info4);

        JButton Ranquing = new JButton("Rànquing");
        Ranquing.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CP.visualitzar_ranquing();
            }
        });
        Ranquing.setFont(new Font("Tahoma", Font.PLAIN, 20));
        Ranquing.setBounds(190, 350, 300, 42);
        getContentPane().add(Ranquing);

        JButton info5 = new JButton("i");
        info5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Visualitzar el rànquing de tots els usuaris.");
            }
        });
        info5.setFont(new Font("Tahoma", Font.PLAIN, 25));
        info5.setBounds(500, 350, 42, 42);
        getContentPane().add(info5);

        JButton close = new JButton("Tanca sessió");
        close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object[] opcions = {"Si", "No", "Cancel·la"};
                int resp = JOptionPane.showOptionDialog(null,
                        "Segur que vol tancar la sessió?", "Confirmació",
                        JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, opcions, opcions[1]);
                if (resp == 0) {
                    CP.logout();
                }
            }
        });
        close.setFont(new Font("Tahoma", Font.PLAIN, 15));
        close.setBounds(532, 54, 120, 23);
        getContentPane().add(close);
    }
}
