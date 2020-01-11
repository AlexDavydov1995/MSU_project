package Gui.Buttons;

import DataDealer.DataDealer;
import FileDealer.FileDealer;
import Gui.Trio;
import Math.TransitionalFunctionsCalculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class TFButton extends BaseComplexButton implements ActionListener {

    public TFButton(String name){
        super(name);
    }

    public void actionPerformed(ActionEvent e){
        JFrame dialogFrame = new JFrame();
        JPanel gridPanel = new JPanel(new GridLayout(3,1));

        Trio partialTrio = new Trio("enter path to partial here");

        Trio yieldTrio = new Trio("enter path to yield here");

        JPanel calculatePanel = new JPanel(new BorderLayout());
        JPanel leftPanel = new JPanel(new GridLayout(2,1));
        JLabel pathToTF = new JLabel("here is your TF");
        JTextField pathToTFTextField = new JTextField("");
        pathToTFTextField.setEditable(false);
        JButton calculateButton = new JButton("calculate");
        calculateButton.addActionListener(actionEvent -> {

            pathToTFTextField.setText("");
            DataDealer partial = new DataDealer(partialTrio.getFilePath());
            DataDealer yield = new DataDealer(yieldTrio.getFilePath());

            try {
                TransitionalFunctionsCalculator calculator = new TransitionalFunctionsCalculator();
                DataDealer transitionalFunctions = calculator.calculate(partial, yield);
                FileDealer fileDealer = new FileDealer();
                String path = transitionalFunctions.getLabel()+".txt";
                fileDealer.writeAFile(path, transitionalFunctions);
                pathToTFTextField.setText(path);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        leftPanel.add(pathToTF);
        leftPanel.add(calculateButton);
        calculatePanel.add(BorderLayout.WEST,leftPanel);
        calculatePanel.add(pathToTFTextField);

        gridPanel.add(partialTrio);
        gridPanel.add(yieldTrio);
        gridPanel.add(calculatePanel);

        dialogFrame.getContentPane().add(gridPanel);

        finalizeButton(dialogFrame);

    }
}
