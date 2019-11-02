package Gui;

import DataDealer.DataDealer;
import FileDealer.FileDealer;
import Gui.Buttons.*;
import Math.MathDealer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainGUI {
    JPanel buttonPanel;

    ICSButton ICSButton;
    TFButton TFButton;
    CorrectButton correctButton;
    EvaluateButton evaluateButton;
    SumButton sumButton;

    JButton openDialogButton;
    JTextField myTextField;

    JTextArea myTextArea;
    String hello = "hello";
    String pathToTestFile = "e1n.dat";
    String pathToSecondTestFile = "eXn.dat";
    JTextField pathToFile1;
    JTextField pathToFile2;

    public void go() {
        JFrame myFrame = new JFrame();
        initializeElements();
        attachElements(myFrame);
    }

    private void initializeElements() {


        myTextField = new JTextField();
        myTextArea = new JTextArea(hello);

        pathToFile1 = new JTextField(hello);
        pathToFile2 = new JTextField(hello);

        buttonPanel = new JPanel(new GridLayout(6, 1));

        ICSButton = new ICSButton("calculate ICS");
        ICSButton.addActionListener(ICSButton);

        TFButton = new TFButton("calculate TF");
        TFButton.addActionListener(TFButton);


        openDialogButton = new JButton("open dialog window");
        openDialogButton.addActionListener(new OpenDialogButtonListener());

        correctButton = new CorrectButton("correct button");

        evaluateButton = new EvaluateButton("evaluate button");

        sumButton = new SumButton("sum button");

        buttonPanel.add(ICSButton);
        buttonPanel.add(TFButton);
        buttonPanel.add(correctButton);
        buttonPanel.add(evaluateButton);
        buttonPanel.add(sumButton);
        buttonPanel.add(openDialogButton);

    }

    public void attachElements(JFrame myFrame) {

        myFrame.getContentPane().add(BorderLayout.EAST, buttonPanel);

        myFrame.getContentPane().add(BorderLayout.SOUTH, myTextField);
        myFrame.getContentPane().add(BorderLayout.CENTER, myTextArea);
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setSize(300, 300);
        myFrame.setVisible(true);

    }

    private String makeStringFromAnswer(double[][] answer) {
        try {
            return round(answer[0][0]) + " " + round(answer[0][1]) + " " + round(answer[1][0]) + " " + round(answer[1][1]);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    private double round(double number) {
        long tempNumber = Math.round(number * 100);
        return (double) tempNumber / 100;
    }

    //LISTENERS SECTION
/*
    class ICSButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            myTextArea.setText("");
            if (ICSButton.getText() == hello)
                ICSButton.setText(bye);
            else
                ICSButton.setText(hello);

            //FileDealer reader = new FileDealer();
            //showFile(reader.readAFile(path));
            DataDealer dataDealer = new DataDealer(pathToTestFile);
            myTextField.setText(makeStringFromAnswer(MathDealer.calculateIntegralCrossSectionAndEnergyCenter(dataDealer)));
            dataDealer.showDataInConsole();
            //showData(dataDealer);
        }
    }

 */
    class TFButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            DataDealer partial = new DataDealer(pathToTestFile);
            DataDealer yield = new DataDealer(pathToSecondTestFile);

            try {
                DataDealer transitionalFunctions = MathDealer.calculateTransitionalFunction(partial, yield);
                FileDealer fileDealer = new FileDealer();
                fileDealer.writeAFile("testAnswer.txt", transitionalFunctions);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    class OpenDialogButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFrame dialogFrame = new JFrame();
            dialogFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            dialogFrame.setSize(200, 200);
            dialogFrame.setVisible(true);

            JButton tempButton = new JButton("hello");

            dialogFrame.getContentPane().add(tempButton);
        }
    }

}
