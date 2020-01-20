package Gui.Buttons;

import Gui.Trio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExtrapolationButton extends BaseComplexButton {
    public ExtrapolationButton(String text){
        super(text);
    }

    @Override
   public void actionPerformed(ActionEvent e) {
       JFrame dialogFrame = new JFrame();
       JPanel gridPanel = new JPanel(new GridLayout(3, 1));

       Trio subjectFile = new Trio("Enter subject file");
       Trio abutmentFile = new Trio("Enter abutment file");

       JPanel calculatePanel = new JPanel(new BorderLayout());

       JPanel leftCalculatePanel = new JPanel(new GridLayout(2,1));
       JTextField pathToAnswerFile = new JTextField("");
       JLabel answerLabel = new JLabel("path to answer file:");
       JButton calculateButton = new JButton("Calculate");

       leftCalculatePanel.add(answerLabel);
       leftCalculatePanel.add(calculateButton);
       calculatePanel.add(BorderLayout.WEST,leftCalculatePanel);
       calculatePanel.add(pathToAnswerFile);
       gridPanel.add(subjectFile);
       gridPanel.add(abutmentFile);
       gridPanel.add(calculatePanel);
       dialogFrame.getContentPane().add(gridPanel);
       finalizeButton(dialogFrame);
    }


}
