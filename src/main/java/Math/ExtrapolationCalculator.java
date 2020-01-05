package Math;

import DataDealer.DataDealer;

public class ExtrapolationCalculator extends BasicMath implements ComplexMathDealer {
    public DataDealer calculate(DataDealer subject, DataDealer abutment) {
        logger.info(logger.getName());
        double[] abutmentEnergies = abutment.getEnergy();
        double[] abutmentValues = abutment.getCrossSection();
        double[] abutmentErrors = abutment.getCrossSectionError();
        double[] subjectEnergies = subject.getEnergy();
        double[] subjectValues = subject.getCrossSection();
        double[] subjectErrors = subject.getCrossSectionError();
        double[] newEnergies = new double[subjectEnergies.length];
        double[] newValues = new double[subjectValues.length];
        double[] newErrors = new double[subjectErrors.length];

        Spline[] splines = new Spline[abutment.getEnergy().length - 1];

        for (int i = 0; i < splines.length; i++) {
            splines[i] = new Spline();
            splines[i].setA(abutmentValues[i]);
            splines[i].setX(abutmentEnergies[i]);
        }
        splines[0].setC(0);

        double[] alpha = new double[splines.length - 1];
        double[] beta = new double[splines.length - 1];
        double A = 0, B = 0, C = 0, F = 0, h_i, h_i1, z;
        alpha[0] = beta[0] = 0;
        for (int i = 1; i < alpha.length; ++i) {
            h_i = abutmentEnergies[i] - abutmentEnergies[i - 1];
            h_i1 = abutmentEnergies[i + 1] - abutmentEnergies[i];
            A = h_i;
            C = 2 * (h_i + h_i1);
            B = h_i1;
            F = 6 * ((abutmentValues[i + 1] - abutmentValues[i]) / h_i1 - (abutmentValues[i] - abutmentValues[i - 1]) / h_i);
            z = (A * alpha[i - 1] + C);
            alpha[i] = -B / z;
            beta[i] = (F - A * beta[i - 1]) / z;
        }
        splines[splines.length - 1].setC((F - A * beta[splines.length - 2]) / (C + A * alpha[splines.length - 2]));

        for (int i = splines.length - 2; i > 0; i--) {
            splines[i].setC(alpha[i] * splines[i + 1].getC() + beta[i]);
        }

        for (int i = splines.length - 2; i > 0; i--) {
            double hi = abutmentEnergies[i] - abutmentEnergies[i - 1];
            splines[i].setD((splines[i].getC() - splines[i - 1].getC()) / hi);
            splines[i].setB(hi * (2 * splines[i].getC() + splines[i - 1].getC()) / 6 + (abutmentValues[i] - abutmentValues[i - 1]) / hi);
        }

        for (int i = 0; i < subjectValues.length; i++) {
            newEnergies[i] = subjectEnergies[i];
            if (subjectEnergies[i] <= abutmentEnergies[0] || subjectEnergies[i] >= abutmentEnergies[abutmentEnergies.length - 1]) {
                newValues[i] = 0;
                newErrors[i] = 0;
            } else {
                for (int j = 0; j < abutmentEnergies.length - 1; j++) {
                    if (subjectEnergies[i] >= abutmentEnergies[j] && subjectEnergies[i] <= abutmentEnergies[j + 1]) {
                        double dx = subjectEnergies[i] - abutmentEnergies[j];
                        newValues[i] = splines[j].getA() + (splines[j].getB() + (splines[j].getC() / 2 + splines[j].getD() / 6 * dx) * dx) * dx;
                        newErrors[i] = Math.sqrt(pow2(abutmentErrors[j]) + pow2(subjectErrors[i]));
                    }
                }
            }
        }
        return new DataDealer(newEnergies, newValues, newErrors, "ExtrapOf" + subject.getLabel());
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
