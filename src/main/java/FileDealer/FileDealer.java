package FileDealer;

import DataDealer.DataDealer;

import java.io.*;
import java.util.ArrayList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FileDealer {
    Logger logger = LogManager.getRootLogger();

    public String[] readAFile(String fileName) {
        logger.info("reading file {}",fileName);
        ArrayList<String> fileContent = new ArrayList<>();
        try {
            File myFile = new File(fileName);
            FileReader fileReader = new FileReader(myFile);

            BufferedReader reader = new BufferedReader(fileReader);
            String str = null;
            while ((str = reader.readLine()) != null) {
                fileContent.add(str);
            }
            reader.close();
        } catch (Exception e) {
            logger.error("There is an error : ", e);
            e.printStackTrace();
        }
        return fileContent.toArray(new String[fileContent.size()]);
    }

    public void writeAFile(String name, DataDealer data) throws IOException {
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
}
