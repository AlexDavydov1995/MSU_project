package Math;

import DataDealer.DataDealer;

public class MathDealer {
    public MathDealer() {

    }

    public static double[][] calculateIntegralCrossSectionAndEnergyCenter(DataDealer data) {
        double[][] answer = new double[2][2];
        double integralCrossSection = 0;
        double errorCrossSection = 0;
        double sumEnergyCenter = 0;
        double errorSumEnergyCenter = 0;
        for (int i = 0; i < data.getLength() - 1; i++) {

            double tempEnergyValueMin = data.getEnergyByIndex(i);
            double tempEnergyValueMax = data.getEnergyByIndex(i + 1);

            double tempCrossSectionValueHigh = data.getCrossSectionByIndex(i + 1);
            double tempCrossSectionValueLow = data.getCrossSectionByIndex(i);

            double tempCrossSectionErrorValueHigh = data.getCrossSectionErrorByIndex(i + 1);
            double tempCrossSectionErrorValueLow = data.getCrossSectionErrorByIndex(i);

            integralCrossSection += (tempCrossSectionValueHigh + tempCrossSectionValueLow) /
                    2 * (tempEnergyValueMax - tempEnergyValueMin);
            errorCrossSection = Math.sqrt(pow2(errorCrossSection) + pow2(tempEnergyValueMax - tempEnergyValueMin) *
                    (pow2(tempCrossSectionErrorValueHigh) + pow2(tempCrossSectionErrorValueLow)) / 4);
            sumEnergyCenter += (tempEnergyValueMax - tempEnergyValueMin) * (tempCrossSectionValueHigh + tempCrossSectionValueLow) /
                    2 * (tempEnergyValueMax + tempEnergyValueMin) / 2;
            errorSumEnergyCenter = Math.sqrt(pow2(errorSumEnergyCenter) + pow2(tempEnergyValueMax - tempEnergyValueMin) * (pow2(tempCrossSectionErrorValueHigh) + pow2(tempCrossSectionErrorValueLow)) /
                    pow2((tempEnergyValueMax - tempEnergyValueMin) * (tempCrossSectionValueHigh + tempCrossSectionValueLow) / 2) * pow2(tempEnergyValueMax + tempEnergyValueMin) * pow2(tempEnergyValueMax - tempEnergyValueMin) *
                    pow2(tempCrossSectionValueHigh + tempCrossSectionValueLow) / 4);
        }
        double energyCenter = sumEnergyCenter / integralCrossSection;
        double errorEnergyCenter = Math.sqrt(pow2(1 / integralCrossSection * errorSumEnergyCenter) + pow2(sumEnergyCenter / pow2(integralCrossSection) * errorCrossSection));

        answer[0][0] = integralCrossSection;
        answer[0][1] = errorCrossSection;
        answer[1][0] = energyCenter;
        answer[1][1] = errorEnergyCenter;

        return answer;
    }

    public static DataDealer calculateTransitionalFunction(DataDealer partial, DataDealer yield) throws Exception {
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
                System.out.println(e + "\n error in cross sections");
            }
            if (partial.getCrossSectionByIndex(i) == 0 && partial.getCrossSectionErrorByIndex(i) == 0) {
                errors[i] = 0;
            } else {

                try {
                    errors[i] = Math.sqrt(pow2(1 / yield.getCrossSectionByIndex(i) * partial.getCrossSectionErrorByIndex(i)) +
                            pow2((partial.getCrossSectionByIndex(i) / pow2(yield.getCrossSectionByIndex(i) * yield.getCrossSectionErrorByIndex(i)))));
                } catch (ArithmeticException e) {
                    System.out.println(e + "\n error in cross errors");
                }
            }
        }
        String multiplicity = partial.getLabel().replaceAll("\\D", "");
        DataDealer transitionalFunction = new DataDealer(energy, values, errors, "F" + multiplicity);
        return transitionalFunction;
    }

    public static DataDealer calculateEvaluatedCrossSections(DataDealer tf, DataDealer yield) throws Exception {
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

    public static DataDealer correct(DataDealer data, double energyCorrection, double crossSectionCorrection) {
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

    public static DataDealer sum(DataDealer first, DataDealer second){
        return null;
    }

    private static boolean checkIfEnergyMatches(double energyPartial, double energyYield) {
        double eps = 0.05;
        if (Math.abs(energyPartial - energyYield) <= 0.05) return true;
        return false;
    }

    private static double pow2(double number) {
        return Math.pow(number, 2);
    }

    private static void checkLength(DataDealer data1, DataDealer data2) throws Exception {
        if (data1.getLength() != data2.getLength()) {
            throw new Exception("arrays have not the same length");
        }
    }
}
