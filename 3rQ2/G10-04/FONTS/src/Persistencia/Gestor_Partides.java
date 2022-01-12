package Persistencia;

import java.io.*;
import java.util.*;

//COMENTARI DE CLASSE:
//Representa al gestor que sera el encarregat de organitzar qualsevol operacio relacionada amb Partides. Creara, llegira
//o eliminara els fitxers corresponents amb els metodes de la classe.

public class Gestor_Partides {

    //Creadora de Gestor_Usuaris: Crea la carpeta real "Partides" dins de repositori en cas que no hi sigui ja.
    public Gestor_Partides() {
        File dire = new File("Repositori/Partides/");
        if (!dire.exists())
            dire.mkdir();
    };


    //Guarda la "partida" en format String en la carpeta "usuari" amb el fitxer que tindrà com a "id_partida". En cas
    //que sigui la primera partida existent de l'usuari creara la seva carpeta dins del repositori. En cas que la
    //partida ja existeixi es reescriura el document.txt d'aquesta.
    public void guardar_partida(String usuari, String id_partida, String partida) {
        File x = new File("Repositori/Partides/" + usuari);
        if (!x.exists()) x.mkdir();
        File x2 = new File("Repositori/Partides/" + usuari + "/" + id_partida + ".txt");
        try {
            x2.createNewFile();
            FileWriter f = new FileWriter(x2);
            PrintWriter p = new PrintWriter(f);
            p.printf(partida);
            p.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Llegeix les tots els documents de totes les carpetes del repositori físic i guarda el seu contingut en un Map.
    // Aquest mapa representa la primera clau l'usuari i el contingut és un Sorted Map de contingut id_partida i el
    // contingut de la partida. Aquesta metode crida a llegir partida per llegir cada fitxer per separat.
    public Map<String, SortedMap<String, String>> partides_disponibles() {
        Map<String, SortedMap<String, String>> s = new HashMap<>();
        File[] arxius = new File("Repositori/Partides/").listFiles();
        for (int i = 0; i < arxius.length; ++i) {
            File[] arxius_user = new File("Repositori/Partides/"+ arxius[i].getName()+"/").listFiles();
            SortedMap<String, String> s2 = new TreeMap<String, String>();
            for (int j = 0; j < arxius_user.length; ++j) {
                String partida = llegir_partida(arxius_user[j].getPath());
                String se = arxius_user[j].getName();
                se = se.substring(0, arxius_user[j].getName().lastIndexOf("."));
                s2.put(se, partida);
            }
            s.put(arxius[i].getName(), (SortedMap<String, String>) s2);
        }
        return s;
    }

    //Llegeix la partida que hi ha en el path s i la retorna en format String. En cas de qualsevol problema retorna
    //"nopartida".
    private String llegir_partida(String s) {
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
        return "nopartida";
    }

    //Elimina el arxiu (partida) "id" de l'usuari "usuari" en cas que existeixi
    public void eliminar_partida(String id, String usuari) {
        File x = new File("Repositori/Partides/" + usuari + "/" + id + ".txt");
        x.delete();
    }

    //Elimina tots els arxius (partida) que té tot el usuari "usuari".
    public void eliminar_partides(String usuari) {
        File x[] = new File("Repositori/Partides/" + usuari).listFiles();
        for (int i = 0; i < x.length; ++i)
            x[i].delete();
    }

    //Crea la carpeta física de l'usuari que hi ha com a parametre.
    public void crear_carpeta_partides (String usuari) {
        File x = new File("Repositori/Partides/" + usuari);
        if (!x.exists()) x.mkdir();
    }
}


