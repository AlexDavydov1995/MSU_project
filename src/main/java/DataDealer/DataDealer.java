package DataDealer;

import FileDealer.FileDealer;
import Math.BasicMath;

import java.nio.file.Path;
import vars.RunVariables;
import java.util.Arrays;

public class DataDealer {
    private double[] energy;
    private double[] crossSection;
    private double[] crossSectionError;
    private int length;
    private String label;

    public DataDealer(double[] energy, double[] values, double[] errors, String label) {
        assert checkLength(energy,values,errors);
        this.energy = roundArray(energy);
        this.crossSection = roundArray(values);
        this.crossSectionError = roundArray(errors);
        this.length = energy.length;
        this.label = label;
    }

    private boolean checkLength(double[] energy, double[] values, double[] errors) {
        boolean res = false;
        try {
            res = energy.length == values.length && values.length == errors.length;
        } catch (Exception e) {
            System.out.println("Arrays dont match each other \n" + e);
        }
        return res;
    }

    public DataDealer(Path filePath) {
        FileDealer fileDealer = new FileDealer();
        String[] fileData = fileDealer.readAFile(filePath);
        label = filePath.getFileName().toString();
        length = fileData.length;
        energy = new double[length];
        crossSection = new double[length];
        crossSectionError = new double[length];
        try {
            assert checkLength(energy,crossSection,crossSectionError);
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

    public double[] getEnergy() {
        return energy;
    }

    public double[] getCrossSection() {
        return crossSection;
    }

    public double[] getCrossSectionError() {
        return crossSectionError;
    }

    public void showDataInConsole() {
        for (int i = 0; i < length; i++) {
            System.out.println(energy[i] + " " + crossSection[i] + " " + crossSectionError[i]);
        }
    }

    public String getLabel() {
        return label;
    }

    public boolean equals(DataDealer data){
        return equalsIgnoreLabel(data) &&
                 this.getLabel().equals(data.getLabel());
    }

    public boolean equalsIgnoreLabel(DataDealer data){
        return Arrays.equals(this.getEnergy(),(data.energy))
                && Arrays.equals(this.getCrossSection(), data.getCrossSection())
                && Arrays.equals(this.getCrossSectionError(),data.getCrossSectionError());
    }

    public String toString(){
        return this.label+"\t"+this.length;
    }

    public DataDealer copy(){
        return new DataDealer(this.getEnergy(), this.getCrossSection(), this.getCrossSectionError(), this.getLabel());
    }

    private static double[] roundArray(double[] array){
        double[] answer = new double[array.length];
        for(int i=0;i<array.length;i++)
            answer[i]=BasicMath.quickRound(array[i]);
        return answer;
    }

    public void cutDataOutOfRange(double leftBound, double rightBound){

    }
}
