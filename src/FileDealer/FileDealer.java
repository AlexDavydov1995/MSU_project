package FileDealer;

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
}
