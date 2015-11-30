package com.company;

import javax.swing.*;

import Controler.ControlerCalculator;
import Model.Calculator;
import View.ViewCalculator;
import Model.absModel;
import Controler.absControler;

public class Main {

    /**
     * FR: On utilise Nimbus LookAndFeel car c'est plus beau
     * EN: We use Nimbus LookAndFeel
     * @param args
     */
    public static void main(String[] args) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("Can't load the LookAndFeed !");
            System.exit(0);
        }
        absModel calcModel = new Calculator();
        absControler calcControler = new ControlerCalculator(calcModel);
        ViewCalculator calculator = new ViewCalculator(calcControler);
        calcModel.addObserver(calculator);
    }
}
