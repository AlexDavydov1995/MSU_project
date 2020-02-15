package Gui.Buttons;

import FileDealer.JFileChooserWrapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import vars.RunVariables;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class BaseComplexButton extends JButton implements ActionListener {

    Logger logger = LogManager.getLogger(getClass().getName());

    BaseComplexButton(String text){
        super(text);
    }

    protected void finalizeButton(JFrame baseFrame) {
        baseFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        baseFrame.setSize(400, 150);
        baseFrame.setVisible(true);
    }
    public void actionPerformed(ActionEvent event){

    }

    protected String setFullPath(String fileName){
        return JFileChooserWrapper.getLastFolder() + RunVariables.FILE_SEPARATOR+fileName;
    }

}
