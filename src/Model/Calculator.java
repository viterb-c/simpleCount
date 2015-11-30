package Model;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;

/**
 * FR: Notre modele -> une calculatrice scientifique simple (un calcul à la fois)
 * EN: Our model -> a scientific calculator (one calculation at once)
 */
public class Calculator extends absModel {
    /**
     * FR: HashMap contenant les fonctions de calcul et leurs clefs
     * EN: HashMap that contains calculation's functions and their keys
     */
    private HashMap<String, FunctionsCalc> calc;

    /**
     * FR: Constructeur de la classe, initialise la HashMap
     * EN: Class contructor, initialize our HashMap
     */
    public Calculator() {
        this.calc = new HashMap<>();
        generateCalc();
    }

    /**
     * FR: calcule & affiche le résultat
     * EN : calculate & display result
     */
    public void getResult() {
        doCalculation();
    }

    /**
     * FR: Efface les données de la calculatrice (operator, operand, result)
     * EN: Reset the calculator's data (operator, operand, result)
     */
    public void clear() {
        this.operand = "";
        this.operator = "";
        this.result = 0;
        notifyObserver(String.valueOf(this.result));

    }

    /**
     * FR: rajoute une fonction à notre HashMap
     * EN: add a function to the HashMap
     * @param name FR: la clef / EN: key
     * @param calcFn FR: la fonction correspondante / EN: equivalent function
     */
    public void addCalc(String name, FunctionsCalc calcFn) {
        this.calc.put(name, calcFn);
    }

    /**
     * FR: lance le calcul de la fonction correspond à la clef
     * EN: execute the function that correspond to the key
     * @param name FR: l'opérateur qui correspond à la clef / EN: operator (in fact the key)
     * @param result FR: le nombre affiché à l'écran / EN: the number display to the screen
     * @param operand FR: l'opérande / EN: operand
     */
    public void doCalc(String name, Double result, String operand) {
        if (this.calc.containsKey(name)) {
            this.calc.get(name).calculate(result, operand);
        }
    }

    /**
     * FR: rajoute à la HashMap les différents calculs
     * EN: add to the HashMap all the operators
     */
    public void generateCalc() {
        addCalc("+", (result, operand) -> {
            this.result += Double.parseDouble(operand);
        });
        addCalc("-", (result, operand) -> {
            this.result -= Double.parseDouble(operand);
        });
        addCalc("*", (result, operand) -> {
            this.result = result * Double.parseDouble(operand);
        });
        addCalc("/", (result, operand) -> {
            if (operand.equals("0")) {
                this.result = 0;
            } else
                this.result = result / Double.parseDouble(operand);
        });
        addCalc("cos", (result, operand) -> {
            this.result = Math.cos(Double.parseDouble(operand));
        });
        addCalc("sin", (result, operand) -> {
            this.result = Math.sin(Double.parseDouble(operand));
        });
        addCalc("tan", (result, operand) -> {
            this.result = Math.tan(Double.parseDouble(operand));
        });
        addCalc("+/-", (result, operand) -> {
            this.result = - Double.parseDouble(operand);
        });
        addCalc("1/x", (result, operand) -> {
            if (operand.equals("0"))
                this.result = 0;
            else
                this.result = 1 / Double.parseDouble(operand);
        });
        addCalc("ln", (result, operand) -> {
            if (Double.parseDouble(operand) < 0)
                this.result = 0;
            else
                this.result = Math.log(Double.parseDouble(operand));
        });
        addCalc("e", (result, operand) -> {
            this.result = Math.exp(Double.parseDouble(operand));
        });
        addCalc("\u00B2", (result, operand) -> {
            this.result = Math.pow(Double.parseDouble(operand), 2.0);
        });
        addCalc("\u221A", (result, operand) -> {
            this.result = Math.sqrt(Double.parseDouble(operand));
        });
        addCalc("x^y", (result, operand) -> {
            this.result = Math.pow(result, Double.parseDouble(operand));
        });

    }

    /**
     * FR: lance le calcul
     * EN: do the calculation
     */

    public void doCalculation() {
        if (this.operator.equals("")) {
            if (this.operand.equals(""))
                this.result = 0;
            else
                this.result = 0 + Double.parseDouble(this.operand);
        }
        else {
            if (!this.operand.equals("")) {
               doCalc(this.operator, this.result, this.operand);
           }
        }
        this.operand = "";
        NumberFormat format = new DecimalFormat("#.####");
        notifyObserver(String.valueOf(format.format(this.result)));
    }

    /**
     * FR: on stocke l'operateur
     * EN: set operator
     * @param operator FR: l'operateur du calcul / EN: calculation's operator
     */
    public void setOperator(String operator) {
        doCalculation();
        this.operator = operator;
        if (!operator.equals("=")) {
            this.operand = "";
        }
    }

    /**
     * FR: on stocke le nombre
     * EN: set our number
     * @param number FR: le nombre utilise pour le calcul / EN: calculation's number
     */
    public void setNumber(String number) {
        if (number.equals(".") && this.operand.contains(".")) {
        }
        else if (this.operand.contains(".")) {
            if (this.operand.length() - this.operand.indexOf(".") < 5)
                this.operand += number;
                notifyObserver(this.operand);
        }
        else {
            this.operand += number;
            notifyObserver(this.operand);
        }
    }
}
