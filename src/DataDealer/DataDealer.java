package DataDealer;

import FileDealer.FileDealer;

public class DataDealer {
    private double[] energy;
    private double[] crossSection;
    private double[] crossSectionError;
    private int length;

    public DataDealer(String fileName) {
        FileDealer fileDealer = new FileDealer();
        String[] fileData = fileDealer.readAFile(fileName);
        length = fileData.length;
        energy = new double[length];
        crossSection = new double[length];
        crossSectionError = new double[length];
        try {
            int i = 0;
            for (String str : fileData) {
                String[] temp = str.split("\\s");
                energy[i] = Double.parseDouble(temp[0]);
                crossSection[i] = Double.parseDouble(temp[1]);
                crossSectionError[i] = Double.parseDouble(temp[2]);
                i++;
            }
        } catch (Exception e) {
            System.out.println("Exception!!!");
            System.out.println(e);
        }
    }

    public int getLength() {
        return length;
    }

    public double getEnergyByIndex(int index) {
        return energy[index];
    }

    public double getCrossSectionByIndex(int index) {
        return crossSection[index];
    }

    public double getCrossSectionErrorByIndex(int index) {
        return crossSectionError[index];
    }

    public void showDataInConsole() {
        for (int i = 0; i < length; i++) {
            System.out.println(energy[i] + " " + crossSection[i] + " " + crossSectionError[i]);
        }
    }


}
