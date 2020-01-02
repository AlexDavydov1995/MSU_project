package Math;

import DataDealer.DataDealer;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class IntegralCrossSectionCalculator extends BasicMath implements SimpleMathDealer{

    @Override
    public double[][] calculate(DataDealer data) {
        Logger logger = LogManager.getLogger();
        logger.info(logger.getName());
        logger.info(logger.getLevel());
        logger.info("integral cross sections \n getting data from "+data.toString());
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
            errorCrossSection = Math.sqrt(BasicMath.pow2(errorCrossSection) + pow2(tempEnergyValueMax - tempEnergyValueMin) *
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
        logger.info(integralCrossSection+"\t"+energyCenter);
        return answer;
    }
}

