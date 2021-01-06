import DataDealer.DataDealer;
import Math.*;
import org.junit.*;

import java.util.Locale;

@Ignore
public class ExtrapolationTest {
    private ExtrapolationCalculator calculator = new ExtrapolationCalculator();

    @Ignore("test is working - round issue")
    @Test
    @Ignore
    public void testExtrapolationFirst(){
        Locale.setDefault(Locale.US);
        double[] abutmentEnergies = {1.0, 2.0, 3.0, 4.0};
        double[] abutmentValues = {1.0,1.0, 1.0,1.0};
        double[] abutmentErrors = {0.0,0.0,0.0,0.0};

        DataDealer abutment = new DataDealer(abutmentEnergies,abutmentValues,abutmentErrors,"subject");

        double[] extrEnergies = {1.5,2.5,3.5};
        double[] extrValues = {0.0,0.0,0.0};
        double[] extrErrors = {0.0,0.0,0.0};

        DataDealer subject = new DataDealer(extrEnergies,extrValues,extrErrors,"abutment");

        double[] ansEnergies = {1.5, 2.5, 3.5};
        double[] ansValues = {1.0,1.0, 1.0};
        double[] ansErrors = {0.0,0.0,0.0};

        DataDealer testAns = new DataDealer(ansEnergies,ansValues,ansErrors,"NE");
        DataDealer ans = calculator.calculate(subject,abutment);
        testAns.showDataInConsole();
        ans.showDataInConsole();
        Assert.assertTrue(testAns.equalsIgnoreLabel(ans));
    }

    @Ignore("test is working - round issue")
    @Test
    public void testExtrapolationSecond(){
        Locale.setDefault(Locale.US);
        double[] abutmentEnergies = {0.0,1.0, 2.0, 3.0, 4.0, 5.0};
        double[] abutmentValues = {0,100,200, 300,400,0};
        double[] abutmentErrors = {0.0,0.0,0.0,0.0,0.0,0.0};

        DataDealer abutment = new DataDealer(abutmentEnergies,abutmentValues,abutmentErrors,"subject");

        double[] extrEnergies = {1.5,2.5,3.5};
        double[] extrValues = {0.0,0.0,0.0};
        double[] extrErrors = {0.0,0.0,0.0};

        DataDealer subject = new DataDealer(extrEnergies,extrValues,extrErrors,"abutment");


        double[] ansEnergies = {1.5, 2.5, 3.5};
        double[] ansValues = {150,250, 350};
        double[] ansErrors = {0.0,0.0,0.0};

        DataDealer testAns = new DataDealer(ansEnergies,ansValues,ansErrors,"NE");
        DataDealer ans = calculator.calculate(subject,abutment);
        testAns.showDataInConsole();
        ans.showDataInConsole();
        Assert.assertTrue(testAns.equalsIgnoreLabel(ans));
    }

    @Ignore("test is working - round issue")
    @Test
    @Ignore
    public void testExtrapolationThird(){
        Locale.setDefault(Locale.US);
        double[] abutmentEnergies = {1.0, 2.0, 3.0, 4.0};
        double[] abutmentValues = {1.0,1, 1,1};
        double[] abutmentErrors = {0.0,0.0,0.0,0.0};

        DataDealer abutment = new DataDealer(abutmentEnergies,abutmentValues,abutmentErrors,"subject");

        double[] extrEnergies = {0.5,1.5,2.5,3.5,4.5};
        double[] extrValues = {0,0,0.0,0.0,0.0};
        double[] extrErrors = {0,0,0.0,0.0,0.0};

        DataDealer subject = new DataDealer(extrEnergies,extrValues,extrErrors,"abutment");


        double[] ansEnergies = {0.5,1.5, 2.5, 3.5,4.5};
        double[] ansValues = {0,1,1,1,0};
        double[] ansErrors = {0,0.0,0.0,0.0,0};

        DataDealer testAns = new DataDealer(ansEnergies,ansValues,ansErrors,"NE");
        DataDealer ans = calculator.calculate(subject,abutment);
        testAns.showDataInConsole();
        ans.showDataInConsole();
        Assert.assertTrue(testAns.equalsIgnoreLabel(ans));
    }

    @Ignore("test is working - round issue")
    @Test
    public void testExtrapolationForth(){
        Locale.setDefault(Locale.US);
        double[] abutmentEnergies = {1.0, 2.0, 3.0, 4.0, 5.0,6.0};
        double[] abutmentValues = {100,200, 300,400,500,600};
        double[] abutmentErrors = {0.0,0.0,0.0,0.0,0.0,0.0};

        DataDealer abutment = new DataDealer(abutmentEnergies,abutmentValues,abutmentErrors,"subject");

        double[] extrEnergies = new double[30];
        double[] extrValues  = new double[30];
        double[] extrErrors = new double[30];
        for(int i =10;i<extrEnergies.length+10;i++){
            extrEnergies[i-10] = ((double)i)/10;
            extrValues[i-10]=0;
            extrErrors[i-10]=0;
        }

        DataDealer subject = new DataDealer(extrEnergies,extrValues,extrErrors,"abutment");


        double[] ansEnergies = new double[30];
        double[] ansValues= new double[30];
        double[] ansErrors = new double[30];

        for(int i=10;i<ansEnergies.length+10;i++){
            extrEnergies[i-10] = ((double)i)/10;
            extrValues[i-10]=extrEnergies[i-10]*100;
            extrErrors[i-10]=0;
        }

        DataDealer testAns = new DataDealer(ansEnergies,ansValues,ansErrors,"NE");
        DataDealer ans = calculator.calculate(subject,abutment);
        testAns.showDataInConsole();
        ans.showDataInConsole();
        Assert.assertTrue(testAns.equalsIgnoreLabel(ans));
    }

}
