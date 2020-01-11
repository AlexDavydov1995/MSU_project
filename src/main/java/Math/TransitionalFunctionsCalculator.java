package Math;

import DataDealer.DataDealer;

public class TransitionalFunctionsCalculator extends BasicMath implements ComplexMathDealer {
    @Override
    public  DataDealer calculate(DataDealer partial, DataDealer yield) throws Exception {
        logger.info("calculating TF");
        checkLength(partial, yield);
        int length = partial.getLength();
        double[] energy = new double[length];
        double[] values = new double[length];
        double[] errors = new double[length];

        for (int i = 0; i < length; i++) {
            if (!checkIfEnergyMatches(partial.getEnergyByIndex(i), yield.getEnergyByIndex(i))) {
                throw new Exception("energies dont match");
            }

            energy[i] = partial.getEnergyByIndex(i);
            try {
                values[i] = partial.getCrossSectionByIndex(i) / yield.getCrossSectionByIndex(i);

            } catch (ArithmeticException e) {
            }
            if (partial.getCrossSectionByIndex(i) == 0 && partial.getCrossSectionErrorByIndex(i) == 0) {
                errors[i] = 0;
            } else {

                try {
                    errors[i] = Math.sqrt(pow2(1 / yield.getCrossSectionByIndex(i) * partial.getCrossSectionErrorByIndex(i)) +
                            pow2((partial.getCrossSectionByIndex(i) / pow2(yield.getCrossSectionByIndex(i) * yield.getCrossSectionErrorByIndex(i)))));
                } catch (ArithmeticException e) {
                }
            }
        }
        String multiplicity = partial.getLabel().replaceAll("\\D", "");
        return new DataDealer(energy, values, errors, "F" + multiplicity);
    }
}
