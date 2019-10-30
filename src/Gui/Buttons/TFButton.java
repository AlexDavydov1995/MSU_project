package Gui.Buttons;

import DataDealer.DataDealer;
import FileDealer.FileDealer;
import Math.MathDealer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class TFButton extends JButton implements ActionListener {

    public TFButton(String name){
        super(name);
    }

    public void actionPerformed(ActionEvent e){
        JFrame dialogFrame = new JFrame();
        JPanel gridPanel = new JPanel(new GridLayout(6,1));
        JPanel fieldsPanel = new JPanel(new GridLayout(3,1));
        dialogFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        dialogFrame.setSize(400,150);
        dialogFrame.setVisible(true);

        JLabel pathToPartial = new JLabel("enter path to partial here");
        JTextField pathToPartialTextField = new JTextField("e1n.dat");
        JButton browsePartialPath = new JButton("Browse");

        JLabel pathToYield = new JLabel("enter path to yield here");
        JTextField pathToYieldTextField = new JTextField("eXn.dat");
        JButton browseYieldPath = new JButton("Browse");

        JLabel pathToTF = new JLabel("here is your TF");
        JTextField pathToTFTextField = new JTextField("");
        JButton calculateButton = new JButton("calculate");
        calculateButton.addActionListener(actionEvent -> {

            pathToTFTextField.setText("");
            DataDealer partial = new DataDealer(pathToPartialTextField.getText());
            DataDealer yield = new DataDealer(pathToYieldTextField.getText());

            try {
                DataDealer transitionalFunctions = MathDealer.calculateTransitionalFunction(partial, yield);
                FileDealer fileDealer = new FileDealer();
                String path = transitionalFunctions.getLabel()+".txt";
                fileDealer.writeAFile(path, transitionalFunctions);
                pathToTFTextField.setText(path);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        gridPanel.add(pathToPartial);
        gridPanel.add(browsePartialPath);
        gridPanel.add(pathToYield);
        gridPanel.add(browseYieldPath);
        gridPanel.add(pathToTF);
        gridPanel.add(calculateButton);

        fieldsPanel.add(pathToPartialTextField);
        fieldsPanel.add(pathToYieldTextField);
        fieldsPanel.add(pathToTFTextField);

        dialogFrame.getContentPane().add(BorderLayout.WEST,gridPanel);
        dialogFrame.getContentPane().add(fieldsPanel);




    }
}
