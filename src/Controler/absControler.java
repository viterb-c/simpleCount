package Controler;
import Model.absModel;

public abstract class absControler {
    protected absModel calcModel;
    protected String operator = "";
    protected String number = "";

    /**
     * FR: Constructeur de la classe
     * EN: Constructor
     * @param calcModel FR: notre modele / EN: our model
     */
    public absControler(absModel calcModel) {
        this.calcModel = calcModel;
    }

    /**
     * FR: on set l'operateur et on appelle le controleur
     * EN: set operator and call controler
     * @param operator FR: l'operateur du calcul / EN: calculation operator
     */
    public void setOperator(String operator) {
        this.operator = operator;
        controler();
    }

    /**
     * FR: on set le nombre et on appelle le controleur
     * EN: set number and call controler
     * @param number
     */
    public void setNumber(String number) {
        this.number = number;
        controler();
    }

    /**
     * FR: retourne le modele
     * EN: return model
     * @return FR: le modele / EN: our model
     */
    public absModel getModel() {
        return this.calcModel;
    }

    /**
     * FR: appelle la function clear du modele
     * EN: call model's clear function
     */
    public void clear() {
        this.calcModel.clear();
    }

    /**
     * FR: vérifie l'operateur et le nombre
     * EN: check operator and number
     */
    abstract void controler();
}
