package Gui.Buttons;

import DataDealer.DataDealer;
import Gui.Trio;

import Math.BasicMath;
import Math.IntegralCrossSectionCalculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Path;

public class ICSButton extends BaseComplexButton {

    public ICSButton(String name) {
        super(name);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        JFrame dialogFrame = new JFrame();
        JPanel gridPanel = new JPanel(new GridLayout(3, 1));
        JPanel rangePanel = new JPanel(new GridLayout(1,4));
        JLabel leftBoundLabel = new JLabel("Left bound ");
        JTextField leftBoundTextField = new JTextField("");
        JLabel rightBoundLabel = new JLabel("Right bound ");
        JTextField rightBoundTextField = new JTextField("");
        rangePanel.add(leftBoundLabel);
        rangePanel.add(leftBoundTextField);
        rangePanel.add(rightBoundLabel);
        rangePanel.add(rightBoundTextField);
        JPanel calculatePanel = new JPanel(new BorderLayout());



        JPanel calculateLeftPanel = new JPanel(new GridLayout(2, 1));
        JLabel labelOfAnswerField = new JLabel("Answer: ");
        JTextField answerTextField = new JTextField("answer field");
        answerTextField.setEditable(false);
        JButton submitButton = new JButton("Calculate!!!");
        calculateLeftPanel.add(labelOfAnswerField);
        calculateLeftPanel.add(submitButton);
        calculatePanel.add(BorderLayout.WEST, calculateLeftPanel);
        calculatePanel.add(answerTextField);

        Trio browseComponent = new Trio("Choose file");

        gridPanel.add(rangePanel);
        gridPanel.add(browseComponent);
        gridPanel.add(calculatePanel);

        dialogFrame.getContentPane().add(gridPanel);

        submitButton.addActionListener(actionEvent -> {
            Path path = browseComponent.getFilePath();
            DataDealer data = new DataDealer(path);
            IntegralCrossSectionCalculator intCalculator = new IntegralCrossSectionCalculator();
            double[][] answer = intCalculator.calculate(data);
            answerTextField.setText(new DoubleArrayToStringConverter().convert(answer));
        });

        finalizeButton(dialogFrame);
    }

     static class DoubleArrayToStringConverter {
         String convert(double[][] array) {
            String answer = "";
            for (double[] subArray : array) {
                for (double number : subArray) {
                    answer += BasicMath.quickRound(number,2) + " ";
                }
            }
            return answer;
        }
    }
}
