package Math;

import DataDealer.DataDealer;

public class ExtrapolationCalculator extends BasicMath implements ComplexMathDealer {
    public DataDealer calculate(DataDealer subject, DataDealer abutment) {
        logger.info(logger.getName());
        double[] subjectEnergies = subject.getEnergy();
        double[] subjectValues = subject.getCrossSection();
        double[] subjectErrors = subject.getCrossSectionError();
        double[] abutmentEnergies = abutment.getEnergy();
        double[] abutmentValues = abutment.getCrossSection();
        double[] abutmentErrors = abutment.getCrossSectionError();
        double[] newEnergies = new double[abutmentEnergies.length];
        double[] newValues = new double[abutmentValues.length];
        double[] newErrors = new double[abutmentErrors.length];

        Spline[] splines = new Spline[subject.getEnergy().length - 1];

        for (int i = 0; i < splines.length; i++) {
            splines[i] = new Spline();
            splines[i].setA(subjectValues[i]);
            splines[i].setX(subjectEnergies[i]);
        }
        splines[0].setC(0);

        double[] alpha = new double[splines.length - 1];
        double[] beta = new double[splines.length - 1];
        double A = 0, B = 0, C = 0, F = 0, h_i, h_i1, z;
        alpha[0] = beta[0] = 0;
        for (int i = 1; i < alpha.length; ++i) {
            h_i = subjectEnergies[i] - subjectEnergies[i - 1];
            h_i1 = subjectEnergies[i + 1] - subjectEnergies[i];
            A = h_i;
            C = 2 * (h_i + h_i1);
            B = h_i1;
            F = 6 * ((subjectValues[i + 1] - subjectValues[i]) / h_i1 - (subjectValues[i] - subjectValues[i - 1]) / h_i);
            z = (A * alpha[i - 1] + C);
            alpha[i] = -B / z;
            beta[i] = (F - A * beta[i - 1]) / z;
        }
        splines[splines.length - 1].setC((F - A * beta[splines.length - 2]) / (C + A * alpha[splines.length - 2]));

        for (int i = splines.length - 2; i > 0; i--) {
            splines[i].setC(alpha[i] * splines[i + 1].getC() + beta[i]);
        }

        for (int i = splines.length - 2; i > 0; i--) {
            double hi = subjectEnergies[i] - subjectEnergies[i - 1];
            splines[i].setD((splines[i].getC() - splines[i - 1].getC()) / hi);
            splines[i].setB(hi * (2 * splines[i].getC() + splines[i - 1].getC()) / 6 + (subjectValues[i] - subjectValues[i - 1]) / hi);
        }

        for (int i = 0; i < abutmentValues.length; i++) {
            newEnergies[i] = abutmentEnergies[i];
            if (abutmentEnergies[i] <= subjectEnergies[0] || abutmentEnergies[i] >= subjectEnergies[subjectEnergies.length - 1]) {
                newValues[i] = 0;
                newErrors[i] = 0;
            } else {
                for (int j = 0; j < subjectEnergies.length - 1; j++) {
                    if (abutmentEnergies[i] >= subjectEnergies[j] && abutmentEnergies[i] <= subjectEnergies[j + 1]) {
                        double dx = abutmentEnergies[i] - subjectEnergies[j];
                        newValues[i] = splines[j].getA() + (splines[j].getB() + (splines[j].getC() / 2 + splines[j].getD() / 6 * dx) * dx) * dx;
                        newErrors[i] = Math.sqrt(pow2(subjectErrors[j]/subjectValues[j]) + pow2(abutmentErrors[i]/abutmentValues[i]))*newValues[i];
                        if(Double.isNaN(newErrors[i])){
                            newErrors[i] = 0.0;
                        }
                    }
                }
            }
        }
        return new DataDealer(newEnergies, newValues, newErrors, "ExtrapOf" + subject.getLabel()+"On"+abutment.getLabel());
    }

    static class Spline {
        private double a, b, c, d, x;

        Spline(){
            this.a = 0;
            this.b = 0;
            this.c = 0;
            this.d = 0;
            this.x = 0;
        }

        Spline(double a, double b, double c, double d, double x) {
            this.a = a;
            this.b = b;
            this.c = c;
            this.d = d;
            this.x = x;
        }

        private double getA() {
            return a;
        }

        private double getB() {
            return b;
        }

        private double getC() {
            return c;
        }

        private double getD() {
            return d;
        }

        private double getX() {
            return x;
        }

        private void setA(double number) {
            this.a = number;
        }

        private void setB(double number) {
            this.b = number;
        }

        private void setC(double number) {
            this.c = number;
        }

        private void setD(double number) {
            this.d = number;
        }

        private void setX(double number) {
            this.x = number;
        }


    }
}
