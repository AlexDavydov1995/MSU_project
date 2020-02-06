package Math;

import DataDealer.DataDealer;

public class DeltaCalculator extends BasicMath implements ComplexMathDealer {
    @Override
    public DataDealer calculate(DataDealer minuend, DataDealer subtract)  {
        try {
            checkLength(minuend, subtract);
            int length = minuend.getLength();
            double[] answerValues = new double[length];
            double[] answerErrors = new double[length];
            for(int i=0; i<length;i++){
                if(checkIfEnergyMatches(minuend.getEnergyByIndex(i),subtract.getEnergyByIndex(i))) {
                    answerValues[i] = minuend.getCrossSectionByIndex(i) - subtract.getCrossSectionByIndex(i);
                    answerErrors[i] = Math.sqrt(pow2(minuend.getCrossSectionErrorByIndex(i)) + pow2(subtract.getCrossSectionErrorByIndex(i)));
                }
            }
            return new DataDealer(minuend.getEnergy(),answerValues,answerErrors,"DeltaBetween"+
                    minuend.getLabel()+"And"+subtract.getLabel());

        }
        catch (Exception ex) {
            logger.error("There is an error: ", ex);
        }

        return null;
    }
}
