import DataDealer.DataDealer;
import junit.framework.TestCase;
import Math.BasicMath;
import Math.*;
import org.junit.Assert;
import org.junit.Test;

public class MathTest {

    private double testValue = 123.4567778;

    private double[] testEnergyArray = {1.0,2.0};
    private double[] testValueArray = {5.0,5.0};
    private double[] testNewValueArray = {10.0,10.0};
    private double[] testErrorsArray = {0.0,0.0};


    @Test
    public void testQuickRound(){
        Assert.assertEquals(123.46, BasicMath.quickRound(testValue),0.01);
    }

    @Test
    public void testSum(){
        DataDealer answerData = new DataDealer(testEnergyArray,testNewValueArray,testErrorsArray,"AnswerArray");
        DataDealer testData1 = new DataDealer(testEnergyArray,testValueArray,testErrorsArray,"Answer");
        DataDealer testData2 = new DataDealer(testEnergyArray,testValueArray,testErrorsArray,"Array");
        SumCalculator calculator = new SumCalculator();
        Assert.assertTrue(answerData.equals(calculator.calculate(testData1, testData2)));
    }




}
