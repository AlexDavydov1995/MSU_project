package Math;

import DataDealer.DataDealer;

public class BasicMath {

    public static double quickRound(double number){
        return (double )Math.round(number*100)/100;
    }

    public static double pow2(double number){
        return Math.pow(number,2);
    }

    public static double pow3(double number){
        return Math.pow(number,3);
    }

    protected static boolean checkIfEnergyMatches(double energyPartial, double energyYield) {
        double eps = 0.05;
        if (Math.abs(energyPartial - energyYield) <= eps) return true;
        return false;
    }

    protected static void checkLength(DataDealer data1, DataDealer data2) throws Exception {
        if (data1.getLength() != data2.getLength()) {
            throw new Exception("arrays have not the same length");
        }
    }


}