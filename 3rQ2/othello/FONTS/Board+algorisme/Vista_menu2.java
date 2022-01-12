import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Vista_menu2 extends JFrame implements ActionListener{

    //private JFrame f = new JFrame();

    //menu
    private JMenuItem newgame1 = new JMenuItem("Jugador contra jugador");
    private JMenuItem newgame2 = new JMenuItem("Jugador contra màquina");
    private JMenuItem newgame3 = new JMenuItem("Màquina contra màquina");
    private JMenu menuload = new JMenu("Gestionar partides guardades");
    private JMenuItem load1 = new JMenuItem("Carregar una partida");
    private JMenuItem load2 = new JMenuItem("Esborrar una partida");

    //jugar main
    private JLabel colortext = new JLabel("Color jugador 1");
    private JRadioButton rbw = new JRadioButton("Blanques");
    private JRadioButton rbb = new JRadioButton("Negres");
    private ButtonGroup bg3 = new ButtonGroup();
    private JLabel taulerstext = new JLabel("Tauler");
    private JComboBox<String> taulers = new JComboBox<String>(importar_taulers());
    private JButton start1 = new JButton("Començar");
    private JButton start2 = new JButton("Començar");
    private JButton start3 = new JButton("Començar");

    //jcj
    private JLabel p2text = new JLabel("Nom jugador 2");
    private JTextField p2username= new JTextField(20);
    private JLabel p2passtext = new JLabel("Contrasenya jugador 2");
    private JPasswordField p2pass = new JPasswordField();
    private JCheckBox cb1 = new JCheckBox("Registrar");

    //jcm
    private JLabel m1text = new JLabel("Profunditat algorisme 1");
    private JTextField m1prof= new JTextField(5);
    private JComboBox<String> m1alg = new JComboBox<String>(importar_algorismes());

    //mcm
    private JLabel m2text = new JLabel("Profunditat algorisme 2");
    private JTextField m2prof= new JTextField(5);
    private JComboBox<String> m2alg = new JComboBox<String>(importar_algorismes());

    //carregar partides
    private JLabel partidestext = new JLabel("Selecciona partida a carregar");
    private JLabel deletetext = new JLabel("Selecciona partida a esborrar");
    private JComboBox<String> partides = new JComboBox<String>(importar_partides());
    private JButton carregar = new JButton("Carregar");
    private JButton delete = new JButton("Esborrar");

    //ranking
    private JMenuItem botoload = new JMenuItem("Consulta");
    private JCheckBoxMenuItem cbMenuItem1 = new JCheckBoxMenuItem("Vertical");
    private JCheckBoxMenuItem cbMenuItem2 = new JCheckBoxMenuItem("Horitzontal");
    private JCheckBoxMenuItem cbMenuItem3 = new JCheckBoxMenuItem("Diagonal");

    //gestionar taulers

    private JLabel tdtext = new JLabel("Selecciona tauler a esborrar");
    private JComboBox<String> td = new JComboBox<String>(importar_taulers());
    private JButton tdb = new JButton("Esborrar");
    private JMenuItem submenu = new JMenuItem("Esborrar un tauler");
    private JMenuItem creartauler = new JMenuItem("Crear tauler");

    private JMenuBar crear_menu(){

        JMenuBar miMenuBar = new JMenuBar();

        JMenu menupartida = new JMenu("Nova partida");

        menupartida.add(newgame1);
        menupartida.add(newgame2);
        menupartida.add(newgame3);
        miMenuBar.add(menupartida);

        menuload.add(load1);
        menuload.add(load2);
        miMenuBar.add(menuload);

        JMenu menuuser= new JMenu("Veure usuari");
        miMenuBar.add(menuuser);

        JMenu menuranking = new JMenu("Veure ranking");

        menuranking.add(cbMenuItem1);
        menuranking.add(cbMenuItem2);
        menuranking.add(cbMenuItem3);
        menuranking.addSeparator();

        menuranking.add(botoload);
        miMenuBar.add(menuranking);

        JMenu menutaulers = new JMenu("Gestionar taulers");




        menutaulers.add(creartauler);
        menutaulers.add(submenu);
        miMenuBar.add(menutaulers);


        return miMenuBar;


    }

    public Vista_menu2(){

        setLayout(null);
        setTitle("Menú");
        setSize(800,800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setJMenuBar(crear_menu());
        setVisible(true);

        newgame1.addActionListener(this);
        newgame2.addActionListener(this);
        newgame3.addActionListener(this);
        start1.addActionListener(this);
        start2.addActionListener(this);
        start3.addActionListener(this);
        carregar.addActionListener(this);
        delete.addActionListener(this);
        botoload.addActionListener(this);
        load1.addActionListener(this);
        load2.addActionListener(this);
        submenu.addActionListener(this);
        creartauler.addActionListener(this);
        tdb.addActionListener(this);

        //main
        colortext.setBounds(100,100,200,30);
        rbw.setBounds(300,100,150,30);
        rbb.setBounds(450,100,150,30);
        taulerstext.setBounds(100,150,150,30);
        taulers.setBounds(300,150,200,30);
        start1.setBounds(100,350,200,60);
        start2.setBounds(100,350,200,60);
        start3.setBounds(100,350,200,60);
        add(colortext);
        add(rbw);
        add(rbb);
        bg3.add(rbw);
        bg3.add(rbb);
        add(taulerstext);
        add(deletetext);
        add(taulers);
        add(start1);
        add(start2);
        add(start3);
        jugar(false);

        //jcj
        p2text.setBounds(100,250,200,30);
        p2username.setBounds(300,250,200,30);
        p2pass.setBounds(300,300,200,30);
        p2passtext.setBounds(100,300,200,30);
        cb1.setBounds(500,300,200,30);
        add(p2text);
        add(p2username);
        add(p2passtext);
        add(p2pass);
        add(cb1);
        jcj(false);

        //jcm
        m1text.setBounds(100,250,200,30);
        m1prof.setBounds(300,250,200,30);
        m1alg.setBounds(500,250,200,30);
        add(m1prof);
        add(m1text);
        add(m1alg);
        jcm(false);

        //mcm
        m2text.setBounds(100,300,200,30);
        m2prof.setBounds(300,300,200,30);
        m2alg.setBounds(500,300,200,30);
        add(m2prof);
        add(m2text);
        add(m2alg);
        mcm(false);

        //carregar
        partidestext.setBounds(100,100,250,30);
        deletetext.setBounds(100,100,250,30);
        partides.setBounds(350, 100, 150, 30);
        carregar.setBounds(100,200,200,60);
        delete.setBounds(100,200,200,60);
        add(partides);
        add(partidestext);
        add(carregar);
        add(delete);
        load1(false,false);

        //esborrar tauler
        tdtext.setBounds(100,100,250,30);
        td.setBounds(350, 100, 150, 30);
        tdb.setBounds(100,200,200,60);
        add(tdtext);
        add(td);
        add(tdb);


    }

    public static void main(String[] args) {

        new Vista_menu2();

    }

    private void jugar(boolean actiu){

        colortext.setVisible(actiu);
        rbw.setVisible(actiu);
        rbb.setVisible(actiu);
        taulers.setVisible(actiu);
        taulerstext.setVisible(actiu);


        if(!actiu){
            jcj(false);
        }

        load1(false, false);
        tdvoid(false);
    }

    private void jcj(boolean actiu){
        m1prof.setVisible(false);
        m1text.setVisible(false);
        m2prof.setVisible(false);
        m2text.setVisible(false);
        m2alg.setVisible(false);
        m1alg.setVisible(false);
        start2.setVisible(false);
        start3.setVisible(false);

        start1.setVisible(actiu);
        p2pass.setVisible(actiu);
        p2passtext.setVisible(actiu);
        p2text.setVisible(actiu);
        p2username.setVisible(actiu);
        cb1.setVisible(actiu);
    }
    private void jcm(boolean actiu){
        m1prof.setVisible(actiu);
        m1text.setVisible(actiu);
        m1alg.setVisible(actiu);
        start2.setVisible(actiu);
        start1.setVisible(false);
        start3.setVisible(false);
        m2prof.setVisible(false);
        m2text.setVisible(false);
        m2alg.setVisible(false);
        p2text.setVisible(false);
        p2username.setVisible(false);
        p2pass.setVisible(false);
        p2passtext.setVisible(false);
        cb1.setVisible(false);
    }
    private void mcm(boolean actiu){
        m1prof.setVisible(actiu);
        m1text.setVisible(actiu);
        m1alg.setVisible(actiu);
        m2prof.setVisible(actiu);
        m2text.setVisible(actiu);
        m2alg.setVisible(actiu);
        start3.setVisible(actiu);
        start1.setVisible(false);
        start2.setVisible(false);
        p2text.setVisible(false);
        p2username.setVisible(false);
        p2pass.setVisible(false);
        p2passtext.setVisible(false);
        cb1.setVisible(false);
    }

    private void load1(boolean actiu, boolean esborrar){
        partides.setVisible(actiu);
        partidestext.setVisible(actiu&&!esborrar);
        deletetext.setVisible(actiu&&esborrar);
        carregar.setVisible(actiu&&!esborrar);
        delete.setVisible(actiu&&esborrar);


    }

    private void tdvoid(boolean actiu){
        td.setVisible(actiu);
        tdtext.setVisible(actiu);
        tdb.setVisible(actiu);


    }
    private String[] importar_taulers(){

        String[] nom_taulers = { "Default", "Bakanito", "Fresco", "Si", "Pig" };

        return nom_taulers;
    }

    private String[] importar_partides(){
        String[] nom_taulers = { "Partida 1", "Partida 2", "a", "Si", "Pig" };
        return nom_taulers;
    }

    private String[] importar_algorismes(){
        String[] nom_taulers = { "Minimax", "Minimax amb podes" };
        return nom_taulers;
    }

    public void actionPerformed(ActionEvent e){

        if(e.getSource() == newgame1){
            jugar(true);
            jcj(true);
        }

        else if(e.getSource() == newgame2){
            jugar(true);
            jcm(true);
        }

        else if(e.getSource() == newgame3){
            jugar(true);
            mcm(true);
        }

        else if(e.getSource() == load1){
            jugar(false);
            load1(true, false);
            tdvoid(false);
        }
        else if(e.getSource() == load2){
            jugar(false);
            load1(true, true);
            tdvoid(false);
        }

        else if(e.getSource() == submenu){
            jugar(false);
            load1(false, false);
            tdvoid(true);
        }

        else if(e.getSource() == start1){

            String b = new String();
            String w = new String();
            String p2 = p2username.getText();
            String c2 = p2pass.getPassword().toString();
            boolean correcte = true;

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


            if(correcte) JOptionPane.showMessageDialog(null, "Mode de joc: JcJ, Blanques: " + w + ", Nom J2: " + p2 + ", Contra J2: " + c2 + " Registrar: " + cb1.isSelected());

        }

        else if(e.getSource() == start2){

            String b = new String();
            String w = new String();
            String m1 = m1prof.getText();
            boolean correcte = true;

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
            if(m1.length()==0){
                JOptionPane.showMessageDialog(null, "Introdueix profunditat per la màquina");
                correcte = false;
            }


            if(correcte) JOptionPane.showMessageDialog(null, "Mode de joc: JcM, Blanques: " + w + ", Prof M1: " + m1 + "Alg: " + m1alg.getSelectedItem());

        }

        else if(e.getSource() == start2){

            String b = new String();
            String w = new String();
            String m1 = m1prof.getText();
            String m2 = m2prof.getText();
            boolean correcte = true;

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
            if(m1.length()==0){
                JOptionPane.showMessageDialog(null, "Introdueix profunditat per la màquina 1");
                correcte = false;
            }
            if(m2.length()==0){
                JOptionPane.showMessageDialog(null, "Introdueix profunditat per la màquina 2");
                correcte = false;
            }


            if(correcte) JOptionPane.showMessageDialog(null, "Mode de joc: JcM, Blanques: " + w + ", Prof M1: " + m1 + "Alg m1: " + m1alg.getSelectedItem() + "Alg m2: " + m2alg.getSelectedItem());

        }

        else if(e.getSource() == carregar){
            String partida = partides.getSelectedItem().toString();
            JOptionPane.showMessageDialog(null, "Partida a carregar: " + partida);
        }
        else if(e.getSource() == delete){
            String partida = partides.getSelectedItem().toString();
            JOptionPane.showMessageDialog(null, "Partida a esborrar: " + partida);
        }
        else if(e.getSource() == botoload){
            if(!cbMenuItem1.isSelected() &&!cbMenuItem2.isSelected()&&!cbMenuItem3.isSelected()) JOptionPane.showMessageDialog(null, "Selecciona al menys una direccio de captura");
            else JOptionPane.showMessageDialog(null, "Ranking a carregar: " + cbMenuItem1.isSelected() + cbMenuItem2.isSelected() + cbMenuItem3.isSelected());
        }
        else if(e.getSource() == tdb){
            String partida = td.getSelectedItem().toString();
            JOptionPane.showMessageDialog(null, "Tauler a esborrar: " + partida);
        }
        else if(e.getSource() == creartauler){

            JOptionPane.showMessageDialog(null, "Cridem creacio de tauler");
        }


    }


}