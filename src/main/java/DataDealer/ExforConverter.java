package DataDealer;

import Math.BasicMath;
import vars.GlobalVariables;

public class ExforConverter {
    public  String[] convertToExforStrings(DataDealer data){
        DataDealer roundedData = roundAllNumbers(data);
        int maxEnergyLength=0;
        int maxCrossSectionLength=0;
        int maxCrossSectionErrorLength=0;
        //Calculating max length
        for(int i=0; i< roundedData.getLength();i++){
            maxEnergyLength=maxLengthIteration(maxEnergyLength, roundedData.getEnergyByIndex(i));
            maxCrossSectionLength=maxLengthIteration(maxCrossSectionLength, roundedData.getCrossSectionByIndex(i));
            maxCrossSectionErrorLength=maxLengthIteration(maxCrossSectionErrorLength, roundedData.getCrossSectionErrorByIndex(i));
        }
        String[] answer = new String[roundedData.getLength()];
        for(int i=0;i<answer.length;i++){
            StringBuilder stringBuilder = new StringBuilder(GlobalVariables.EXFOR_CELL_SIZE*3);
            stringBuilder.append(doubleToExforString(data.getEnergyByIndex(i),maxEnergyLength));
            stringBuilder.append(doubleToExforString(data.getCrossSectionByIndex(i),maxCrossSectionLength));
            stringBuilder.append(doubleToExforString(data.getCrossSectionErrorByIndex(i),maxCrossSectionErrorLength));
            answer[i]=stringBuilder.toString()+"\n";
        }
        return answer;
    }

    private static DataDealer roundAllNumbers(DataDealer data){
        double[] energy = data.getEnergy();
        double[] crossSection =data.getCrossSection();
        double[] crossSectionError = data.getCrossSectionError();
        for(int i=0;i<data.getLength();i++){
            energy[i] = BasicMath.quickRound(energy[i]);
            crossSection[i]=BasicMath.quickRound(crossSection[i]);
            crossSectionError[i]=BasicMath.quickRound(crossSectionError[i]);
        }
        return new DataDealer(energy,crossSection,crossSectionError,data.getLabel());
    }

    private static String doubleToExforString(double number, int maxLengthInArray){
        String stringValueOfNumber =  String.format("%.2f", number);
        int doubleLength = stringValueOfNumber.length();
        int numberOfSpacesBeforeDouble = maxLengthInArray - doubleLength;
        return " ".repeat(numberOfSpacesBeforeDouble) + stringValueOfNumber + " ".repeat(GlobalVariables.EXFOR_CELL_SIZE-maxLengthInArray);
    }

    private static int maxLengthIteration(int currentMaxLength,double currentNumber){
        int currentNumberLength = String.valueOf(currentNumber).length();
        if(currentNumberLength>currentMaxLength)
            return currentNumberLength;
        return currentMaxLength;
    }

    private static int numberOfZerosAfterDecimalDelimiter(String stringNumber){
        return 0;
    }
}
