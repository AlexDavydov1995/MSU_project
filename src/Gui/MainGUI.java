package Gui;

import DataDealer.DataDealer;
import FileDealer.FileDealer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Math.MathDealer;

public class MainGUI {
    JPanel buttonPanel;
    JButton ICSButton;
    JButton TFButton;
    JTextField myTextField;
    JTextArea myTextArea;
    String hello = "hello";
    String bye = "bye";
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

        buttonPanel = new JPanel(new GridLayout(1,4));


        ICSButton = new JButton("calculate ICS");
        ICSButton.addActionListener(new ICSButtonListener());



        TFButton = new JButton("calculate TF");
        TFButton.addActionListener(new TFButtonListener());

        buttonPanel.add(ICSButton);
        buttonPanel.add(TFButton);

    }

    public void attachElements(JFrame myFrame) {

        myFrame.getContentPane().add(BorderLayout.NORTH,buttonPanel);



        //myFrame.getContentPane().add(BorderLayout.WEST, ICSButton);
        //myFrame.getContentPane().add(BorderLayout.EAST, TFButton);
        myFrame.getContentPane().add(BorderLayout.SOUTH, myTextField);
        myFrame.getContentPane().add(BorderLayout.CENTER, myTextArea);
        //myFrame.getContentPane().add(BorderLayout.NORTH,pathToFile1);
        //myFrame.getContentPane().add(BorderLayout.NORTH,pathToFile2);
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

     static void addButtonsToPanel(JPanel panel) {
    }

    //LISTENERS SECTION

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
    class TFButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            myTextArea.setText("");

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

}
