import Gui.MainGUI;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class MainClass {
    static final Logger logger = LogManager.getLogger(MainClass.class.getName());
    public static void main(String... args) {
        logger.info("It started");
        go();
    }

    public static void go() {
        MainGUI myGui = new MainGUI();
        logger.info("maingui created");
        myGui.go();
    }
}
