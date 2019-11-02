package Gui.Buttons;

import DataDealer.DataDealer;
import Gui.Trio;
import Math.MathDealer;
import Math.QuickMath;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ICSButton extends JButton implements ActionListener {

    public ICSButton(String name) {
        super(name);
    }

    public void actionPerformed(ActionEvent e) {

        JFrame dialogFrame = new JFrame();
        JPanel gridPanel = new JPanel(new GridLayout(2, 1));
        JPanel calculatePanel = new JPanel(new BorderLayout());
        dialogFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        dialogFrame.setSize(300, 150);
        dialogFrame.setVisible(true);


        JPanel calculateLeftPanel = new JPanel(new GridLayout(2, 1));
        JLabel labelOfAnswerField = new JLabel("Answer: ");
        JTextField answerTextField = new JTextField("answer field");
        answerTextField.setEditable(false);
        JButton submitButton = new JButton("Calculate!!!");
        calculateLeftPanel.add(labelOfAnswerField);
        calculateLeftPanel.add(submitButton);
        calculatePanel.add(BorderLayout.WEST, calculateLeftPanel);
        calculatePanel.add(answerTextField);

        Trio browseComponent = new Trio("Choose file", "e1n.dat", "Browse!!!");

        gridPanel.add(browseComponent);
        gridPanel.add(calculatePanel);

        dialogFrame.getContentPane().add(gridPanel);

        submitButton.addActionListener(actionEvent -> {
            String path = browseComponent.getFilePath();
            DataDealer data = new DataDealer(path);
            double[][] answer = MathDealer.calculateIntegralCrossSectionAndEnergyCenter(data);

            answerTextField.setText(new DoubleArrayToStringConverter().convert(answer));

        });
    }

    class DoubleArrayToStringConverter {
        String convert(double[][] array) {
            String answer = new String();
            for (double[] subArray : array) {
                for (double number : subArray) {
                    answer += QuickMath.quickRound(number) + " ";
                    System.out.println(number);
                }
            }
            return answer;
        }
    }
}
