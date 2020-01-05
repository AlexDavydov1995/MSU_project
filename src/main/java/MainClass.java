import Gui.MainGUI;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class MainClass {
    private static Logger logger = LogManager.getLogger(MainClass.class);
    public static void main(String... args) {
        logger.info("It started");
        logger.info(logger.getName());
        logger.info(logger.getLevel());
        go();
    }

    public static void go() {
        logger.info("method 'go' started");
        MainGUI myGui = new MainGUI();
        logger.info("maingui created");
        myGui.go();
    }
}
