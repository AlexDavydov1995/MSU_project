import DataDealer.DataDealer;
import junit.framework.TestCase;
import Math.BasicMath;
import Math.*;
public class MathTest extends TestCase {

    double testValue = 123.4567778;

    double[] testEnergyArray = {1.0,2.0};
    double[] testValueArray = {5.0,5.0};
    double[] testNewValueArray = {10.0,10.0};
    double[] testErrorsArray = {0.0,0.0};


    public void testQuickRound(){

        assertEquals(123.46, BasicMath.quickRound(testValue));
    }

    public void testSum(){


        DataDealer answerData = new DataDealer(testEnergyArray,testNewValueArray,testErrorsArray,"AnswerArray");
        DataDealer testData1 = new DataDealer(testEnergyArray,testValueArray,testErrorsArray,"Answer");
        DataDealer testData2 = new DataDealer(testEnergyArray,testValueArray,testErrorsArray,"Array");
        SumCalculator calculator = new SumCalculator();
        assertTrue(answerData.equals( calculator.calculate(testData1,testData2)));
    }




}
