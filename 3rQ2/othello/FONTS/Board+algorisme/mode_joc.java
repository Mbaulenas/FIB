/**Classe per contenir el mode de joc
 * @author Genis Nin
 * @version 1.0
 */
public class mode_joc {
    private boolean vertical;
    private boolean horitzontal;
    private boolean diagonal;


    /**
     * Modifica l'atribut horitzontal. En cas que s'intenti posar en false si els altres dos atributs ja estan
     * en false no ens ho permetrà i enviarà un missatge per a avisar.
     * @param horitzontal boolea que indica si es pot capturar en horitzontal (true) o no (false)
     */
    public void setHoritzontal(boolean horitzontal) {
        if (horitzontal == false){
            if (this.vertical == false && this.diagonal == false){
                System.out.println("No podem tenir tots els modes de captura en false!!");
            }
            else {
                this.horitzontal = horitzontal;
            }
        }
        else{
            this.horitzontal = horitzontal;
        }

    }

    /**
     * Modifica l'atribut vertical. En cas que s'intenti posar en false si els altres dos atributs ja estan
     * en false no ens ho permetrà i enviarà un missatge per a avisar.
     * @param vertical boolea que indica si es pot capturar en vertical (true) o no (false)
     */
    public void setVertical(boolean vertical) {
        if (vertical == false){
            if (this.horitzontal == false && this.diagonal == false){
                System.out.println("No podem tenir tots els modes de captura en false!!");
            }
            else {
                this.vertical = vertical;
            }
        }
        else{
            this.vertical = vertical;
        }
    }

    /**
     * Modifica l'atribut diagonal. En cas que s'intenti posar en false si els altres dos atributs ja estan
     * en false no ens ho permetrà i enviarà un missatge per a avisar.
     * @param diagonal boolea que indica si es pot capturar en diagonal (true) o no (false)
     */
    public void setDiagonal(boolean diagonal) {
        if (diagonal == false){
            if (this.horitzontal == false && this.vertical == false){
                System.out.println("No podem tenir tots els modes de captura en false!!");
            }
            else {
                this.diagonal = diagonal;
            }
        }
        else{
            this.diagonal = diagonal;
        }
    }

    /**
     *
     * @return Boleà que indica si es pot capturar en horitzontal (true) o no (false)
     */
    public boolean isHoritzontal() {
        return horitzontal;
    }

    /**
     * @return Boleà que indica si es pot capturar en diagonal (true) o no (false)
     */
    public boolean isDiagonal() {
        return diagonal;
    }

    /**
     * @return Boleà que indica si es pot capturar en vertical (true) o no (false)
     */
    public boolean isVertical() {
        return vertical;
    }

    /**
     *Constructora de la classe sense paràmetres. Posa tots els modes de captura com a vàlids
     */
    public mode_joc(){
        this.diagonal = true;
        this.horitzontal = true;
        this.vertical = true;
    }

    /**
     * Constructora de la classe amb paràmetres. Posa els modes de captura segons els parametres enviats.
     * En cas de que tots els paràmetres estiguin en false, es posen tots els modes de captura com a true ja que
     * no podem tenir tots els modes de captura invalidats
     * @param diagonal indica si es pot capturar en diagonal o no
     * @param horitzontal indica si es pot capturar en horitzontal o no
     * @param vertical idica si es pot capturar en vertical o no
     */
    public mode_joc(boolean diagonal, boolean horitzontal, boolean vertical){
        if (!diagonal && !horitzontal && !vertical){
            System.out.println("No podem tenir tots els modes de captura en false!!");
            System.out.println("Farem servir la creadora per defecte...");
            this.diagonal = true;
            this.vertical = true;
            this.horitzontal = true;
        }
        else{
            this.diagonal = diagonal;
            this.horitzontal = horitzontal;
            this.vertical = vertical;
        }
    }


}