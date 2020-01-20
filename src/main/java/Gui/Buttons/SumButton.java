package Gui.Buttons;

import DataDealer.DataDealer;
import FileDealer.FileDealer;
import Math.SumCalculator;
import Gui.Trio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SumButton extends BaseComplexButton {

    public SumButton(String name){
        super(name);
    }

    @Override
    public void actionPerformed(ActionEvent e){

        JFrame dialogFrame = new JFrame();

        JPanel mainGrid = new JPanel(new GridLayout(3,1));

        Trio firstFile = new Trio("enter name of first file");
        Trio secondFile = new Trio("enter name of second file");

        JPanel sumBorder = new JPanel(new BorderLayout());
        JPanel sumButtonGrid = new JPanel(new GridLayout(2,1));

        JLabel sumLabel = new JLabel("sum file: ");
        JButton sumButton = new JButton("Calc");

        JTextField sumTextField = new JTextField("");
        sumTextField.setEditable(false);

        sumButton.addActionListener(ActionListener -> {
            DataDealer firstData = new DataDealer(firstFile.getFilePath());
            DataDealer secondData = new DataDealer(secondFile.getFilePath());
            try {
                SumCalculator calculator = new SumCalculator();
                DataDealer sumData = calculator.calculate(firstData,secondData);
                FileDealer fileDealer = new FileDealer();
                String path = sumData.getLabel() + ".txt";
                fileDealer.writeAFileFromDataDealer(path, sumData);
                sumTextField.setText(path);
            }
            catch (Exception ex){
                ex.printStackTrace();
            }
        });

        sumButtonGrid.add(sumLabel);
        sumButtonGrid.add(sumButton);

        sumBorder.add(BorderLayout.WEST,sumButtonGrid);
        sumBorder.add(sumTextField);

        mainGrid.add(firstFile);
        mainGrid.add(secondFile);
        mainGrid.add(sumBorder);

        dialogFrame.getContentPane().add(mainGrid);
        finalizeButton(dialogFrame);
    }
}
