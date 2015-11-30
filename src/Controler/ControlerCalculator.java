package Controler;

import Model.absModel;

import java.io.Console;

public class ControlerCalculator extends absControler {
    public ControlerCalculator(absModel modelCalc) {
        super(modelCalc);
    }

    /**
     * FR: vérifie l'operateur et le nombre
     * EN: check operator and number
     */
    public void controler() {
        if (this.operator.equals("="))
            this.calcModel.getResult();
        else if (!this.operator.equals("")) {
            this.calcModel.setOperator(this.operator);
        }

        if (this.number.matches("^[0-9.]+$"))
            this.calcModel.setNumber(this.number);

        this.operator = "";
        this.number = "";
    }
}
