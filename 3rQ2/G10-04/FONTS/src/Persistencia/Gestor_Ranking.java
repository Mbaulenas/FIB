package Persistencia;

import java.io.*;
import java.util.*;
import javafx.util.*;

public class Gestor_Ranking {

    //Creadora: Crea la carpeta "Repositori" si aquesta no existeix ja i dins d'ella crea el .txt "Ranking" si aquest no existeix ja.
    public Gestor_Ranking() {
        File dire = new File("Repositori");
        if (!dire.exists()) dire.mkdir();

        File x = new File("Repositori/Ranking.txt");
        try {
            x.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        x.setWritable(true);
    }

    //Retorna les dades de Ranking.txt amb el format corresponent per a poder carregar-les al Map de la classe "Ranking".
    public Map<String, Map<String, Pair<Double, Double>>> load() {
        File x = new File("Repositori/Ranking.txt");
        Map<String, Map<String, Pair<Double, Double>>> r = new HashMap<String, Map<String, Pair<Double, Double>>>();
        try {
            Scanner scan = new Scanner(x);
            String us = "", ds = "", ps = "", ts = "", aux = "";
            Boolean ae = true;
            Boolean empty = false;
            if(scan.hasNextLine()) aux = scan.nextLine();
            //System.out.println(aux);
            if(scan.hasNextLine()) aux = scan.nextLine();
            while (scan.hasNextLine()) {
                Map<String, Pair<Double, Double>> rx = new HashMap<String, Pair<Double, Double>>();
                ae = true;
                //System.out.println(aux);
                us = aux;
                for(Integer i=0; i<3 && ae && scan.hasNextLine(); ++i) {
                    ds = scan.nextLine();
                    if(scan.hasNextLine()) ps = scan.nextLine();
                    //System.out.println(ds);
                    //System.out.println(ps);
                    Double p = Double.valueOf(ps);
                    if(scan.hasNextLine()) ts = scan.nextLine();
                    //System.out.println(ts);
                    double t = Double.valueOf(ts);
                    Pair<Double, Double> par = new Pair<Double, Double>(p, t);
                    rx.put(ds, par);
                    if(scan.hasNextLine()) aux = scan.nextLine();
                    if(!us.equals(aux)) ae = false;
                }
                r.put(us, rx);
            }
            scan.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //System.out.println("After");
        return r;
    }

    //Guarda les dades rebudes a Ranking.txt
    public void save(String s) {
        File x = new File("Repositori/Ranking.txt");
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(x, true));
            writer.newLine();
            writer.append(s);
            writer.close();
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Elimina Ranking.txt
    public void restart_rank() {
        //System.out.println("GR");
        File x = new File("Repositori/Ranking.txt");
        x.delete();
    }
}