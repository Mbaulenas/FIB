package Domini;

//COMENTARI DE LA CLASSE
//Representa la classe que fa referencia a les partides a mitges dels usuaris de la aplicacio. Cada partida conte el seu
//id, el kakuro en format Matriu de Strings actual de la partida, el kakuro resolt en format matriu de Stirngs, el temps
//que porta la partida en execució.
public class Partida {
    
    String id;
    String[][] kakuro;
    String[][] kakuro_resolt;
    int Time;
    String dificultat;

    /* FORMAT ESTANDARD PARTIDA*/
    // n*n
    // kakuro actual
    // dificultat
    // temps que porta
    // kakuro resolt

    //Creadora de Partida: Donada una partida en format estandard (String) interpreta aquesta i omple els diferents
    //valors del parametre implicit.
    public Partida(String part) {
        int aux = Character.getNumericValue(part.charAt(0));
        this.kakuro = new String[aux][aux];
        this.kakuro_resolt = new String[aux][aux];
        int k = 4;
        for (int i = 0; i < aux && k < part.length(); ++i) {
            for (int j = 0; j < aux && k < part.length(); ++j) {
                if (part.charAt(k) == ',' || part.charAt(k) == '\n') ++k;
                if (part.charAt(k) == '*') {
                    this.kakuro[i][j] = "*";
                    ++k;
                }
                else if (part.charAt(k) == '?') {
                    this.kakuro[i][j] = "?";
                    ++k;
                }
                else if (part.charAt(k) == 'C') {
                    this.kakuro[i][j] = "C";
                    ++k;
                    this.kakuro[i][j] += Character.getNumericValue(part.charAt(k));
                    ++k;
                    if (part.charAt(k) != 'F' && part.charAt(k) != ',' && part.charAt(k) != '\n') {
                        this.kakuro[i][j] += Character.getNumericValue(part.charAt(k));
                        ++k;
                        if (part.charAt(k) == 'F') {
                            this.kakuro[i][j] += "F";
                            ++k;
                            this.kakuro[i][j] += Character.getNumericValue(part.charAt(k));
                            ++k;
                            if (part.charAt(k) != ',') {
                                this.kakuro[i][j] += Character.getNumericValue(part.charAt(k));
                                ++k;
                            }
                        }
                    } else if (part.charAt(k) == 'F') {
                        this.kakuro[i][j] += "F";
                        ++k;
                        this.kakuro[i][j] += Character.getNumericValue(part.charAt(k));
                        ++k;
                        if (part.charAt(k) != ',') {
                            this.kakuro[i][j] += Character.getNumericValue(part.charAt(k));
                            ++k;
                        }
                    }
                }
                else if (part.charAt(k) == 'F') {
                    this.kakuro[i][j] = "F";
                    ++k;
                    this.kakuro[i][j] += Character.getNumericValue(part.charAt(k));
                    ++k;
                    if (part.charAt(k) != ',') {
                        this.kakuro[i][j] += Character.getNumericValue(part.charAt(k));
                        ++k;
                    }
                }
                else {
                    this.kakuro[i][j] = Character.toString(part.charAt(k));
                    ++k;
                }
            }
        }
        ++k;
        String s = "";
        while (k < part.length() && part.charAt(k) != '\n') {
            s += part.charAt(k);
            ++k;
        }
        this.dificultat = s;
        ++k;
        s = "";
        while (k < part.length() && part.charAt(k) != '\n') {
            s += part.charAt(k);
            ++k;
        }
        this.Time = Integer.parseInt(s);
        ++k;

        for (int i = 0; i < aux && k < part.length(); ++i) {
            for (int j = 0; j < aux && k < part.length(); ++j) {
                if (part.charAt(k) == ',' || part.charAt(k) == '\n') ++k;
                if (part.charAt(k) == '*') {
                    this.kakuro_resolt[i][j] = "*";
                    ++k;
                }
                else if (part.charAt(k) == 'C') {
                    this.kakuro_resolt[i][j] = "C";
                    ++k;
                    this.kakuro_resolt[i][j] += Character.getNumericValue(part.charAt(k));
                    ++k;
                    if (part.charAt(k) != 'F' && part.charAt(k) != ',' && part.charAt(k) != '\n') {
                        this.kakuro_resolt[i][j] += Character.getNumericValue(part.charAt(k));
                        ++k;
                        if (part.charAt(k) == 'F') {
                            this.kakuro_resolt[i][j] += "F";
                            ++k;
                            this.kakuro_resolt[i][j] += Character.getNumericValue(part.charAt(k));
                            ++k;
                            if (part.charAt(k) != ',') {
                                this.kakuro_resolt[i][j] += Character.getNumericValue(part.charAt(k));
                                ++k;
                            }
                        }
                    } else if (part.charAt(k) == 'F') {
                        this.kakuro_resolt[i][j] += "F";
                        ++k;
                        this.kakuro_resolt[i][j] += Character.getNumericValue(part.charAt(k));
                        ++k;
                        if (part.charAt(k) != ',') {
                            this.kakuro_resolt[i][j] += Character.getNumericValue(part.charAt(k));
                            ++k;
                        }
                    }
                }
                else if (part.charAt(k) == 'F') {
                    this.kakuro_resolt[i][j] = "F";
                    ++k;
                    this.kakuro_resolt[i][j] += Character.getNumericValue(part.charAt(k));
                    ++k;
                    if (part.charAt(k) != ',') {
                        this.kakuro_resolt[i][j] += Character.getNumericValue(part.charAt(k));
                        ++k;
                    }
                }
                else {
                    this.kakuro_resolt[i][j] = Character.toString(part.charAt(k));
                    ++k;
                }
            }
        }
    }

    //Creadora de Partida: Donada una partida en format estandard (String) interpreta aquesta i omple els diferents
    //valors del parametre implicit.
    public Partida(Kakuro k) {
        this.kakuro = k.getKakuro_sincomas();  
        Kakuro kak = new Kakuro(k.res_kakuro().getKakuro());                           
        this.kakuro_resolt = kak.getKakuro_sincomas();                
        this.dificultat = k.get_dificultat();
        //this.pistas = 0;
        this.Time = 0;
    }

    //Retorna la dificultat de la partida del parametre implicit.
    public String get_dificultat() {
        return this.dificultat;
    }

    //Retorna el temps que porta la partida en funcionament del parametre implicit.
    public int get_time() {
        return this.Time;
    }

    //Retorna el kakuro actual (a mitges) que porta la partida del parametre implicit.
    public String[][] get_actual() {
        return this.kakuro;
    }

    //Retorna el kakuro resolt de la partida del parametre implicit.
    public String[][] get_resolt() {
        return this.kakuro_resolt;
    }

    //Retorna la partida del parametre implicit en format estandard.
    public String get_text() {
        String resultat = this.kakuro.length + "," + this.kakuro[0].length + "\n";
        for (int i = 0; i < this.kakuro.length; ++i) {
            for (int j = 0; j < this.kakuro[i].length; ++j) {
                if (j != 0) resultat += ",";
                resultat += this.kakuro[i][j];
            }
            resultat += "\n";
        }
        resultat += dificultat + "\n";
        resultat += Time + "\n";
        for (int i = 0; i < this.kakuro.length; ++i) {
            for (int j = 0; j < this.kakuro[i].length; ++j) {
                if (j != 0) resultat += ",";
                resultat += this.kakuro_resolt[i][j];
            }
            if (i != this.kakuro.length - 1) resultat += "\n";
        }
        return resultat;
    }
} 