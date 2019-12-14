package Math;

import DataDealer.DataDealer;

public class SumCalculator extends BasicMath implements ComplexMathDealer {
    public DataDealer calculate(DataDealer first, DataDealer second){
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
            return sum;
        }
        catch (Exception ex){
            ex.printStackTrace();
        }

        return null;
    }

}
