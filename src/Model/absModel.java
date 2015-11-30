package Model;

import java.util.Observable;
import java.util.Observer;

/**
 * FR: classe abstraite du modèle
 * EN: model's abstract class
 */
public abstract class absModel extends Observable {
    /**
     * FR:
     * Variables de la classe abstraite "Model"
     * operator : stocke l'opérateur du calcul
     * operand : stocke le chiffre du calcul en string
     * memory : mémoire de la calculatrice pour les fonctions M*
     * result : résultat du calcul
     * _modelObserver : l'oberser lié à notre modèle
     *
     * EN:
     * Variables from the abstract class "Model"
     * operator: stores the calculation operator
     * operand : stores the calculation's number in a string
     * memory: memory of the calculator for the functions M*
     * result : calculation's result
     * _modelObserver : the observer related to our model
     */
    protected String operator = "";
    protected String operand = "";
    protected double memory = 0;
    protected double result = 0;
    private Observer _modelObserver = null;

    /**
     * FR: rajoute un observer au modèle
     * EN: set our observer
     * @param observer FR: notre observer / EN: an observer
     */
    public void addObserver(Observer observer) {
        _modelObserver = observer;
    }

    /**
     * FR: supprime l'observeur du modèle
     * EN: delete our model's observer
     */
    public void deleteObserver() {
        _modelObserver = null;
    }

    /**
     * FR: notifie l'observeur et lance l'update
     * EN: notify observer and update with the string
     * @param str FR: la chaine de charactere a notifier / EN: the string that will be update to observer
     */
    public void notifyObserver(String str) {
        if (str.matches("^0[0-9]+"))
            str = str.substring(1, str.length());
        _modelObserver.update(this, str);
    }

    /**
     * FR: Efface les donnees de la calculatrice (operator, operand, result)
     * EN: Reset the calculator's data (operator, operand, result)
     */
    public abstract void clear();

    /**
     * FR: calcule & affiche le résultat
     * EN : calculate & display result
     */
    public abstract void getResult();

    /**
     * FR: on stocke l'operateur
     * EN: set operator
     * @param operator FR: l'operateur du calcul / EN: calculation's operator
     */
    public abstract void setOperator(String operator);

    /**
     * FR: on stocke le nombre
     * EN: set our number
     * @param number FR: le nombre utilise pour le calcul / EN: calculation's number
     */
    public abstract void setNumber(String number);

    /**
     * FR: lance le calcul
     * EN: do the calculation
     */
    public abstract void doCalculation();
}
