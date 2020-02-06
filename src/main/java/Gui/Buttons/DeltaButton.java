package Gui.Buttons;

import Gui.Trio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class DeltaButton extends BaseComplexButton {
    DeltaButton(String text){
        super(text);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        JFrame dialogFrame = new JFrame();
        JPanel mainPanel = new JPanel(new GridLayout(3,1));
        Trio minuend = new Trio("minuend file: ");
        Trio subtract = new Trio("subtract file: ");

        JPanel answerPanel = new JPanel(new BorderLayout());
        JPanel leftPanel = new JPanel(new GridLayout(2,1));
        JLabel label = new JLabel("answer file");
        JTextField answerTextField = new JTextField("");
        answerTextField.setEditable(false);
        JButton submitButton = new JButton("calculate");
        submitButton.addActionListener(ActionListener -> {

                }

                );
    }
}
