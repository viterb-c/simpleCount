package View;

import Controler.absControler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Console;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Observable;
import java.util.Observer;
import Model.absModel;

public class ViewCalculator extends JFrame implements Observer {

    private static int WINDOW_WIDTH = 450;
    private static int WINDOW_HEIGHT = 300;
    private  JPanel _container = new JPanel();
    private JTextField resultText;

    private absControler _controler;

    public ViewCalculator(absControler controler) {
        this.setTitle("Calculator N°42");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        initComponants();
        this.setVisible(true);
        this._controler = controler;
    }

    /**
     * FR: JTextField pour afficher le resultat et un JPanel pour le contenir
     * EN: JTextFiel to display result and a JPanel to contain it
     * @param mainPanel Notre panel principal / Main panel
     */
    private void setTextUI(JPanel mainPanel) {
        JPanel resultPanel = new JPanel();
        resultPanel.setPreferredSize(new Dimension(WINDOW_WIDTH - 20, 50));
        resultText = new JTextField();
        resultText.setEditable(false);
        resultText.setPreferredSize(new Dimension(WINDOW_WIDTH - 20, 40));
        resultText.setText("0");
        resultText.setHorizontalAlignment(SwingConstants.RIGHT);
        resultText.setFont(new Font(null, Font.PLAIN, 23));
        resultPanel.add(resultText);
        mainPanel.add(resultPanel);
    }

    /**
     * FR: On definit les boutons du JPanel actionPanel qui contiendra les boutons des nombres + '=' + '.'
     * EN: Set all numbers' buttons and '=' and '.'
     * @param actionPanel FR: le JPanel qui contiendra les boutons / EN: JPanel that contains buttons
     */
    private void setNumberButtons(JPanel actionPanel) {
        JPanel numberPanel = new JPanel(new GridLayout(4, 3));
        numberPanel.setPreferredSize(new Dimension(200, 350));
        numberPanel.setSize(200, 350);
        actionPanel.add(numberPanel);

        ArrayList<String> listButton = new ArrayList<>();
        listButton.add("7");
        listButton.add("8");
        listButton.add("9");
        listButton.add("4");
        listButton.add("5");
        listButton.add("6");
        listButton.add("1");
        listButton.add("2");
        listButton.add("3");
        listButton.add(".");
        listButton.add("0");
        listButton.add("=");
        for (String buttonName : listButton) {
            JButton button = new JButton(buttonName);
            if (buttonName.equals("=")) {
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        _controler.setOperator(((JButton) e.getSource()).getText());
                    }
                });
            } else {
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String str = ((JButton) e.getSource()).getText();
                        String rslt = resultText.getText();
                        if (str.equals(".") && rslt.contains(".")) {
                        }
                        else if (rslt.contains(".")) {
                            if (rslt.length() - rslt.indexOf(".") < 5)
                                if (!resultText.getText().equals("0"))
                                    resultText.setText(resultText.getText() + str);
                            _controler.setNumber(str);
                        }
                        else {
                            resultText.setText(resultText.getText() + str);
                            _controler.setNumber(str);
                        }
                    }
                });
            }
            numberPanel.add(button);
        }
    }

    /**
     * FR: On definit les boutons pour les opérateurs
     * EN: operators' buttons
     * @param actionPanel FR: le JPanel qui contiendra le JPanel des boutons / EN: JPanel that contains a JPanel with buttons
     */
    private void setOperatorButtons(JPanel actionPanel) {
        JPanel operatorPanel = new JPanel(new GridLayout(5, 3));
        operatorPanel.setPreferredSize(new Dimension(180, 350));
        operatorPanel.setSize(new Dimension(180, 350));
        actionPanel.add(operatorPanel);
        ArrayList<String> listOperator = new ArrayList<>();
        listOperator.add("+");
        listOperator.add("cos");
        listOperator.add("ln");
        listOperator.add("-");
        listOperator.add("sin");
        listOperator.add("e");
        listOperator.add("*");
        listOperator.add("tan");
        listOperator.add("+/-");
        listOperator.add("/");
        listOperator.add("\u00B2");
        listOperator.add("1/x");
        listOperator.add("x^y");
        listOperator.add("\u221A");
        listOperator.add("C");


        for (String buttonName : listOperator) {
            JButton button = new JButton(buttonName);
            if (buttonName.equals("C")) {
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        _controler.clear();
                    }
                });
            }
            else {
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        _controler.setOperator(((JButton) e.getSource()).getText());
                    }
                });
            }
            operatorPanel.add(button);
        }
    }

    /**
     * FR: initialisation des differents JPanel
     * EN: initialize all JPanel
     */
    private void initComponants() {
        /**
         * FR: Un JPanel qui contiendra les autres (operateusr, memoires et chiffres) + text resultat
         * EN: A JPanel that contains others JPanel
         */
        JPanel mainPanel = new JPanel();
        this.getContentPane().add(mainPanel);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        setTextUI(mainPanel);

        /**
         * FR: JPanel qui contiendra les boutons
         * EN: JPanel contains buttons
         */
        JPanel actionPanel = new JPanel();
        actionPanel.setLayout(new BoxLayout(actionPanel, BoxLayout.X_AXIS));
        mainPanel.add(actionPanel);
        setNumberButtons(actionPanel);
        setOperatorButtons(actionPanel);
        }

    /**
     * FR: Met a jour le texte
     * EN: update our text
     * @param o FR: notre observable / EN: our observable
     * @param str FR: notre texte a afficher / EN: our string to display
     */
    public void update(Observable o, Object str) {
        resultText.setText((String)str);
    }
}

