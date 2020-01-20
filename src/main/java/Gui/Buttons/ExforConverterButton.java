package Gui.Buttons;

import Gui.Trio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ExforConverterButton extends BaseComplexButton {
    public ExforConverterButton(String text) {
        super(text);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        JFrame dialogFrame = new JFrame();
        JPanel gridPanel = new JPanel(new GridLayout(2,1));
        Trio browseTrio = new Trio("Choose a file to convert");

        JPanel converterPanel = new JPanel(new BorderLayout());
        JPanel leftPanel = new JPanel(new GridLayout(2,1));

        JLabel pathToConvertedFile = new JLabel("Path to converted file");
        JTextField pathToConvertedFileTextField = new JTextField("");
        pathToConvertedFileTextField.setEditable(false);
        JButton calculateButton = new JButton("calculate");

        calculateButton.addActionListener( actionEvent -> {

        });

        leftPanel.add(pathToConvertedFile);
        leftPanel.add(calculateButton);
        converterPanel.add(BorderLayout.WEST,leftPanel);
        converterPanel.add(pathToConvertedFileTextField);

        gridPanel.add(browseTrio);
        gridPanel.add(converterPanel);

        dialogFrame.getContentPane().add(gridPanel);
        finalizeButton(dialogFrame);

    }
}
