package Math;

import DataDealer.DataDealer;

public class CorrectionCalculator extends BasicMath implements SemiComplexMathDealer {
    public DataDealer calculate(DataDealer data, double energyCorrection, double crossSectionCorrection) {
        int length = data.getLength();
        double[] energy = new double[length];
        double[] values = new double[length];
        double[] errors = new double[length];
        for (int i = 0; i < length; i++) {
            energy[i] = data.getEnergyByIndex(i) - energyCorrection;
            values[i] = data.getCrossSectionByIndex(i) * crossSectionCorrection;
            errors[i] = data.getCrossSectionErrorByIndex(i) * crossSectionCorrection;
        }
        DataDealer correction = new DataDealer(energy, values, errors, "Cor" + data.getLabel());
        return correction;
    }

}
