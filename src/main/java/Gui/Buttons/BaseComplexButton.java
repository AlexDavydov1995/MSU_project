package Gui.Buttons;

import javax.swing.*;

public class BaseComplexButton extends JButton {
    BaseComplexButton(String text){
        super(text);
    }

    void finalizeButton(JFrame baseFrame) {
        baseFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        baseFrame.setSize(300, 150);
        baseFrame.setVisible(true);
    }
}
