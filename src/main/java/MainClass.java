import Gui.MainGUI;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import vars.RunVariables;

import java.util.Locale;

public class MainClass {
    static final Logger logger = LogManager.getLogger(MainClass.class.getName());
    public static void main(String... args) {
        logger.info("It started");
        prepareRuntimeEnvironment();
        go();
    }

    private static void go() {
        MainGUI myGui = new MainGUI();
        logger.info("maingui created");
        myGui.go();
    }

    private static void prepareRuntimeEnvironment(){
        Locale.setDefault(Locale.US);
        RunVariables.FILE_SEPARATOR = System.getProperty("file.separator");
    }

}
