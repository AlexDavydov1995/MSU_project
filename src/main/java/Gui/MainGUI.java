package Gui;

import Gui.Buttons.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import vars.RunVariables;

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
    ExtrapolationButton extrapolationButton;
    ExforConverterButton exforConverterButton;

    JButton openDialogButton;

    public void go() {
        Logger logger = LogManager.getLogger();
        logger.info(RunVariables.FILE_SEPARATOR);
        JFrame myFrame = new JFrame();
        initializeElements();
        attachElements(myFrame);
    }

    private void initializeElements() {

        buttonPanel = new JPanel(new GridLayout(7, 1));

        ICSButton = new ICSButton("calculate ICS");
        ICSButton.addActionListener(ICSButton);

        TFButton = new TFButton("calculate TF");
        TFButton.addActionListener(TFButton);

        openDialogButton = new JButton("open dialog window");
        openDialogButton.addActionListener(new OpenDialogButtonListener());

        correctButton = new CorrectButton("correct button");
        correctButton.addActionListener(correctButton);

        evaluateButton = new EvaluateButton("evaluate button");
        evaluateButton.addActionListener(evaluateButton);

        sumButton = new SumButton("sum button");
        sumButton.addActionListener(sumButton);

        extrapolationButton = new ExtrapolationButton("set new energy grid");
        extrapolationButton.addActionListener(extrapolationButton);

        exforConverterButton = new ExforConverterButton("ExforConverter");
        exforConverterButton.addActionListener(exforConverterButton);

        buttonPanel.add(ICSButton);
        buttonPanel.add(TFButton);
        buttonPanel.add(correctButton);
        buttonPanel.add(evaluateButton);
        buttonPanel.add(sumButton);
        buttonPanel.add(extrapolationButton);
        buttonPanel.add(exforConverterButton);
    }

    public void attachElements(JFrame myFrame) {

        myFrame.getContentPane().add(BorderLayout.EAST, buttonPanel);

        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setSize(300, 500);
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
    static class OpenDialogButtonListener implements ActionListener {
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
