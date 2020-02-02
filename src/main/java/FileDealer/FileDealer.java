package FileDealer;

import DataDealer.DataDealer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;

public class FileDealer {
    Logger logger = LogManager.getRootLogger();

    public String[] readAFile(Path filePath) {
        logger.info("reading file {}",filePath);
        ArrayList<String> fileContent = new ArrayList<>();
        try {
            File myFile = new File(String.valueOf(filePath));
            FileReader fileReader = new FileReader(myFile);

            BufferedReader reader = new BufferedReader(fileReader);
            String str;
            while ((str = reader.readLine()) != null) {
                fileContent.add(str);
            }
            reader.close();
            return fileContent.toArray(new String[fileContent.size()]);
        } catch (Exception e) {
            logger.error("There is an error : ", e);
            e.printStackTrace();
        }
        return null;
    }

    public void writeAFileFromDataDealer(String name, DataDealer data) throws Exception {
        FileWriter fileWriter = new FileWriter(name, false);
        logger.info("writing file {}", name);
        fileWriter.write("");
        try {
            for (int i = 0; i < data.getLength(); i++) {
                String line = data.getEnergyByIndex(i) + "\t"
                        + data.getCrossSectionByIndex(i) + "\t"
                        + data.getCrossSectionErrorByIndex(i)+"\n";
                fileWriter.append(line);
            }
        } catch (Exception e) {
            logger.error("There is an error : ", e);
            e.printStackTrace();
        }
        fileWriter.flush();
    }

    public void writeAFileFromStringArray(String name, String[] strings) throws Exception {
        FileWriter fileWriter = new FileWriter(name, false);
        logger.info("writing file {}", name);
        fileWriter.write("");
        try {
            for (int i = 0; i < strings.length; i++) {
                fileWriter.append(strings[i]);
            }
        } catch (Exception e) {
            logger.error("There is an error : ", e);
            e.printStackTrace();
        }
        fileWriter.flush();
    }
}
