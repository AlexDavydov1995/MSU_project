package Gui.Buttons;

import DataDealer.DataDealer;
import FileDealer.FileDealer;
import Gui.Trio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import Math.DeltaCalculator;


public class DeltaButton extends BaseComplexButton {
    public DeltaButton(String text) {
        super(text);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        JFrame dialogFrame = new JFrame();
        JPanel mainPanel = new JPanel(new GridLayout(3, 1));
        Trio minuend = new Trio("minuend file: ");
        Trio subtract = new Trio("subtract file: ");

        JPanel answerPanel = new JPanel(new BorderLayout());
        JPanel leftPanel = new JPanel(new GridLayout(2, 1));
        JLabel label = new JLabel("answer file");
        JTextField answerTextField = new JTextField("");
        answerTextField.setEditable(false);
        JButton submitButton = new JButton("calculate");
        submitButton.addActionListener(ActionListener -> {
            DataDealer minuendDataDealer = new DataDealer(minuend.getFilePath());
            DataDealer subtractDataDealer = new DataDealer(subtract.getFilePath());
            try {
                DeltaCalculator calculator = new DeltaCalculator();
                DataDealer answer = calculator.calculate(minuendDataDealer, subtractDataDealer);
                FileDealer fileDealer = new FileDealer();
                String path = setFullPath(answer.getLabel() + ".txt");
                fileDealer.writeAFileFromDataDealer(path, answer);
                answerTextField.setText(path);
            } catch (Exception e) {
                logger.error("There is an error", e);
            }
        });
        leftPanel.add(label);
        leftPanel.add(submitButton);
        answerPanel.add(BorderLayout.WEST,leftPanel);
        answerPanel.add(answerTextField);

        mainPanel.add(minuend);
        mainPanel.add(subtract);
        mainPanel.add(answerPanel);

        dialogFrame.add(mainPanel);

        finalizeButton(dialogFrame);
    }
}
