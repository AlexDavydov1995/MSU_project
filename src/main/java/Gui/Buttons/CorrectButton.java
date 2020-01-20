package Gui.Buttons;

import DataDealer.DataDealer;
import FileDealer.FileDealer;
import Math.CorrectionCalculator;
import Gui.Trio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CorrectButton extends BaseComplexButton {

    public CorrectButton(String name){
        super(name);
    }

    @Override
    public void actionPerformed(ActionEvent e){

        JFrame dialogFrame = new JFrame();
        dialogFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        dialogFrame.setSize(400,200);

        JPanel mainGrid = new JPanel(new GridLayout(4,1));

        Trio fileToCorrect = new Trio("enter file you want to correct here");

        JLabel energyLabel = new JLabel("enter energy correction here:");
        JTextField energyField = new JTextField("0");
        JLabel ampLabel = new JLabel("enter amp correction here:");
        JTextField ampField = new JTextField("1");
        JButton correctButton = new JButton("correct");
        JTextField correctField = new JTextField("");
        correctField.setEditable(false);

        correctButton.addActionListener(ActionListener -> {
            DataDealer data = new DataDealer(fileToCorrect.getFilePath());
            double energyCor = Double.parseDouble(energyField.getText());
            double ampCor = Double.parseDouble(ampField.getText());
            try {
                CorrectionCalculator corCalculator = new CorrectionCalculator();
                DataDealer corData = corCalculator.calculate(data, energyCor, ampCor);
                FileDealer fileDealer = new FileDealer();
                String path = corData.getLabel()+".txt";
                fileDealer.writeAFileFromDataDealer(path, corData);
                correctField.setText(path);
            }
            catch (Exception ex){
                ex.printStackTrace();
            }
        });

        mainGrid.add(fileToCorrect);

        JPanel energyGrid = new JPanel(new BorderLayout());
        JPanel ampGrid = new JPanel(new BorderLayout());
        JPanel corGrid = new JPanel(new BorderLayout());

        energyGrid.add(BorderLayout.WEST, energyLabel);
        energyGrid.add(energyField);

        ampGrid.add(BorderLayout.WEST, ampLabel);
        ampGrid.add(ampField);

        corGrid.add(BorderLayout.WEST, correctButton);
        corGrid.add(correctField);

        mainGrid.add(energyGrid);
        mainGrid.add(ampGrid);
        mainGrid.add(corGrid);

        dialogFrame.getContentPane().add(mainGrid);
        dialogFrame.setVisible(true);


    }
}
