package Math;

import DataDealer.DataDealer;

public class ExtrapolationCalculator extends BasicMath implements ComplexMathDealer {
    public DataDealer calculate(DataDealer subject, DataDealer abutment){
        double[] abutmentEnergies = abutment.getEnergy();
        double[] abutmentValues = abutment.getCrossSection();
        double[] abutmentErrors = abutment.getCrossSectionError();
        double[] subjectEnergies = subject.getEnergy();
        double[] subjectValues = subject.getCrossSection();
        double[] subjectErrors = subject.getCrossSectionError();
        double[] newEnergies = new double[abutmentEnergies.length];
        double[] newValues = new double[abutmentValues.length];
        double[] newErrors = new double[abutmentErrors.length];
        double h = (subjectEnergies[subjectEnergies.length-1]-subjectEnergies[0])/(subjectEnergies.length-1);
        double[] coefficients = new double[subjectEnergies.length];
        for(int i=0;i<coefficients.length;i++){
            if(i==0){
                coefficients[i]=(4*subjectValues[i+1]-subjectValues[i+2]-3*subjectValues[i])/(2*h);
            } else if(i==coefficients.length-1){
                coefficients[i]=(4*subjectValues[i-1]-subjectValues[i-2]-3*subjectValues[i])/(2*h);
            } else{
                coefficients[i]=(subjectValues[i+1]-subjectValues[i-1])/(2*h);
            }
        }
        for(int i = 0;i<abutmentEnergies.length;i++){
            if (abutmentEnergies[i]<subjectEnergies[0] || abutmentEnergies[i]>subjectEnergies[subjectEnergies.length-1]){
                newValues[i]=0;
                newErrors[i]=0;
            } else {
                for(int j = 0;j<coefficients.length-1;j++){
                    if(abutmentEnergies[i]<=subjectEnergies[j+1] && abutmentEnergies[i]>=subjectEnergies[j]) {
                        double first = pow2(subjectEnergies[j + 1] - abutmentEnergies[i]) *
                                (2 * (abutmentEnergies[i] - subjectEnergies[j]) + h) / pow3(h);
                        double second = pow2(abutmentEnergies[i] - subjectEnergies[j]) *
                                (2 * (subjectEnergies[j + 1] - abutmentEnergies[i]) + h) / pow3(h);
                        double third = pow2(subjectEnergies[j + 1] - abutmentEnergies[i]) *
                                (abutmentEnergies[i] - subjectEnergies[j]) / pow2(h);
                        double forth = pow2(abutmentEnergies[i] - subjectEnergies[j]) *
                                (abutmentEnergies[i] - subjectEnergies[j]) / pow2(h);
                        newValues[i] = first * subjectValues[j] + second * subjectValues[j+1] +
                                third * coefficients[j] + forth * coefficients[j+1];
                        newErrors[i] = Math.sqrt(pow2(subjectErrors[j]) + pow2(subjectErrors[j + 1]) + pow2(abutmentErrors[i]));
                    }
                }
            }
            newEnergies[i]=abutmentEnergies[i];
        }
        //DataDealer answer = new DataDealer(newEnergies,newValues,newErrors,"ExtrapOf"+subject.getLabel());
        return new DataDealer(newEnergies,newValues,newErrors,"ExtrapOf"+subject.getLabel());
    }

}
