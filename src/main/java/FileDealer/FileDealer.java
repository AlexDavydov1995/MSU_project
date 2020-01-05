package FileDealer;

import DataDealer.DataDealer;

import java.io.*;
import java.util.ArrayList;

public class FileDealer {


    public String[] readAFile(String fileName) {
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
            e.printStackTrace();
        }
        return fileContent.toArray(new String[fileContent.size()]);
    }

    public void writeAFile(String name, DataDealer data) throws IOException {
        FileWriter fileWriter = new FileWriter(name, false);
        fileWriter.write("");
        try {
            for (int i = 0; i < data.getLength(); i++) {
                String line = data.getEnergyByIndex(i) + "\t"
                        + data.getCrossSectionByIndex(i) + "\t"
                        + data.getCrossSectionErrorByIndex(i)+"\n";
                fileWriter.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        fileWriter.flush();
    }
}
