package Gui.interfaces;

import javax.swing.*;

public class MyRunnable implements Runnable{
    @Override
    public void run(){
        go();
    }

    private void go(){
        JFrame dialogFrame = new JFrame();
        dialogFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        dialogFrame.setVisible(true);

        JButton tempButton = new JButton("hello");

        dialogFrame.add(tempButton);
    }
}
