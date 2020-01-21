package Math;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import DataDealer.DataDealer;

import java.text.DecimalFormat;

public class BasicMath {
    Logger logger = LogManager.getLogger(getClass());

    public static double quickRound(double number){
        DecimalFormat format = new DecimalFormat("#.##");
        return Double.parseDouble(format.format(number));
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
