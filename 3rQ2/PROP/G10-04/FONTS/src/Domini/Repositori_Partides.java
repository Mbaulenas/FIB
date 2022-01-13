package Domini;

import java.util.*;

//COMENTARI DE CLASSE:
//Representa el repositori logic que gestiona tot el que va relacionat amb les partides. La classe nomes tindra una
//unica instancia.
//El repositori es representa com un Map de strings i de SortedMaps. La clau del primer map es el nom del usuari i fa
//referencia a un nou mapa que conté per cada instancia la key igual a la partida que te começada el usuari anterior i
//contigut la propia partida en format estandard.
public class Repositori_Partides {
    Map<String, SortedMap<String, String>> map = new HashMap<>();

    //Creadora del repositori de Partides: assigna al parametre implicit  el mapa que es passa per parametre. Aquest es
    //la lectura del gestor de partides que han estat instanciades anteriorment en cas de que hi siguin.
    public Repositori_Partides(Map<String, SortedMap<String, String>> repo) {
        map = new HashMap<>(repo);
    }

    //Retorna la instancia (partida a mitges) del mapa del parametre implicit que ve donada per les keys id i usuari.
    public String carregar_partida(String id, String user) {
        return this.map.get(user).get(id);
    }

    //Elimina la instancia (partida a mitges) del mapa del parametre implicit que ve donada per les keys id i usuari.
    public void eliminar_partida(String id, String user) {
        this.map.get(user).remove(id);
    }

    //Elimina totes les instancies (partides a mitges) del mapa del parametre implicit que ve donada per la key usuari.
    public void eliminar_partides(String user) {
        this.map.get(user).clear();
    }

    //Guarda una instancia (partida a mitges) que la trobem al parametre de la funcio "part" ja existent en el
    //repositori del del parametre implicit que ve donada per les keys "user" i "id_part".
    public void guardar_partida_existent(String user, String id_partida, String part) {
        this.map.get(user).put(id_partida, part);
    }

    //Guarda una instancia (partida a mitges) que la trobem al parametre de la funcio "part" nova en el repositori del
    //parametre implicit que ve donada per les keys "user". Retorna la nova id que si la assignat a aquesta partida.
    public String guardar_partida_nova(String user, String part) {
        int id;
        if (this.map.get(user).isEmpty()) {
            SortedMap<String, String> partides = new TreeMap<>();
            this.map.put(user, partides);
            id = 0;
        }
        else {
            id = Integer.parseInt(this.map.get(user).lastKey()) + 1;
        }
        this.map.get(user).put(Integer.toString(id), part);
        return Integer.toString(id);
    }

    //Retorna un Array de ids de les partides que te la key "usuari"  del mapa del parametre implicit.
    public String[] llistar_partides(String usuari) {
        String[] res;
        if (this.map.get(usuari).isEmpty()) {
            String[] aux = {"no"};
            res = aux;
        }
        else {
            res = (String[])this.map.get(usuari).keySet().toArray(new String[this.map.get(usuari).size()]);
        }
        return res;
    }

    //Afegeix una carpeta logica en el primer mapa del parametre implicit amb la key "user".
    public void usuari_nou(String user) {
        SortedMap<String, String> partides = new TreeMap<>();
        this.map.put(user, partides);
    }
}
