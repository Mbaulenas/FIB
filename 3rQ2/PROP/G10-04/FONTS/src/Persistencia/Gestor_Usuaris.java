package Persistencia;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;

//COMENTARI DE CLASSE:
//Representa al gestor que sera el encarregat de organitzar qualsevol operacio relacionada amb Usuaris. Creara, llegira
// o eliminara els fitxers corresponents amb els metodes de la classe.

public class Gestor_Usuaris {

    //Creadora de Gestor_Usuaris: Crea la carpeta real "Repositori" en cas que no estigui creada i crea el fitxer
    //Usuaris.txt en cas que no hi sigui.
    public Gestor_Usuaris() {
        File dire = new File("Repositori");
        if (!dire.exists())
            dire.mkdir();
        File x = new File("Repositori/Usuaris.txt");
        try {
            x.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    };

    //Llegeix els usuaris actuals que conte el fitxer real i els converteix en un Array de Usuari - Contrassenya de tots
    //els registrats.
    public ArrayList<String> llegir_usuaris() {
        ArrayList<String> aux = new ArrayList<String>();
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader("Repositori/Usuaris.txt"));
            String line;
            while ((line = br.readLine()) != null) {
                aux.add(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return aux;
    }

    //Escriu el usuari i la contrassenya del nou usuari que es vol registrar en el projecte al final del fitxer real.
    //Usuari i Contrassenya valids.
    public void register(String usuari, String pass) {
        try {
            File x = new File("Repositori/Usuaris.txt");
            FileWriter fileWriter = new FileWriter(x.getPath(), true);
            if (x.length() != 0)fileWriter.append("\n");
            fileWriter.append(usuari);
            fileWriter.append("\n");
            fileWriter.append(pass);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
