import DataDealer.DataDealer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import DataDealer.DataDealer;


public class SampleGUI implements ActionListener {
    JButton firstButton;
    JTextField myTextField;
    JTextArea myTextArea;
    String hello = "hello kate";
    String love = "i love you";
    String path = "D:\\downloads\\MSU_project-myBranch\\MSU_project-myBranch\\src\\example.txt";
    String pathToTestFile = "D:\\downloads\\MSU_project-myBranch\\MSU_project-myBranch\\e1n.dat";


    public static void main(String... args){
        SampleGUI myGUI = new SampleGUI();
        myGUI.go();
    }

    public void go(){
        JFrame myFrame = new JFrame();
        initializeElements();
        attachElements(myFrame);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        myTextArea.setText("");
        if (firstButton.getText()==hello)
            firstButton.setText(love);
        else
            firstButton.setText(hello);
        myTextField.setText(Double.toString(calculate()));
        //FileDealer reader = new FileDealer();
        //showFile(reader.readAFile(path));
        DataDealer dataDealer = new DataDealer(pathToTestFile);
        dataDealer.showDataInConsole();
        showData(dataDealer);

    }

    private void showData(DataDealer dataDealer){
        for(int i=0; i< dataDealer.getLength();i++){
            myTextArea.append(makeStringFromData(dataDealer,i)+"\n");
        }
    }

    private String makeStringFromData(DataDealer dataDealer, int index){
        return dataDealer.getEnergyByIndex(index)+" "+dataDealer.getCrossSectionByIndex(index)+" "+dataDealer.getCrossSectionErrorByIndex(index);
    }

    public double calculate(){
        return Math.round(Math.random()*100);
    }



    private void initializeElements(){
        myTextField = new JTextField();
        myTextArea = new JTextArea(hello);
        firstButton = new JButton(hello);
        firstButton.addActionListener(this);
    }

    public void attachElements(JFrame myFrame){
        myFrame.getContentPane().add(BorderLayout.WEST, firstButton);
        myFrame.getContentPane().add(BorderLayout.SOUTH, myTextField);
        myFrame.getContentPane().add(BorderLayout.CENTER,myTextArea);
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setSize(300,300);
        myFrame.setVisible(true);
    }
}

