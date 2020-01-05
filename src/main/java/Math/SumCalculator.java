package Math;

import DataDealer.DataDealer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SumCalculator extends BasicMath implements ComplexMathDealer {
    public DataDealer calculate(DataDealer first, DataDealer second){
        Logger logger = LogManager.getLogger();
        logger.trace("in calculating stage");
        try{

            checkLength(first,second);
            int length = first.getLength();
            double[] energy = new double[length];
            double[] values = new double[length];
            double[] errors = new double[length];
            for(int i=0;i<length;i++){
                energy[i]=first.getEnergyByIndex(i);
                values[i]=first.getCrossSectionByIndex(i)+second.getCrossSectionByIndex(i);
                errors[i]=Math.sqrt(pow2(first.getCrossSectionErrorByIndex(i))+pow2(second.getCrossSectionErrorByIndex(i)));
            }
            DataDealer sum = new DataDealer(energy,values,errors,first.getLabel()+second.getLabel());
            logger.info(sum.toString());
            return sum;
        }
        catch (Exception ex){
            logger.error(ex);
        }
        return null;
    }

}
