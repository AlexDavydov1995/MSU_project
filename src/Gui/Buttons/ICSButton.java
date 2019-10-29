package Gui.Buttons;

import DataDealer.DataDealer;
import Math.MathDealer;
import Math.QuickMath;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ICSButton extends JButton implements ActionListener {

    public ICSButton(String name){
        super(name);
    }

    public void actionPerformed(ActionEvent e){

        JFrame dialogFrame = new JFrame();
        JPanel gridPanel = new JPanel(new GridLayout(4,1));
        JPanel fieldsPanel = new JPanel(new GridLayout(2,1));
        dialogFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        dialogFrame.setSize(300,100);
        dialogFrame.setVisible(true);

        JLabel labelOfTextField = new JLabel("Path to file: ");
       JTextField pathTextField = new JTextField("e1n.dat");
       JButton browseButton = new JButton("Browse!!!");

       JLabel labelOfAnswerField = new JLabel("Answer: ");
       JTextField answerTextField = new JTextField("answer field");
       answerTextField.setEditable(false);
       JButton submitButton = new JButton("Calculate!!!");

        gridPanel.add(labelOfTextField);
        gridPanel.add(browseButton);
        gridPanel.add(labelOfAnswerField);
        gridPanel.add(submitButton);


       dialogFrame.getContentPane().add(BorderLayout.WEST,gridPanel);


       fieldsPanel.add(pathTextField);
       fieldsPanel.add(answerTextField);
       dialogFrame.getContentPane().add(fieldsPanel);
       submitButton.addActionListener( actionEvent -> {
           String path = pathTextField.getText();
           DataDealer data = new DataDealer(path);
           double[][] answer = MathDealer.calculateIntegralCrossSectionAndEnergyCenter(data);

           answerTextField.setText(new DoubleArrayToStringConverter().convert(answer));

       });
    }

    class DoubleArrayToStringConverter{
         String convert(double[][] array ){
            String answer = new String();
            for(double[] subArray : array){
                for(double number : subArray){
                    answer+= QuickMath.quickRound(number)+" ";
                    System.out.println(number);
                }
            }
            return answer;
        }
    }
}
