import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Vista_menu extends JFrame implements ActionListener{

    //main
    private Container container = getContentPane();
    private JRadioButton botojugar = new JRadioButton("JUGAR");
    private JRadioButton botoranking = new JRadioButton("CONSULTAR RANKING");
    private JRadioButton botopartida = new JRadioButton("CARREGAR PARTIDA");
    private ButtonGroup bg = new ButtonGroup();




    //jugar
    private JRadioButton rb1 = new JRadioButton("Jugador contra jugador");
    private JRadioButton rb2 = new JRadioButton("Jugador contra màquina");
    private JRadioButton rb3 = new JRadioButton("Màquina contra màquina");
    private ButtonGroup bg2 = new ButtonGroup();

    private JLabel colortext = new JLabel("Color jugador 1");
    private JRadioButton rbw = new JRadioButton("Blanques");
    private JRadioButton rbb = new JRadioButton("Negres");
    private ButtonGroup bg3 = new ButtonGroup();

    private JLabel taulerstext = new JLabel("Tauler");
    private JComboBox<String> taulers = new JComboBox<String>(importar_taulers());

    private JLabel p2text = new JLabel("Nom jugador 2");
    private JTextField p2username= new JTextField(20);

    private JLabel m1text = new JLabel("Profunditat algorisme 1");
    private JTextField m1prof= new JTextField(5);
    private JLabel m2text = new JLabel("Profunditat algorisme 2");
    private JTextField m2prof= new JTextField(5);


    private JButton start = new JButton("Començar");

    //carregar
    private JLabel partidestext = new JLabel("Selecciona partida a carregar");
    private JComboBox<String> partides = new JComboBox<String>(importar_partides());
    private JButton carregar = new JButton("Carregar");

    //ranking
    private JCheckBox cb1 = new JCheckBox("Horitzonal");
    private JCheckBox cb2 = new JCheckBox("Vertical");
    private JCheckBox cb3 = new JCheckBox("Diagonal");
    private JButton consultar = new JButton("Consultar");




    public Vista_menu(){

        container.setLayout(null);
        //container.setJMenuBar(crear_menu());
        setJMenuBar(crear_menu());
        setTitle("Menú");
        setSize(800,800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);




        //main
        botojugar.setBounds(100, 50, 150, 30);
        botopartida.setBounds(250, 50, 200, 30);
        botoranking.setBounds(450, 50, 200, 30);
        botopartida.addActionListener(this);
        botoranking.addActionListener(this);
        botojugar.addActionListener(this);
        bg.add(botojugar);
        bg.add(botopartida);
        bg.add(botoranking);
        container.add(botojugar);



        //main
        container.add(botopartida);
        container.add(botoranking);

        //jugar

        rb1.setBounds(100,200,250,30);
        rb2.setBounds(100,250,250,30);
        rb3.setBounds(100,300,250,30);
        colortext.setBounds(100,400,200,30);
        rbw.setBounds(300,400,150,30);
        rbb.setBounds(450,400,150,30);
        taulerstext.setBounds(100,450,150,30);
        taulers.setBounds(300,450,200,30);
        p2text.setBounds(100,500,200,30);
        p2username.setBounds(300,500,200,30);
        m1text.setBounds(100,500,200,30);
        m1prof.setBounds(300,500,200,30);
        m2text.setBounds(100,550,200,30);
        m2prof.setBounds(300,550,200,30);
        start.setBounds(100,600,200,60);

        partidestext.setBounds(100,200,250,30);
        partides.setBounds(350, 200, 150, 30);
        carregar.setBounds(100,300,200,60);

        cb1.setBounds(100,200,150,30);
        cb2.setBounds(250,200,150,30);
        cb3.setBounds(400,200,150,30);
        consultar.setBounds(100,300,200,60);



        rb1.addActionListener(this);
        rb2.addActionListener(this);
        rb3.addActionListener(this);
        start.addActionListener(this);
        carregar.addActionListener(this);
        consultar.addActionListener(this);
        taulers.setSelectedIndex(0);

        container.add(rb1);
        container.add(rb2);
        container.add(rb3);
        container.add(colortext);
        container.add(rbb);
        container.add(rbw);
        container.add(taulers);
        container.add(taulerstext);
        container.add(p2text);
        container.add(p2username);
        container.add(m1text);
        container.add(m1prof);
        container.add(m2text);
        container.add(m2prof);
        container.add(start);

        container.add(partidestext);
        container.add(partides);
        container.add(carregar);

        container.add(cb1);
        container.add(cb2);
        container.add(cb3);
        container.add(consultar);

        bg2.add(rb1);
        bg2.add(rb2);
        bg2.add(rb3);

        bg3.add(rbb);
        bg3.add(rbw);

        amagar();
        partidestext.setVisible(false);
        partides.setVisible(false);
        carregar.setVisible(false);

        cb1.setVisible(false);
        cb2.setVisible(false);
        cb3.setVisible(false);
        consultar.setVisible(false);


    }

    private String[] importar_taulers(){

        String[] nom_taulers = { "Default", "Bakanito", "Fresco", "Si", "Pig" };

        return nom_taulers;
    }

    private String[] importar_partides(){
        String[] nom_taulers = { "Partida 1", "Partida 2", "a", "Si", "Pig" };
        return nom_taulers;
    }

    private void amagar(){

        rb1.setVisible(false);
        rb2.setVisible(false);
        rb3.setVisible(false);
        colortext.setVisible(false);

        rbw.setVisible(false);
        rbb.setVisible(false);

        taulers.setVisible(false);
        taulerstext.setVisible(false);

        p2text.setVisible(false);
        p2username.setVisible(false);

        m1prof.setVisible(false);
        m1text.setVisible(false);
        m2prof.setVisible(false);
        m2text.setVisible(false);

        start.setVisible(false);

    }

    private void jugar(boolean actiu){

        colortext.setVisible(actiu);
        rbw.setVisible(actiu);
        rbb.setVisible(actiu);
        taulers.setVisible(actiu);
        taulerstext.setVisible(actiu);
        start.setVisible(true);
    }

    private void amaga_jugar(){

        colortext.setVisible(false);
        rbw.setVisible(false);
        rbb.setVisible(false);
        taulers.setVisible(false);
        taulerstext.setVisible(false);
        start.setVisible(false);
    }

    private void jcj(boolean actiu){
        m1prof.setVisible(false);
        m1text.setVisible(false);
        m2prof.setVisible(false);
        m2text.setVisible(false);

        p2text.setVisible(actiu);
        p2username.setVisible(actiu);
    }

    private void jcm(boolean actiu){
        m1prof.setVisible(actiu);
        m1text.setVisible(actiu);
        m2prof.setVisible(false);
        m2text.setVisible(false);
        p2text.setVisible(false);
        p2username.setVisible(false);
    }

    private void mcm(boolean actiu){
        m1prof.setVisible(actiu);
        m1text.setVisible(actiu);
        m2prof.setVisible(actiu);
        m2text.setVisible(actiu);
        p2text.setVisible(false);
        p2username.setVisible(false);
    }

    public void actionPerformed(ActionEvent e){



        if ( e.getSource() == start ){

            String b = new String();
            String w = new String();
            String p2 = p2username.getText();
            String m1 = m1prof.getText();
            String m2 = m2prof.getText();
            boolean correcte = true;

            if(rb1.isSelected()){

                if(rbw.isSelected()){
                    w = "J1";
                    b = "J2";
                }
                else if(rbb.isSelected()){
                    w = "J2";
                    b = "J1";
                }
                else{
                    JOptionPane.showMessageDialog(null, "Selecciona un color pel jugador 1");
                    correcte = false;
                }

                if(p2.length()==0){
                    JOptionPane.showMessageDialog(null, "Introdueix nom pel jugador 2");
                    correcte = false;
                }

                if(correcte) JOptionPane.showMessageDialog(null, "Mode de joc: JcJ, Blanques: " + w + ", Nom J2: " + p2);

            }

            else if(rb2.isSelected()){
                if(rbw.isSelected()){
                    w = "J1";
                    b = "J2";
                }
                else if(rbb.isSelected()){
                    w = "J2";
                    b = "J1";
                }
                else {
                    JOptionPane.showMessageDialog(null, "Selecciona un color pel jugador 1");
                    correcte = false;
                }

                if(m1.length()==0){
                    JOptionPane.showMessageDialog(null, "Introdueix profunditat per la màquina");
                    correcte = false;
                }


                if(correcte) JOptionPane.showMessageDialog(null, "Mode de joc: JcM, Blanques: " + w + ", Prof M1: " + m1);

            }
            else if(rb3.isSelected()){
                if(rbw.isSelected()){
                    w = "J1";
                    b = "J2";
                }
                else if(rbb.isSelected()){
                    w = "J2";
                    b = "J1";
                }
                else {
                    JOptionPane.showMessageDialog(null, "Selecciona un color pel jugador 1");
                    correcte = false;
                }

                if(m1.length()==0){
                    JOptionPane.showMessageDialog(null, "Introdueix profunditat per la màquina 1");
                    correcte = false;
                }


                if(m2.length()==0){
                    JOptionPane.showMessageDialog(null, "Introdueix profunditat per la màquina 2");
                    correcte = false;
                }


                if(correcte) JOptionPane.showMessageDialog(null, "Mode de joc: McM, Blanques: " + w + ", Prof M1: " + m1+ ", Prof M2: " + m2);

            }
            else{
                JOptionPane.showMessageDialog(null, "Selecciona un tipus de partida");
            }
        }

        if(e.getSource()==carregar){
            String test = partides.getSelectedItem().toString();
            JOptionPane.showMessageDialog(null, "Seleccionada: " + test);
        }

        if(e.getSource()==consultar){
            boolean b1 = cb1.isSelected();
            boolean b2 = cb2.isSelected();
            boolean b3 = cb3.isSelected();
            if(!b1&&!b2&&!b3) JOptionPane.showMessageDialog(null, "Escull el tipus de ranking");
            else JOptionPane.showMessageDialog(null, "Ranking a carregar: " + b1+b2+b3);
        }

    }

    private JMenuBar crear_menu(){

        JMenuBar miMenuBar = new JMenuBar();

        JMenu menupartida = new JMenu("Nova partida");
        JMenuItem newgame1 = new JMenuItem("Jugador contra jugador");
        newgame1.addActionListener
                (new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                       mostra_jugar();
                       jcj(true);
                    }
                });

        JMenuItem newgame2 = new JMenuItem("Jugador contra màquina");
        newgame2.addActionListener
                (new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        mostra_jugar();
                        jcm(true);
                    }
                });

        JMenuItem newgame3 = new JMenuItem("Màquina contra màquina");
        newgame3.addActionListener
                (new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        mostra_jugar();
                        mcm(true);
                    }
                });

        menupartida.add(newgame1);
        menupartida.add(newgame2);
        menupartida.add(newgame3);
        miMenuBar.add(menupartida);

        JMenu menuload = new JMenu("Carregar partida");
        miMenuBar.add(menuload);

        JMenu menuuser= new JMenu("Veure usuari");
        miMenuBar.add(menuuser);

        JMenu menuranking = new JMenu("Veure ranking");
        JCheckBoxMenuItem cbMenuItem1 = new JCheckBoxMenuItem("Vertical");
        JCheckBoxMenuItem cbMenuItem2 = new JCheckBoxMenuItem("Horitzontal");
        JCheckBoxMenuItem cbMenuItem3 = new JCheckBoxMenuItem("Diagonal");
        menuranking.add(cbMenuItem1);
        menuranking.add(cbMenuItem2);
        menuranking.add(cbMenuItem3);
        menuranking.addSeparator();
        JMenuItem botoload = new JMenuItem("Consulta");
        menuranking.add(botoload);
        miMenuBar.add(menuranking);

        JMenu menutaulers = new JMenu("Gestionar taulers");
        JMenuItem creartauler = new JMenuItem("Crear tauler");
        JMenu submenu = new JMenu("Esborrar un tauler");
        menutaulers.add(creartauler);
        menutaulers.add(submenu);
        miMenuBar.add(menutaulers);




        return miMenuBar;
    }


    public static void main(String[] args) {

        new Vista_menu();

    }

}

