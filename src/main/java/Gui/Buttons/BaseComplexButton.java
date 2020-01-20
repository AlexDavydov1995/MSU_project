package Gui.Buttons;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BaseComplexButton extends JButton implements ActionListener {
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
