package Domini;

import java.util.*;
import javafx.util.*;

public class Ranking {

    //Map on guardarem el nom d'usuari com a Key i un altre Map com a Value, que contindra la dificultat com a Key i un Pair com a Value,
    //que contindra el nombre de partides acabades com a Key i la mitjana de temps d'aquestes com a Value.
    private Map<String, Map<String, Pair<Double, Double>>> rank;
    //Map auxiliar on guardarem temporalment la dificultat com a Key i un Pair com a Value,
    //que contindra el nombre de partides acabades com a Key i la mitjana de temps d'aquestes com a Value.
    Map<String, Pair<Double, Double>> aux_map = new HashMap<String, Pair<Double, Double>>();

    //Creadora: Carrega al Map rankt i omple els SortedSet's corresponents amb les dades de Ranking.txt ja retornades amb el format corresponent.
    public Ranking(Map<String, Map<String, Pair<Double, Double>>> r) {
        rank = new HashMap<String, Map<String, Pair<Double, Double>>>(r);

        for (Map.Entry<String, Map<String, Pair<Double, Double>>> entry : rank.entrySet()) {
            String user = entry.getKey();
            for(Map.Entry<String, Pair<Double, Double>> entry2 : rank.get(entry.getKey()).entrySet()) {
                String d = entry2.getKey();
                Double nombre = entry2.getValue().getKey();
                Double t = entry2.getValue().getValue();
                Pair<String, Double> pn = new Pair<> (user, nombre);
                Pair<String, Double> pt = new Pair<> (user, t);
                if (d.equals("Difícil")) {
                    Iterator<Pair<String, Double>> it = nombre_dificil.iterator();
                    while(it.hasNext()){
                        Pair<String, Double> wtv = it.next();
                        if(wtv.getKey().equals(user)) {
                            nombre_dificil.remove(wtv);
                        }
                    }
                    Iterator<Pair<String, Double>> it2 = temps_dificil.iterator();
                    while(it2.hasNext()){
                        Pair<String, Double> wtv = it2.next();
                        if(wtv.getKey().equals(user)) {
                            temps_dificil.remove(wtv);
                        }
                    }
                    nombre_dificil.add(pn);
                    temps_dificil.add(pt);
                    while (nombre_dificil.size() > 10) {
                        nombre_dificil.remove(nombre_dificil.last());
                    }
                    while (temps_dificil.size() > 10) {
                        temps_dificil.remove(temps_dificil.last());
                    }
                }
                else if (d.equals("Mitjana")) {
                    Iterator<Pair<String, Double>> it = nombre_mitja.iterator();
                    while(it.hasNext()){
                        Pair<String, Double> wtv = it.next();
                        if(wtv.getKey().equals(user)) {
                            nombre_mitja.remove(wtv);
                        }
                    }
                    Iterator<Pair<String, Double>> it2 = temps_mitja.iterator();
                    while(it2.hasNext()){
                        Pair<String, Double> wtv = it2.next();
                        if(wtv.getKey().equals(user)) {
                            temps_mitja.remove(wtv);
                        }
                    }
                    nombre_mitja.add(pn);
                    temps_mitja.add(pt);
                    while (nombre_mitja.size() > 10) {
                        nombre_mitja.remove(nombre_mitja.last());
                    }
                    while (temps_mitja.size() > 10) {
                        temps_mitja.remove(temps_mitja.last());
                    }
                }
                else if (d.equals("Fàcil")) {
                    Iterator<Pair<String, Double>> it = nombre_facil.iterator();
                    while(it.hasNext()){
                        Pair<String, Double> wtv = it.next();
                        if(wtv.getKey().equals(user)) {
                            nombre_facil.remove(wtv);
                        }
                    }
                    Iterator<Pair<String, Double>> it2 = temps_facil.iterator();
                    while(it2.hasNext()){
                        Pair<String, Double> wtv = it2.next();
                        if(wtv.getKey().equals(user)) {
                            temps_facil.remove(wtv);
                        }
                    }
                    nombre_facil.add(pn);
                    temps_facil.add(pt);
                    while (nombre_facil.size() > 10) {
                        nombre_facil.remove(nombre_facil.last());
                    }
                    while (temps_facil.size() > 10) {
                        temps_facil.remove(temps_facil.last());
                    }
                }

            }

        }

    };

    //Mantrindrem els 6 tipus de ranking actualitzats.

    //SortedSet amb un Pair que te com a Key el nom d'usuari i com a Value el nombre de partides dificils acabades.
    private SortedSet<Pair<String, Double>> nombre_dificil = new TreeSet<>(new Comparator<Pair<String, Double>>() {
        @Override
        public int compare(Pair<String, Double> o1, Pair<String, Double> o2) {
            if (o2.getValue() < o1.getValue()) return -1;
            else if (o2.getValue() == o1.getValue()) {
                if (o2.getKey() != o1.getKey()) return 1;
                else return 0;
            }
            else return 1;
        }
    });
    //SortedSet amb un Pair que te com a Key el nom d'usuari i com a Value el nombre de partides mitjanes acabades.
    private SortedSet<Pair<String, Double>> nombre_mitja = new TreeSet<>(new Comparator<Pair<String, Double>>() {
        @Override
        public int compare(Pair<String, Double> o1, Pair<String, Double> o2) {
            if (o2.getValue() < o1.getValue()) return -1;
            else if (o2.getValue() == o1.getValue()) {
                if (o2.getKey() != o1.getKey()) return 1;
                else return 0;
            }
            else return 1;
        }
    });
    //SortedSet amb un Pair que te com a Key el nom d'usuari i com a Value el nombre de partides facils acabades.
    private SortedSet<Pair<String, Double>> nombre_facil = new TreeSet<>(new Comparator<Pair<String, Double>>() {
        @Override
        public int compare(Pair<String, Double> o1, Pair<String, Double> o2) {
            if (o2.getValue() < o1.getValue()) return -1;
            else if (o2.getValue() == o1.getValue()) {
                if (o2.getKey() != o1.getKey()) return 1;
                else return 0;
            }
            else return 1;
        }
    });
    //SortedSet amb un Pair que te com a Key el nom d'usuari i com a Value la mitja del temps de resolucio de les partides dificils acabades.
    private SortedSet<Pair<String, Double>> temps_dificil = new TreeSet<>(new Comparator<Pair<String, Double>>() {
        @Override
        public int compare(Pair<String, Double> o1, Pair<String, Double> o2) {
            if (o1.getValue() < o2.getValue()) return -1;
            else if (o1.getValue() == o2.getValue()) {
                if (o2.getKey() != o1.getKey()) return 1;
                else return 0;
            }
            else return 1;
        }
    });
    //SortedSet amb un Pair que te com a Key el nom d'usuari i com a Value la mitja del temps de resolucio de les partides mitjanes acabades.
    private SortedSet<Pair<String, Double>> temps_mitja = new TreeSet<>(new Comparator<Pair<String, Double>>() {
        @Override
        public int compare(Pair<String, Double> o1, Pair<String, Double> o2) {
            if (o1.getValue() < o2.getValue()) return -1;
            else if (o1.getValue() == o2.getValue()) {
                if (o2.getKey() != o1.getKey()) return 1;
                else return 0;
            }
            else return 1;
        }
    });
    //SortedSet amb un Pair que te com a Key el nom d'usuari i com a Value la mitja del temps de resolucio de les partides facils acabades.
    private SortedSet<Pair<String, Double>> temps_facil = new TreeSet<>(new Comparator<Pair<String, Double>>() {
        @Override
        public int compare(Pair<String, Double> o1, Pair<String, Double> o2) {
            if (o1.getValue() < o2.getValue()) return -1;
            else if (o1.getValue() == o2.getValue()) {
                if (o2.getKey() != o1.getKey()) return 1;
                else return 0;
            }
            else return 1;
        }
    });

    //Actualitza el rankt aixi com els SortedSet's corresponents.
    public void actualitzar_ranking (String user, String d, Double t) {
        Map<String, Pair<Double, Double>> aux_map = new HashMap<String, Pair<Double, Double>>();
        Double nombre = 1.0;

        if (rank.containsKey(user) && rank.get(user).containsKey(d)) {
            nombre = rank.get(user).get(d).getKey();
            ++nombre;

            double aux = (1/nombre) * (nombre-1) * rank.get(user).get(d).getValue();
            aux += (1/nombre) * t;
            t = aux;

            Pair<Double, Double> aux_pair = new Pair<> (nombre, t);
            rank.get(user).put(d, aux_pair);
        }

        else if(rank.containsKey(user) && !rank.get(user).containsKey(d)) {
            Pair<Double, Double> aux_pair = new Pair<> (nombre, t);
            rank.get(user).put(d, aux_pair);
        }

        else {
            Pair<Double, Double> aux_pair = new Pair<> (nombre, t);
            aux_map.put(d, aux_pair);
            rank.put(user, aux_map);
        }

        Pair<String, Double> pn = new Pair<> (user, nombre);
        Pair<String, Double> pt = new Pair<> (user, t);
        if (d.equals("Difícil")) {
            Iterator<Pair<String, Double>> it = nombre_dificil.iterator();
            while(it.hasNext()){
                Pair<String, Double> wtv = it.next();
                if(wtv.getKey().equals(user)) {
                    nombre_dificil.remove(wtv);
                }
            }
            Iterator<Pair<String, Double>> it2 = temps_dificil.iterator();
            while(it2.hasNext()){
                Pair<String, Double> wtv = it2.next();
                if(wtv.getKey().equals(user)) {
                    temps_dificil.remove(wtv);
                }
            }
            nombre_dificil.add(pn);
            temps_dificil.add(pt);
            while (nombre_dificil.size() > 10) {
                nombre_dificil.remove(nombre_dificil.last());
            }
            while (temps_dificil.size() > 10) {
                temps_dificil.remove(temps_dificil.last());
            }
        }
        else if (d.equals("Mitjana")) {
            Iterator<Pair<String, Double>> it = nombre_mitja.iterator();
            while(it.hasNext()){
                Pair<String, Double> wtv = it.next();
                if(wtv.getKey().equals(user)) {
                    nombre_mitja.remove(wtv);
                }
            }
            Iterator<Pair<String, Double>> it2 = temps_mitja.iterator();
            while(it2.hasNext()){
                Pair<String, Double> wtv = it2.next();
                if(wtv.getKey().equals(user)) {
                    temps_mitja.remove(wtv);
                }
            }
            nombre_mitja.add(pn);
            temps_mitja.add(pt);
            while (nombre_mitja.size() > 10) {
                nombre_mitja.remove(nombre_mitja.last());
            }
            while (temps_mitja.size() > 10) {
                temps_mitja.remove(temps_mitja.last());
            }
        }
        else if (d.equals("Fàcil")) {
            Iterator<Pair<String, Double>> it = nombre_facil.iterator();
            while(it.hasNext()){
                Pair<String, Double> wtv = it.next();
                if(wtv.getKey().equals(user)) {
                    nombre_facil.remove(wtv);
                }
            }
            Iterator<Pair<String, Double>> it2 = temps_facil.iterator();
            while(it2.hasNext()){
                Pair<String, Double> wtv = it2.next();
                if(wtv.getKey().equals(user)) {
                    temps_facil.remove(wtv);
                }
            }
            nombre_facil.add(pn);
            temps_facil.add(pt);
            while (nombre_facil.size() > 10) {
                nombre_facil.remove(nombre_facil.last());
            }
            while (temps_facil.size() > 10) {
                temps_facil.remove(temps_facil.last());
            }
        }
    }

    //Retorna el SortedSet de nombre corresponent segons la dificultat "d" demanada.
    public SortedSet<Pair<String, Double>> mostra_ranking_nombre (String d) {
        if (d.equals("Difícil")) {
            return nombre_dificil;
        }
        else if (d.equals("Mitjana")) {
            return nombre_mitja;
        }
        else {
            return nombre_facil;
        }
    }

    //Retorna el SortedSet de temps corresponent segons la dificultat "d" demanada.
    public SortedSet<Pair<String, Double>> mostra_ranking_temps (String d) {
        if (d == "Difícil") {
            return temps_dificil;
        }
        else if (d == "Mitjana") {
            return temps_mitja;
        }
        else {
            return temps_facil;
        }
    }

    //Retorna un vector amb les dades de rankt ja ordenades per a escriure a Ranking.txt
    public ArrayList<String> guardar() {
        ArrayList<String> g = new ArrayList<String>();
        Double pi;
        Double td;
        Map<String, Pair<Double, Double>> aux_m_aux = new HashMap<String, Pair<Double, Double>>();
        Iterator<Map.Entry<String, Map<String, Pair<Double, Double>>>> it = rank.entrySet().iterator();

        while(it.hasNext()) {
            Map.Entry<String, Map<String, Pair<Double, Double>>> aux_r = it.next();
            aux_m_aux = aux_r.getValue();

            Iterator<Map.Entry<String, Pair<Double, Double>>> it2 = aux_m_aux.entrySet().iterator();
            while(it2.hasNext()) {
                Map.Entry<String, Pair<Double, Double>> m_aux = it2.next();
                g.add(aux_r.getKey());
                g.add(m_aux.getKey());
                pi = m_aux.getValue().getKey();
                g.add(pi.toString());
                td = m_aux.getValue().getValue();
                g.add(td.toString());
            }
        }
        return g;
    }

}
