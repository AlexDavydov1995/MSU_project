package Gui.Buttons;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BaseComplexButton extends JButton implements ActionListener {

    Logger logger = LogManager.getLogger(getClass().getName());

    BaseComplexButton(String text){
        super(text);
    }

    void finalizeButton(JFrame baseFrame) {
        baseFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        baseFrame.setSize(300, 150);
        baseFrame.setVisible(true);
    }
    public void actionPerformed(ActionEvent event){

    }
}
