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





        return null;
    }


    private double[] calculateACoefficients(double[] function){
        double[] aCoefficients = new double[function.length];
        for(int i=0;i<aCoefficients.length;i++){
            aCoefficients[i]=function[i];
        }
        return aCoefficients;
    }

    private double[] calculateBCoefficients(double[] energies, double[] function, double[] cCoefficients){
        double[] bCoefficients = new double[function.length];
        bCoefficients[0]=0;
        for(int i=1;i<bCoefficients.length;i++){
            double h = function[i]-function[i-1];
            bCoefficients[i]=(function[i]-function[i-1])/h+(2*cCoefficients[i]+cCoefficients[i-1])*h/3;
        }
        return bCoefficients;
    }

    private double[] calculateDCoefficients(double[] cCoefficients, double[] function){
        double[] dCoefficients = new double[function.length];
        dCoefficients[0]=0;
        for(int i=1;i<dCoefficients.length;i++){
            double h = function[i]-function[i-1];
            dCoefficients[i]=(cCoefficients[i]-cCoefficients[i-1])/(3*h);
        }
        return dCoefficients;
    }

    private double[] calculateCCoefficients(double[] function,double[] energies){
        double[] cCoefficients = new double[function.length];
        cCoefficients[0]=0;
        cCoefficients[cCoefficients.length-1]=0;
        /**
         * we need to solve 3-diagonal matrix
         * Thomas method
         * Russian resource
         * http://e-lib.gasu.ru/eposobia/metody/R_1_3.html
         *http://www.simumath.net/library/book.html?code=Interpol_splines
         */
        double[] tempBCoefficients = new double[cCoefficients.length];
        double[] steps = new double[cCoefficients.length];
        tempBCoefficients[0]=tempBCoefficients[tempBCoefficients.length]=0;
        for(int i=1;i<cCoefficients.length-1;i++){
            tempBCoefficients[i]=6*((function[i+1]-function[i])/(energies[i+1]-energies[i])-(function[i]-function[i-1])/(energies[i]-energies[i-1]));
        }

        return cCoefficients;
    }

}
