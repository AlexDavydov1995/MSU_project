package Gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Trio extends JPanel {
    JTextField text;
    JLabel label;
    JButton button;

    public Trio(String textOfLabel, String textOfTextField, String textOfButton) {
        super();
        text = new JTextField(textOfTextField);
        label = new JLabel(textOfLabel);
        button = new JButton(textOfButton);
        button.addActionListener(new BrowseButtonListener());
        this.setLayout(new BorderLayout());

        JPanel leftPanel = new JPanel(new GridLayout(2, 1));
        leftPanel.add(label);
        leftPanel.add(button);
        this.add(BorderLayout.WEST, leftPanel);
        this.add(text);
    }

    public String getFilePath() {
        return text.getText();
    }


    class BrowseButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String currentDir = System.getProperty("user.dir");
            final JFileChooser fileChooser = new JFileChooser(currentDir);
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

            int returnVal = fileChooser.showOpenDialog(getParent());
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                System.out.println("we CAN choose file");
                String filePath = fileChooser.getSelectedFile().getAbsolutePath();

                text.setText(filePath);
            } else {
                text.setText("incorrect file");
            }
        }
    }
}
