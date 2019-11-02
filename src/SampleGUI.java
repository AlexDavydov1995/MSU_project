import Gui.Trio;

import javax.swing.*;
import java.awt.*;


public class SampleGUI {


    public static void main(String... args) {
        SampleGUI myGUI = new SampleGUI();
        myGUI.go();
    }

    public void go() {
        JFrame myFrame = new JFrame();
        myFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        myFrame.setSize(200, 100);

        Trio bt = new Trio("one","two","three");
        myFrame.setLayout(new GridLayout(1,1));
        //myFrame.getContentPane().add(new JButton("adding button"));
        myFrame.getContentPane().add(bt);
        myFrame.setVisible(true);
    }
}












