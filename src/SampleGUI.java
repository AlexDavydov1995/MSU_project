import DataDealer.DataDealer;
import FileDealer.FileDealer;
import Math.MathDealer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class SampleGUI {
    JButton firstButton;
    JButton secondButton;
    JTextField myTextField;
    JTextArea myTextArea;
    String hello = "hello";
    String bye = "bye";
    //String path = "D:\\downloads\\MSU_project-myBranch\\MSU_project-myBranch\\src\\example.txt";
    String pathToTestFile = "D:\\downloads\\MSU_project-myBranch\\MSU_project-myBranch\\e1n.dat";
    String pathToSecondTestFile = "D:\\downloads\\MSU_project-myBranch\\MSU_project-myBranch\\eXn.dat";


    public static void main(String... args) {
        SampleGUI myGUI = new SampleGUI();
        myGUI.go();
    }

    public void go() {
        JFrame myFrame = new JFrame();
        initializeElements();
        attachElements(myFrame);
    }

    private void showData(DataDealer dataDealer) {
        for (int i = 0; i < dataDealer.getLength(); i++) {
            myTextArea.append(makeStringFromData(dataDealer, i) + "\n");
        }
    }

    private String makeStringFromData(DataDealer dataDealer, int index) {
        return dataDealer.getEnergyByIndex(index) + " " + dataDealer.getCrossSectionByIndex(index) + " " + dataDealer.getCrossSectionErrorByIndex(index);
    }

    private String makeStringFromAnswer(double[][] answer) {
        try {
            return round(answer[0][0]) + " " + round(answer[0][1]) + " " + round(answer[1][0]) + " " + round(answer[1][1]);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public double[][] calculate(DataDealer data) {
        MathDealer mathDealer = new MathDealer();
        return mathDealer.calculateIntegralCrossSectionAndEnergyCenter(data);
    }

    private double round(double number) {
        long tempNumber = Math.round(number * 100);
        return (double) tempNumber / 100;
    }


    private void initializeElements() {
        myTextField = new JTextField();
        myTextArea = new JTextArea(hello);
        firstButton = new JButton( new AbstractAction("hello") {
            public void actionPerformed(ActionEvent event){
                myTextArea.setText("");
                if (firstButton.getText() == hello)
                    firstButton.setText(bye);
                else
                    firstButton.setText(hello);

                //FileDealer reader = new FileDealer();
                //showFile(reader.readAFile(path));
                DataDealer dataDealer = new DataDealer(pathToTestFile);
                myTextField.setText(makeStringFromAnswer(calculate(dataDealer)));
                dataDealer.showDataInConsole();
                showData(dataDealer);
            }
        });
        secondButton = new JButton(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e)  {
                DataDealer partial = new DataDealer(pathToTestFile);
                DataDealer yield = new DataDealer(pathToSecondTestFile);
                try {
                    DataDealer transitionalFunctions = MathDealer.calculateTransitionalFunction(partial,yield);
                    FileDealer fileDealer = new FileDealer();
                    fileDealer.writeAFile("testAnswer.txt",transitionalFunctions);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

    }

    public void attachElements(JFrame myFrame) {
        myFrame.getContentPane().add(BorderLayout.WEST, firstButton);
        myFrame.getContentPane().add(BorderLayout.SOUTH, myTextField);
        myFrame.getContentPane().add(BorderLayout.CENTER, myTextArea);
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setSize(300, 300);
        myFrame.setVisible(true);
    }
}

