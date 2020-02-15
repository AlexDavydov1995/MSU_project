package FileDealer;

import javax.swing.*;

public class JFileChooserWrapper extends JFileChooser {
    private static String LAST_FOLDER=System.getProperty("user.dir");
    public JFileChooserWrapper(String currentFolder){
        super(currentFolder);
    }

    public JFileChooserWrapper(){
        super(LAST_FOLDER);
    }

    public synchronized void setLastFolder(String currentFolder){
        LAST_FOLDER=currentFolder;
    }

    public static synchronized String getLastFolder(){
        return LAST_FOLDER;
    }
}
