package Persistencia;

import java.io.*;
import java.util.*;

//COMENTARI DE CLASSE:
//Representa al gestor que sera el encarregat de organitzar qualsevol operacio relacionada amb kakuros. Creara, llegira
//o eliminara els fitxers corresponents amb els metodes de la classe.

public class Gestor_Kakuro {

    //Creadora de Gestor_Kakuro: Crea la carpeta real "Kakuro" dins de repositori en cas que no hi sigui ja.
    public Gestor_Kakuro() {
        File dire = new File("Repositori/Kakuro");
        if (!dire.exists()) dire.mkdir();
    };

    //Guarda en un nou fitxer el contingut "kakuro" del parametre amb el nom de fitxer "id" del parametre.
    public void guardar_kakuro(String id, String kakuro) {
        File x = new File("Repositori/Kakuro/" + id + ".txt");
        try {
            x.createNewFile();
            x.setWritable(true);
            FileWriter f = new FileWriter(x);
            PrintWriter p = new PrintWriter(f);
            p.printf(kakuro);
            p.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Llegeix tots els kakuros que te actualment el repositori fisic de kakuros i els posa en un SortedMap amb clau = id
    //del kakuro i contingut el kakuro en format estandard. Aquest metode crida a llegir_kakuro per fer una lectura de
    //un fitxer de kakuro en concret.
    public SortedMap<String, String> llegir_kakuros() {
        File[] x = new File("Repositori/Kakuro").listFiles();
        SortedMap<String, String> m = new TreeMap<String, String>();
        for (int i = 0; i < x.length; ++i) {
            String kakuro = llegir_kakuro(x[i].getPath());
            String s = x[i].getName();
            s = s.substring(0, x[i].getName().lastIndexOf("."));
            m.put(s, kakuro);
        }
        return m;
    }

    //Llegeix el fitxer (kakuro) del path s del parametre. En cas de qualsevol problema retorna "nokakuro".
    private String llegir_kakuro(String s) {
        File x = new File(s);
        String contingut = "";
        Scanner scan;
        try {
            scan = new Scanner(x);
            if (!x.exists()) {
                scan.close();
                return contingut; 
            }
            else {
                while (scan.hasNextLine()) contingut = contingut.concat(scan.nextLine() + "\n");
                scan.close();
                return contingut;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return "nokakuro";
    }

    // Elimina tots els fitxes (kakuros) del repositori físic.
    public void instanciar() {
        File[] x = new File("Repositori/Kakuro").listFiles();
        for (int i = 0; i < x.length; ++i) x[i].delete();
    }

    //Elimina el fitxer (kakuro) identificat amb el "id" del parametre en cas que existeixi.
    public void eliminar_kakuro(String id) {
        File x = new File("Repositori/Kakuro/" + id + ".txt");
        x.delete();
    }
}
