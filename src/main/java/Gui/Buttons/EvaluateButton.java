package Gui.Buttons;

import DataDealer.DataDealer;
import FileDealer.FileDealer;
import Gui.Trio;
import Math.EvaluatedCrossSectionCalculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;


public class EvaluateButton extends BaseComplexButton {

    public EvaluateButton(String name){
        super(name);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        JFrame dialogFrame = new JFrame();

        JPanel mainGrid = new JPanel(new GridLayout(3,1));

        Trio abutmentTrio = new Trio("enter abutment file here: ");
        Trio tfTrio = new Trio("enter TF file here: ");

        JPanel answerPanel = new JPanel(new BorderLayout());
        JPanel leftAnswerPanel = new JPanel(new GridLayout(2,1));

        JLabel answerLabel = new JLabel("answer file: ");
        JTextField answerTextField = new JTextField("");
        answerTextField.setEditable(false);
        JButton calcButton = new JButton("Calculate");
        calcButton.addActionListener(ActionListener -> {

            DataDealer abutmentData = new DataDealer(abutmentTrio.getFilePath());
            DataDealer tfData = new DataDealer(tfTrio.getFilePath());
            try {
                EvaluatedCrossSectionCalculator calculator = new EvaluatedCrossSectionCalculator();
                DataDealer answer = calculator.calculate(tfData, abutmentData);
                FileDealer fileDealer = new FileDealer();
                String path = answer.getLabel()+".txt";
                fileDealer.writeAFileFromDataDealer(path,answer);
                answerTextField.setText(path);
            }
            catch (Exception ex){
                ex.printStackTrace();
            }

        });
        leftAnswerPanel.add(answerLabel);
        leftAnswerPanel.add(calcButton);
        answerPanel.add(BorderLayout.WEST,leftAnswerPanel);
        answerPanel.add(answerTextField);

        mainGrid.add(tfTrio);
        mainGrid.add(abutmentTrio);
        mainGrid.add(answerPanel);

        dialogFrame.add(mainGrid);

        finalizeButton(dialogFrame);
    }
}
