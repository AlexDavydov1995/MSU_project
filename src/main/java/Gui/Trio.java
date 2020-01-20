package Gui;

import vars.GlobalVariables;

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

    public Trio(String textOfLabel, String textOfTextField){
      this(textOfLabel,textOfTextField,"Browse");
    }

    public Trio(String textOfLabel){
        this(textOfLabel, "");
    }

    public String getFilePath() {
        return text.getText();
    }


    class BrowseButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            final JFileChooser fileChooser = new JFileChooser(GlobalVariables.LAST_FOLDER);
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

            int returnVal = fileChooser.showOpenDialog(getParent());
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                GlobalVariables.LAST_FOLDER = fileChooser.getSelectedFile().getParent();
                String filePath = fileChooser.getSelectedFile().getPath();
                text.setText(filePath);
            } else {
                text.setText("incorrect file");
            }
        }
    }
}
