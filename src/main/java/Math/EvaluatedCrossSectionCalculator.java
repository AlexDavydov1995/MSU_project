package Math;

import DataDealer.DataDealer;

public class EvaluatedCrossSectionCalculator extends BasicMath implements ComplexMathDealer {
    public DataDealer calculate(DataDealer tf, DataDealer yield) throws Exception {
        checkLength(tf, yield);
        int length = tf.getLength();
        double[] energy = new double[length];
        double[] values = new double[length];
        double[] errors = new double[length];
        for (int i = 0; i < length; i++) {
            if (!checkIfEnergyMatches(tf.getEnergyByIndex(i), yield.getEnergyByIndex(i))) {
                throw new Exception("energies dont match");
            }
            energy[i] = tf.getEnergyByIndex(i);
            values[i] = tf.getCrossSectionByIndex(i) * yield.getCrossSectionByIndex(i);
            errors[i] = Math.sqrt(pow2(yield.getCrossSectionByIndex(i) * tf.getCrossSectionErrorByIndex(i)) + pow2(tf.getCrossSectionByIndex(i) * yield.getCrossSectionErrorByIndex(i)));
        }
        String multiplicity = tf.getLabel().replaceAll("\\D", "");
        DataDealer evaluation = new DataDealer(energy, values, errors, "Eval" + multiplicity);
        return evaluation;
    }
}
