package Gui.Buttons;

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
        JPanel gridPanel = new JPanel(new GridLayout(2,1));
        dialogFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        dialogFrame.setSize(300,100);
        dialogFrame.setVisible(true);

        JLabel labelOfTextField = new JLabel("Path to file: ");
       JTextField pathTextField = new JTextField("enter path here");
       JButton submitButton = new JButton("Submit!!!");
        gridPanel.add(labelOfTextField);
        gridPanel.add(submitButton);
       dialogFrame.getContentPane().add(BorderLayout.WEST,gridPanel);
       dialogFrame.getContentPane().add(pathTextField);





    }
}
