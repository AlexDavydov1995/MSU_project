package Math;

import DataDealer.DataDealer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CorrectionCalculator extends BasicMath implements SemiComplexMathDealer {
    public DataDealer calculate(DataDealer data, double energyCorrection, double crossSectionCorrection) {
        Logger logger = LogManager.getLogger();
        logger.info(logger.getName());
        int length = data.getLength();
        double[] energy = new double[length];
        double[] values = new double[length];
        double[] errors = new double[length];
        for (int i = 0; i < length; i++) {
            energy[i] = data.getEnergyByIndex(i) - energyCorrection;
            values[i] = data.getCrossSectionByIndex(i) * crossSectionCorrection;
            errors[i] = data.getCrossSectionErrorByIndex(i) * crossSectionCorrection;
            logger.trace("current row" + energy[i]+"\t"+values[i]+"\t"+errors[i]);
        }
        DataDealer correction = new DataDealer(energy, values, errors, "Cor" + data.getLabel());
        return correction;
    }

}
