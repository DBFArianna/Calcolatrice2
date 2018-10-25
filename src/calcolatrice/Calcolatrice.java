package calcolatrice;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Calcolatrice extends JFrame implements ActionListener {

    JButton[] b = new JButton[20];
    String[] s = {
        "1", "2", "3", "+",
        "4", "5", "6", "-",
        "7", "8", "9", "*",
        "CE", "0", "C", "/",
        "", "", "", "="};
    JTextField display;
    long operando1, operando2, risultato;
    String operazione;
    boolean inizio = true;

    public Calcolatrice() {
        super("Calcolatrice");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(600, 500));
        JPanel pFrame = new JPanel();
        pFrame.setLayout(new BorderLayout());
        add(pFrame);

        display = new JTextField("");
        display.setFont(new Font("Arial", 0, 20));
        display.setPreferredSize(new Dimension(80, 40));
        display.setHorizontalAlignment(JTextField.RIGHT);
        pFrame.add(display, BorderLayout.NORTH);

        JPanel pCenter = new JPanel();
        pCenter.setLayout(new GridLayout(5, 4, 3, 3));
        for (int i = 0; i < 20; i++) {
            b[i] = new JButton();
            b[i].setText(s[i]);
            b[i].setFont(new Font("Arial", 0, 20));
            b[i].setPreferredSize(new Dimension(80, 40));
            b[i].addActionListener(this);
            pCenter.add(b[i]);
        }
        pFrame.add(pCenter, BorderLayout.CENTER);

        pack();
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        JButton b = (JButton) e.getSource();
        String s = b.getText();
        if (inizio) {
            display.setText("");
            operando1 = 0;
            operando2 = 0;
            inizio = false;
        }
        switch (s) {
            case "+":
            case "-":
            case "*":
            case "/":
                operando1 = Long.parseLong(display.getText());
                operazione = s;
                display.setText("");
                break;
            case "=":
                operando2 = Long.parseLong(display.getText());
                switch (operazione) {
                    case "+":
                        risultato = operando1 + operando2;
                        break;
                    case "-":
                        risultato = operando1 - operando2;
                        break;
                    case "*":
                        risultato = operando1 * operando2;
                        break;
                    case "/":
                        risultato = (operando2 == 0) ? 0 : operando1 / operando2;
                }
                display.setText(Long.toString(risultato));
                operando1 = operando2 = 0;
                inizio = true;
                break;
            case "C":
                display.setText(Long.toString(risultato));
                operando1 = risultato;
                operando2 = 0;
                break;
            case "CE": ;
                display.setText("");
                operando1 = operando2 = 0;
                inizio = true;
                break;
            default:
                display.setText(display.getText() + s);
        }
    }

    public static void test() {
        Calcolatrice c = new Calcolatrice();
    }
}
