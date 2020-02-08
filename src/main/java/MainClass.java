import Gui.MainGUI;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.Properties;

public class MainClass {
    static final Logger logger = LogManager.getLogger(MainClass.class.getName());
    public static void main(String... args) {
        logger.info("It started");
        prepareRuntimeEnvironment();
        go();
    }

    private static void go() {
        MainGUI myGui = new MainGUI();
        logger.info("main gui created");
        myGui.go();
    }

    private static void prepareRuntimeEnvironment(){
        Locale.setDefault(Locale.US);
        try{
            Properties prop = new Properties();
            InputStream  inputStream = MainClass.class.getResourceAsStream("config.properties");
            prop.load(inputStream);
            logger.info("file.separator "+prop.getProperty("file.separator"));
        }
        catch (IOException ex){
            logger.error("Error during properties loading",ex);
        }
    }

}
